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

/** 电子书管理 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class EbookController {

  private final EbookService ebookService;

  /** 分页查询Ebook */
  @PostMapping("/ebook/pageEbook")
  public ResponseVo<PageData<EbookData>> pageEbook(@Valid @RequestBody PageEbookRequest request) {
    return ResponseVo.success(ebookService.pageEbook(request));
  }

  /** 创建Ebook */
  @PostMapping("/ebook/createEbook")
  public ResponseVo<Long> createEbook(@Valid @RequestBody CreateEbookRequest request) {
    return ResponseVo.success(ebookService.createEbook(request));
  }

  /** 更新Ebook */
  @PostMapping("/ebook/updateEbook")
  public ResponseVo<Long> updateEbook(@Valid @RequestBody UpdateEbookRequest request) {
    return ResponseVo.success(ebookService.updateEbook(request));
  }

  /**
   * 根据Id查询Ebook
   *
   * @param ebookId Ebook的主键Id
   */
  @PostMapping("/ebook/queryEbook")
  public ResponseVo<EbookData> queryEbook(@RequestParam("ebookId") Long ebookId) {

    List<EbookData> ebookData = ebookService.queryEbooks(Collections.singletonList(ebookId));

    return ResponseVo.success(CollectionUtils.isNotEmpty(ebookData) ? ebookData.get(0) : null);
  }

  /**
   * 根据Id数组批量查询Ebook
   *
   * @param ebookIds Ebook的主键Id数组
   */
  @PostMapping("/ebook/queryEbooks")
  public ResponseVo<List<EbookData>> queryEbooks(@RequestBody List<Long> ebookIds) {

    return ResponseVo.success(ebookService.queryEbooks(ebookIds));
  }

  /**
   * 根据Id删除Ebook
   *
   * @param ebookId Ebook的主键Id
   */
  @PostMapping("/ebook/deleteEbook")
  public ResponseVo<Long> deleteEbook(@RequestParam("ebookId") Long ebookId) {
    ebookService.deleteEbooks(Collections.singletonList(ebookId));

    return ResponseVo.success();
  }

  /**
   * 根据Id数组批量删除Ebook
   *
   * @param ebookIds Ebook的主键Id数组
   */
  @PostMapping("/ebook/deleteEbooks")
  public ResponseVo<Void> deleteEbooks(@RequestBody List<Long> ebookIds) {

    ebookService.deleteEbooks(ebookIds);

    return ResponseVo.success();
  }
}
