package junjie.fun.mywiki.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import junjie.fun.mywiki.common.response.PageData;
import junjie.fun.mywiki.common.response.ResponseVo;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.service.*;

/** 电子书快照表管理 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class EbookSnapshotController {

  private final EbookSnapshotService ebookSnapshotService;

  /** 分页查询EbookSnapshot */
  @PostMapping("/ebookSnapshot/pageEbookSnapshot")
  public ResponseVo<PageData<EbookSnapshotData>> pageEbookSnapshot(
      @Valid @RequestBody PageEbookSnapshotRequest request) {
    return ResponseVo.success(ebookSnapshotService.pageEbookSnapshot(request));
  }

  /** 创建EbookSnapshot */
  @PostMapping("/ebookSnapshot/createEbookSnapshot")
  public ResponseVo<Long> createEbookSnapshot(
      @Valid @RequestBody CreateEbookSnapshotRequest request) {
    return ResponseVo.success(ebookSnapshotService.createEbookSnapshot(request));
  }

  /** 更新EbookSnapshot */
  @PostMapping("/ebookSnapshot/updateEbookSnapshot")
  public ResponseVo<Long> updateEbookSnapshot(
      @Valid @RequestBody UpdateEbookSnapshotRequest request) {
    return ResponseVo.success(ebookSnapshotService.updateEbookSnapshot(request));
  }

  /**
   * 根据Id查询EbookSnapshot
   *
   * @param ebookSnapshotId EbookSnapshot的主键Id
   */
  @PostMapping("/ebookSnapshot/queryEbookSnapshot")
  public ResponseVo<EbookSnapshotData> queryEbookSnapshot(
      @RequestParam("ebookSnapshotId") Long ebookSnapshotId) {

    List<EbookSnapshotData> ebookSnapshotData =
        ebookSnapshotService.queryEbookSnapshots(Collections.singletonList(ebookSnapshotId));

    return ResponseVo.success(
        CollectionUtils.isNotEmpty(ebookSnapshotData) ? ebookSnapshotData.get(0) : null);
  }

  /**
   * 根据Id数组批量查询EbookSnapshot
   *
   * @param ebookSnapshotIds EbookSnapshot的主键Id数组
   */
  @PostMapping("/ebookSnapshot/queryEbookSnapshots")
  public ResponseVo<List<EbookSnapshotData>> queryEbookSnapshots(
      @RequestBody List<Long> ebookSnapshotIds) {

    return ResponseVo.success(ebookSnapshotService.queryEbookSnapshots(ebookSnapshotIds));
  }

  /**
   * 根据Id删除EbookSnapshot
   *
   * @param ebookSnapshotId EbookSnapshot的主键Id
   */
  @PostMapping("/ebookSnapshot/deleteEbookSnapshot")
  public ResponseVo<Long> deleteEbookSnapshot(
      @RequestParam("ebookSnapshotId") Long ebookSnapshotId) {
    ebookSnapshotService.deleteEbookSnapshots(Collections.singletonList(ebookSnapshotId));

    return ResponseVo.success();
  }

  /**
   * 根据Id数组批量删除EbookSnapshot
   *
   * @param ebookSnapshotIds EbookSnapshot的主键Id数组
   */
  @PostMapping("/ebookSnapshot/deleteEbookSnapshots")
  public ResponseVo<Void> deleteEbookSnapshots(@RequestBody List<Long> ebookSnapshotIds) {

    ebookSnapshotService.deleteEbookSnapshots(ebookSnapshotIds);

    return ResponseVo.success();
  }
}
