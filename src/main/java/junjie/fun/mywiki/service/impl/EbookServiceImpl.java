package junjie.fun.mywiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import junjie.fun.mywiki.common.response.PageData;
import junjie.fun.mywiki.utils.CopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;
import junjie.fun.mywiki.service.*;
import junjie.fun.mywiki.mapper.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements EbookService {

  @Override
  public Long createEbook(CreateEbookRequest request) {
    Ebook eBookInert =
        Ebook.builder()
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

  @Override
  public void deleteEbooks(List<Long> ebookIds) {
    this.baseMapper.deleteBatchIds(ebookIds);
  }

  @Override
  public Long updateEbook(UpdateEbookRequest request) {
    LambdaUpdateWrapper<Ebook> updateWrapper =
        new LambdaUpdateWrapper<Ebook>()
            .eq(Ebook::getId, request.getId())
            .set(Ebook::getName, request.getName())
            .set(Ebook::getCategory1Id, request.getCategory1Id())
            .set(Ebook::getCategory2Id, request.getCategory2Id())
            .set(Ebook::getDescription, request.getDescription())
            .set(Ebook::getCover, request.getCover())
            .set(Ebook::getDocCount, request.getDocCount())
            .set(Ebook::getViewCount, request.getViewCount())
            .set(Ebook::getVoteCount, request.getVoteCount());

    this.baseMapper.update(null, updateWrapper);

    return request.getId();
  }

  @Override
  public PageData<EbookData> pageEbook(PageEbookRequest request) {

    Page<Ebook> pageEntity = request.getPage(Ebook.class);
    PageEbookRequest.Condition condition = request.getCondition();

    LambdaQueryWrapper<Ebook> queryWrapper =
        new LambdaQueryWrapper<Ebook>().orderByDesc(Ebook::getCreateTime);

    baseMapper.selectPage(pageEntity, queryWrapper);

    return CopyUtils.copyPageData(pageEntity, EbookData.class);
  }

  @Override
  public List<EbookData> queryEbooks(List<Long> ebookIds) {

    LambdaQueryWrapper<Ebook> queryWrapper =
        new LambdaQueryWrapper<Ebook>().in(Ebook::getId, ebookIds);

    return CopyUtils.copyList(baseMapper.selectList(queryWrapper), EbookData.class);
  }
}
