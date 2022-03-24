package junjie.fun.mywiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageDocCondition;
import junjie.fun.mywiki.request.doc.CreateOrUpdateDocRequest;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.response.data.DocData;
import junjie.fun.mywiki.service.DocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 文档管理
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DocController {

    private final DocService docService;

    @PostMapping("/doc/pageDoc")
    public ResponseVo<Page<DocData>> pageDoc(@Valid @RequestBody PageRequest<PageDocCondition> request) {
        return ResponseVo.success(docService.pageDoc(request));
    }

    @PostMapping("/doc/createOrUpdateDoc")
    public ResponseVo<Long> createOrUpdateDoc(@Valid @RequestBody CreateOrUpdateDocRequest request) {
        return ResponseVo.success(docService.createOrUpdateDoc(request));
    }

    @PostMapping("/doc/deleteDocs")
    public ResponseVo<Void> deleteDocs(@RequestBody List<Long> docIds) {

        docService.deleteDocs(docIds);

        return ResponseVo.success();
    }

    @PostMapping("/doc/vote")
    public ResponseVo<Void> vote(@RequestParam("docId") Long docId) {
        docService.vote(docId);

        return ResponseVo.success();
    }

    @PostMapping("/doc/getContent")
    public ResponseVo<String> getContent(@RequestParam("docId") Long docId) {
        return ResponseVo.success(docService.getContent(docId));
    }
}
