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

/** 文档内容管理 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ContentController {

  private final ContentService contentService;

  /** 分页查询Content */
  @PostMapping("/content/pageContent")
  public ResponseVo<PageData<ContentData>> pageContent(
      @Valid @RequestBody PageContentRequest request) {
    return ResponseVo.success(contentService.pageContent(request));
  }

  /** 创建Content */
  @PostMapping("/content/createContent")
  public ResponseVo<Long> createContent(@Valid @RequestBody CreateContentRequest request) {
    return ResponseVo.success(contentService.createContent(request));
  }

  /** 更新Content */
  @PostMapping("/content/updateContent")
  public ResponseVo<Long> updateContent(@Valid @RequestBody UpdateContentRequest request) {
    return ResponseVo.success(contentService.updateContent(request));
  }

  /**
   * 根据Id查询Content
   *
   * @param contentId Content的主键Id
   */
  @PostMapping("/content/queryContent")
  public ResponseVo<ContentData> queryContent(@RequestParam("contentId") Long contentId) {

    List<ContentData> contentData =
        contentService.queryContents(Collections.singletonList(contentId));

    return ResponseVo.success(CollectionUtils.isNotEmpty(contentData) ? contentData.get(0) : null);
  }

  /**
   * 根据Id数组批量查询Content
   *
   * @param contentIds Content的主键Id数组
   */
  @PostMapping("/content/queryContents")
  public ResponseVo<List<ContentData>> queryContents(@RequestBody List<Long> contentIds) {

    return ResponseVo.success(contentService.queryContents(contentIds));
  }

  /**
   * 根据Id删除Content
   *
   * @param contentId Content的主键Id
   */
  @PostMapping("/content/deleteContent")
  public ResponseVo<Long> deleteContent(@RequestParam("contentId") Long contentId) {
    contentService.deleteContents(Collections.singletonList(contentId));

    return ResponseVo.success();
  }

  /**
   * 根据Id数组批量删除Content
   *
   * @param contentIds Content的主键Id数组
   */
  @PostMapping("/content/deleteContents")
  public ResponseVo<Void> deleteContents(@RequestBody List<Long> contentIds) {

    contentService.deleteContents(contentIds);

    return ResponseVo.success();
  }
}
