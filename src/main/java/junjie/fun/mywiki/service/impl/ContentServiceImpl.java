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
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements ContentService {

  @Override
  public Long createContent(CreateContentRequest request) {
    Content eBookInert = Content.builder().content(request.getContent()).build();

    this.baseMapper.insert(eBookInert);

    return eBookInert.getId();
  }

  @Override
  public void deleteContents(List<Long> contentIds) {
    this.baseMapper.deleteBatchIds(contentIds);
  }

  @Override
  public Long updateContent(UpdateContentRequest request) {
    LambdaUpdateWrapper<Content> updateWrapper =
        new LambdaUpdateWrapper<Content>()
            .eq(Content::getId, request.getId())
            .set(Content::getContent, request.getContent());

    this.baseMapper.update(null, updateWrapper);

    return request.getId();
  }

  @Override
  public PageData<ContentData> pageContent(PageContentRequest request) {

    Page<Content> pageEntity = request.getPage(Content.class);
    PageContentRequest.Condition condition = request.getCondition();

    LambdaQueryWrapper<Content> queryWrapper =
        new LambdaQueryWrapper<Content>().orderByDesc(Content::getCreateTime);

    baseMapper.selectPage(pageEntity, queryWrapper);

    return CopyUtils.copyPageData(pageEntity, ContentData.class);
  }

  @Override
  public List<ContentData> queryContents(List<Long> contentIds) {

    LambdaQueryWrapper<Content> queryWrapper =
        new LambdaQueryWrapper<Content>().in(Content::getId, contentIds);

    return CopyUtils.copyList(baseMapper.selectList(queryWrapper), ContentData.class);
  }
}
