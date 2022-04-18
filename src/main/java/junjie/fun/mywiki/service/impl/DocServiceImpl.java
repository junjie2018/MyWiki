package junjie.fun.mywiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import junjie.fun.mywiki.entity.Content;
import junjie.fun.mywiki.entity.Doc;
import junjie.fun.mywiki.exception.BusinessException;
import junjie.fun.mywiki.mapper.ContentMapper;
import junjie.fun.mywiki.mapper.DocMapper;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageDocCondition;
import junjie.fun.mywiki.request.doc.CreateOrUpdateDocRequest;
import junjie.fun.mywiki.response.data.DocData;
import junjie.fun.mywiki.service.DocService;
import junjie.fun.mywiki.utils.CopyUtils;
import junjie.fun.mywiki.context.RequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

import static junjie.fun.mywiki.constant.code.BusinessCode.HAS_ALREADY_VOTE;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

    private final ContentMapper contentMapper;
    private final StringRedisTemplate stringRedisTemplate;

    public Page<DocData> pageDoc(PageRequest<PageDocCondition> request) {
        PageDocCondition condition = request.getCondition() == null ?
                new PageDocCondition() :
                request.getCondition();

        Page<Doc> pageEntity = new Page<>(request.getCurrent(), request.getSize());

        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<Doc>()
                .eq(ObjectUtils.isNotEmpty(condition.getEBookId()), Doc::getEBookId, condition.getEBookId());

        baseMapper.selectPage(pageEntity, queryWrapper);

        return CopyUtils.copyPage(pageEntity, DocData.class);
    }

    public Long createOrUpdateDoc(CreateOrUpdateDocRequest request) {
        if (isNotEmpty(request.getId())) {
            // 更新文档
            LambdaUpdateWrapper<Doc> updateWrapperForDoc = new LambdaUpdateWrapper<Doc>()
                    .eq(Doc::getId, request.getId())
                    .set(Doc::getEBookId, request.getEbookId())
                    .set(Doc::getParentId, request.getParentId())
                    .set(Doc::getName, request.getName())
                    .set(Doc::getSort, request.getSort())
                    .set(Doc::getViewCount, request.getViewCount())
                    .set(Doc::getVoteCount, request.getVoteCount());

            this.baseMapper.update(null, updateWrapperForDoc);

            // 更新内容
            LambdaUpdateWrapper<Content> updateWrapperForContent = new LambdaUpdateWrapper<Content>()
                    .eq(Content::getDocId, request.getId())
                    .set(Content::getEBookId, request.getEbookId())
                    .set(Content::getContent, request.getContent());

            contentMapper.update(null, updateWrapperForContent);

            return request.getId();
        } else {
            Doc docInert = Doc.builder()
                    .eBookId(request.getEbookId())
                    .parentId(request.getParentId())
                    .name(request.getName())
                    .sort(request.getSort())
                    .viewCount(request.getViewCount())
                    .voteCount(request.getVoteCount())
                    .build();

            this.baseMapper.insert(docInert);

            Content contentInsert = Content.builder()
                    .eBookId(request.getEbookId())
                    .docId(docInert.getId())
                    .content(request.getContent())
                    .build();

            contentMapper.insert(contentInsert);

            return docInert.getId();
        }
    }

    @Override
    public void deleteDocs(List<Long> docIds) {
        // 删除关联的内容
        LambdaQueryWrapper<Content> contentDelete = new LambdaQueryWrapper<Content>()
                .in(Content::getDocId, docIds);

        contentMapper.delete(contentDelete);

        // 删除文档
        this.baseMapper.deleteBatchIds(docIds);
    }

    @Override
    public void vote(Long docId) {
        String ip = RequestContext.getRemoteAddress();

        String redisKey = String.format("vote:%s:%s", docId, ip);
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(redisKey))) {
            throw new BusinessException(HAS_ALREADY_VOTE);
        } else {
            this.baseMapper.increaseVoteCount(docId);

            // 5000秒后可再次操作
            stringRedisTemplate.opsForValue().set(redisKey, redisKey, Duration.ofSeconds(5000));
        }


        // 推送消息
        // todo 这部分稍后再写
    }

    @Override
    public String getContent(Long docId) {
        LambdaQueryWrapper<Content> queryWrapper = new LambdaQueryWrapper<Content>()
                .eq(Content::getDocId, docId)
                .select(Content::getContent);

        this.baseMapper.increaseViewCount(docId);

        Content content = contentMapper.selectOne(queryWrapper);

        return content.getContent();
    }
}
