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
public class EbookSnapshotServiceImpl extends ServiceImpl<EbookSnapshotMapper, EbookSnapshot>
    implements EbookSnapshotService {

  @Override
  public Long createEbookSnapshot(CreateEbookSnapshotRequest request) {
    EbookSnapshot eBookInert =
        EbookSnapshot.builder()
            .ebookId(request.getEbookId())
            .date(request.getDate())
            .viewCount(request.getViewCount())
            .voteCount(request.getVoteCount())
            .viewIncrease(request.getViewIncrease())
            .voteIncrease(request.getVoteIncrease())
            .build();

    this.baseMapper.insert(eBookInert);

    return eBookInert.getId();
  }

  @Override
  public void deleteEbookSnapshots(List<Long> ebookSnapshotIds) {
    this.baseMapper.deleteBatchIds(ebookSnapshotIds);
  }

  @Override
  public Long updateEbookSnapshot(UpdateEbookSnapshotRequest request) {
    LambdaUpdateWrapper<EbookSnapshot> updateWrapper =
        new LambdaUpdateWrapper<EbookSnapshot>()
            .eq(EbookSnapshot::getId, request.getId())
            .set(EbookSnapshot::getEbookId, request.getEbookId())
            .set(EbookSnapshot::getDate, request.getDate())
            .set(EbookSnapshot::getViewCount, request.getViewCount())
            .set(EbookSnapshot::getVoteCount, request.getVoteCount())
            .set(EbookSnapshot::getViewIncrease, request.getViewIncrease())
            .set(EbookSnapshot::getVoteIncrease, request.getVoteIncrease());

    this.baseMapper.update(null, updateWrapper);

    return request.getId();
  }

  @Override
  public PageData<EbookSnapshotData> pageEbookSnapshot(PageEbookSnapshotRequest request) {

    Page<EbookSnapshot> pageEntity = request.getPage(EbookSnapshot.class);
    PageEbookSnapshotRequest.Condition condition = request.getCondition();

    LambdaQueryWrapper<EbookSnapshot> queryWrapper =
        new LambdaQueryWrapper<EbookSnapshot>().orderByDesc(EbookSnapshot::getCreateTime);

    baseMapper.selectPage(pageEntity, queryWrapper);

    return CopyUtils.copyPageData(pageEntity, EbookSnapshotData.class);
  }

  @Override
  public List<EbookSnapshotData> queryEbookSnapshots(List<Long> ebookSnapshotIds) {

    LambdaQueryWrapper<EbookSnapshot> queryWrapper =
        new LambdaQueryWrapper<EbookSnapshot>().in(EbookSnapshot::getId, ebookSnapshotIds);

    return CopyUtils.copyList(baseMapper.selectList(queryWrapper), EbookSnapshotData.class);
  }
}
