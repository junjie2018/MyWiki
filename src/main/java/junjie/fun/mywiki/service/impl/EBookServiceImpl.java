package junjie.fun.mywiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import junjie.fun.mywiki.entity.Content;
import junjie.fun.mywiki.entity.Doc;
import junjie.fun.mywiki.entity.EBook;
import junjie.fun.mywiki.mapper.ContentMapper;
import junjie.fun.mywiki.mapper.DocMapper;
import junjie.fun.mywiki.mapper.EBookMapper;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageEBookCondition;
import junjie.fun.mywiki.request.ebook.CreateOrUpdateEBookRequest;
import junjie.fun.mywiki.response.data.EBookData;
import junjie.fun.mywiki.service.EBookService;
import junjie.fun.mywiki.utils.CopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class EBookServiceImpl extends ServiceImpl<EBookMapper, EBook> implements EBookService {

    private final ContentMapper contentMapper;
    private final DocMapper docMapper;

    public Long createOrUpdate(CreateOrUpdateEBookRequest request) {
        if (isNotEmpty(request.getId())) {
            LambdaUpdateWrapper<EBook> updateWrapper = new LambdaUpdateWrapper<EBook>()
                    .eq(EBook::getId, request.getId())
                    .set(EBook::getName, request.getName())
                    .set(EBook::getCategory1Id, request.getCategory1Id())
                    .set(EBook::getCategory2Id, request.getCategory2Id())
                    .set(EBook::getDescription, request.getDescription())
                    .set(EBook::getCover, request.getCover())
                    .set(EBook::getDocCount, request.getDocCount())
                    .set(EBook::getViewCount, request.getViewCount())
                    .set(EBook::getVoteCount, request.getVoteCount());

            this.baseMapper.update(null, updateWrapper);

            return request.getId();
        } else {
            EBook eBookInert = EBook.builder()
                    .name(request.getName())
                    .category1Id(request.getCategory1Id())
                    .category2Id(request.getCategory2Id())
                    .description(request.getDescription())
                    .cover(request.getCover())
                    .docCount(request.getDocCount())
                    .viewCount(request.getViewCount())
                    .voteCount(request.getVoteCount())
                    .build();

            this.baseMapper.insert(eBookInert);

            return eBookInert.getId();
        }
    }

    public Page<EBookData> pageEBook(PageRequest<PageEBookCondition> request) {
        PageEBookCondition condition = request.getCondition() == null ?
                new PageEBookCondition() :
                request.getCondition();

        Page<EBook> pageEntity = new Page<>(request.getPageNo(), request.getPageSize());

        LambdaQueryWrapper<EBook> queryWrapper = new LambdaQueryWrapper<EBook>()
                .eq(isNotEmpty(condition.getCategory2Id()), EBook::getCategory2Id, condition.getCategory2Id())
                .like(isNotBlank(condition.getName()), EBook::getName, condition.getName());

        baseMapper.selectPage(pageEntity, queryWrapper);

        return CopyUtils.copyPage(pageEntity, EBookData.class);
    }

    @Override
    public Long deleteEBook(Long eBookId) {
        // 删除关联的内容
        LambdaQueryWrapper<Content> contentDelete = new LambdaQueryWrapper<Content>()
                .eq(Content::getEBookId, eBookId);

        contentMapper.delete(contentDelete);

        // 删除关联的文档
        LambdaQueryWrapper<Doc> docDelete = new LambdaQueryWrapper<Doc>()
                .eq(Doc::getEBookId, eBookId);

        docMapper.delete(docDelete);

        // 删除文档
        this.baseMapper.deleteById(eBookId);

        return eBookId;
    }
}