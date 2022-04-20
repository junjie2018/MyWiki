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

/** 文档管理 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DocController {

  private final DocService docService;

  /** 分页查询Doc */
  @PostMapping("/doc/pageDoc")
  public ResponseVo<PageData<DocData>> pageDoc(@Valid @RequestBody PageDocRequest request) {
    return ResponseVo.success(docService.pageDoc(request));
  }

  /** 创建Doc */
  @PostMapping("/doc/createDoc")
  public ResponseVo<Long> createDoc(@Valid @RequestBody CreateDocRequest request) {
    return ResponseVo.success(docService.createDoc(request));
  }

  /** 更新Doc */
  @PostMapping("/doc/updateDoc")
  public ResponseVo<Long> updateDoc(@Valid @RequestBody UpdateDocRequest request) {
    return ResponseVo.success(docService.updateDoc(request));
  }

  /**
   * 根据Id查询Doc
   *
   * @param docId Doc的主键Id
   */
  @PostMapping("/doc/queryDoc")
  public ResponseVo<DocData> queryDoc(@RequestParam("docId") Long docId) {

    List<DocData> docData = docService.queryDocs(Collections.singletonList(docId));

    return ResponseVo.success(CollectionUtils.isNotEmpty(docData) ? docData.get(0) : null);
  }

  /**
   * 根据Id数组批量查询Doc
   *
   * @param docIds Doc的主键Id数组
   */
  @PostMapping("/doc/queryDocs")
  public ResponseVo<List<DocData>> queryDocs(@RequestBody List<Long> docIds) {

    return ResponseVo.success(docService.queryDocs(docIds));
  }

  /**
   * 根据Id删除Doc
   *
   * @param docId Doc的主键Id
   */
  @PostMapping("/doc/deleteDoc")
  public ResponseVo<Long> deleteDoc(@RequestParam("docId") Long docId) {
    docService.deleteDocs(Collections.singletonList(docId));

    return ResponseVo.success();
  }

  /**
   * 根据Id数组批量删除Doc
   *
   * @param docIds Doc的主键Id数组
   */
  @PostMapping("/doc/deleteDocs")
  public ResponseVo<Void> deleteDocs(@RequestBody List<Long> docIds) {

    docService.deleteDocs(docIds);

    return ResponseVo.success();
  }
}
