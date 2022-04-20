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
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

  @Override
  public Long createDoc(CreateDocRequest request) {
    Doc eBookInert =
        Doc.builder()
            .ebookId(request.getEbookId())
            .parent(request.getParent())
            .name(request.getName())
            .sort(request.getSort())
            .viewCount(request.getViewCount())
            .voteCount(request.getVoteCount())
            .build();

    this.baseMapper.insert(eBookInert);

    return eBookInert.getId();
  }

  @Override
  public void deleteDocs(List<Long> docIds) {
    this.baseMapper.deleteBatchIds(docIds);
  }

  @Override
  public Long updateDoc(UpdateDocRequest request) {
    LambdaUpdateWrapper<Doc> updateWrapper =
        new LambdaUpdateWrapper<Doc>()
            .eq(Doc::getId, request.getId())
            .set(Doc::getEbookId, request.getEbookId())
            .set(Doc::getParent, request.getParent())
            .set(Doc::getName, request.getName())
            .set(Doc::getSort, request.getSort())
            .set(Doc::getViewCount, request.getViewCount())
            .set(Doc::getVoteCount, request.getVoteCount());

    this.baseMapper.update(null, updateWrapper);

    return request.getId();
  }

  @Override
  public PageData<DocData> pageDoc(PageDocRequest request) {

    Page<Doc> pageEntity = request.getPage(Doc.class);
    PageDocRequest.Condition condition = request.getCondition();

    LambdaQueryWrapper<Doc> queryWrapper =
        new LambdaQueryWrapper<Doc>().orderByDesc(Doc::getCreateTime);

    baseMapper.selectPage(pageEntity, queryWrapper);

    return CopyUtils.copyPageData(pageEntity, DocData.class);
  }

  @Override
  public List<DocData> queryDocs(List<Long> docIds) {

    LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<Doc>().in(Doc::getId, docIds);

    return CopyUtils.copyList(baseMapper.selectList(queryWrapper), DocData.class);
  }
}
