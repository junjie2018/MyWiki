package junjie.fun.mywiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import junjie.fun.mywiki.entity.EBook;
import junjie.fun.mywiki.mapper.ContentMapper;
import junjie.fun.mywiki.mapper.DocMapper;
import junjie.fun.mywiki.mapper.EBookMapper;

import junjie.fun.mywiki.request.ebook.CreateEBookRequest;
import junjie.fun.mywiki.request.ebook.PageEBookRequest;
import junjie.fun.mywiki.request.ebook.UpdateEBookRequest;
import junjie.fun.mywiki.response.PageData;
import junjie.fun.mywiki.response.data.EBookData;
import junjie.fun.mywiki.service.EBookService;
import junjie.fun.mywiki.utils.CopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.*;
import static org.apache.commons.lang3.StringUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EBookServiceImpl extends ServiceImpl<EBookMapper, EBook> implements EBookService {

    private final ContentMapper contentMapper;
    private final DocMapper docMapper;

    public Long createEBook(CreateEBookRequest request) {
        EBook eBookInert = EBook.builder()
                .name(request.getName())
                .category1Id(request.getCategory1Id())
                .category2Id(request.getCategory2Id())
                .description(request.getDescription())
                .cover(request.getCover())
                .docCount(0)
                .viewCount(0)
                .voteCount(0)
                .build();

        this.baseMapper.insert(eBookInert);

        return eBookInert.getId();
    }

    public Long updateEBook(UpdateEBookRequest request) {
        LambdaUpdateWrapper<EBook> updateWrapper = new LambdaUpdateWrapper<EBook>()
                .eq(EBook::getId, request.getId())
                .set(EBook::getName, request.getName())
                .set(EBook::getCategory1Id, request.getCategory1Id())
                .set(EBook::getCategory2Id, request.getCategory2Id())
                .set(EBook::getDescription, request.getDescription())
                .set(EBook::getCover, request.getCover());

        this.baseMapper.update(null, updateWrapper);

        return request.getId();
    }

    public PageData<EBookData> pageEBook(PageEBookRequest request) {

        Page<EBook> pageEntity = request.getPage(EBook.class);
        PageEBookRequest.Condition condition = request.getCondition();

        LambdaQueryWrapper<EBook> queryWrapper = new LambdaQueryWrapper<EBook>()
                .eq(isNotEmpty(condition.getCategory2Id()), EBook::getCategory2Id, condition.getCategory2Id())
                .like(isNotBlank(condition.getName()), EBook::getName, condition.getName())
                .orderByDesc(EBook::getId);

        baseMapper.selectPage(pageEntity, queryWrapper);

        return CopyUtils.copyPageData(pageEntity, EBookData.class);
    }

    @Override
    public List<EBookData> queryEBooks(List<Long> ebookIds) {

        LambdaQueryWrapper<EBook> queryWrapper = new LambdaQueryWrapper<EBook>()
                .in(EBook::getId, ebookIds);

        return CopyUtils.copyList(baseMapper.selectList(queryWrapper), EBookData.class);
    }

    @Override
    public void deleteEBooks(List<Long> eBookIds) {
        // todo 这块有Bug，明天修理一下
//        // 删除关联的内容
//        LambdaQueryWrapper<Content> contentDelete = new LambdaQueryWrapper<Content>()
//                .eq(Content::getEBookId, eBookId);
//
//        contentMapper.delete(contentDelete);
//
//        // 删除关联的文档
//        LambdaQueryWrapper<Doc> docDelete = new LambdaQueryWrapper<Doc>()
//                .eq(Doc::getEBookId, eBookId);
//
//        docMapper.delete(docDelete);

        // 删除文档
        this.baseMapper.deleteBatchIds(eBookIds);
    }
}