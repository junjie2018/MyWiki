package junjie.fun.mywiki.controller;

import junjie.fun.mywiki.request.ebook.CreateEBookRequest;
import junjie.fun.mywiki.request.ebook.PageEBookRequest;
import junjie.fun.mywiki.request.ebook.UpdateEBookRequest;
import junjie.fun.mywiki.response.PageData;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.response.data.EBookData;
import junjie.fun.mywiki.service.EBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * EBook管理
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class EBookController {

    private final EBookService eBookService;

    /**
     * 分页查询EBook
     */
    @PostMapping("/ebook/pageEBook")
    public ResponseVo<PageData<EBookData>> pageEBook(@Valid @RequestBody PageEBookRequest request) {
        return ResponseVo.success(eBookService.pageEBook(request));
    }

    /**
     * 创建EBook
     */
    @PostMapping("/ebook/createEBook")
    public ResponseVo<Long> createEBook(@Valid @RequestBody CreateEBookRequest request) {
        return ResponseVo.success(eBookService.createEBook(request));
    }

    /**
     * 更新EBook
     */
    @PostMapping("/ebook/updateEBook")
    public ResponseVo<Long> updateEBook(@Valid @RequestBody UpdateEBookRequest request) {
        return ResponseVo.success(eBookService.updateEBook(request));
    }

    /**
     * 根据Id查询EBook
     *
     * @param eBookId EBook的主键Id
     */
    @PostMapping("/ebook/queryEBook")
    public ResponseVo<EBookData> queryEBook(@RequestParam("eBookId") Long eBookId) {

        List<EBookData> eBookData = eBookService.queryEBooks(Collections.singletonList(eBookId));

        return ResponseVo.success(CollectionUtils.isNotEmpty(eBookData) ? eBookData.get(0) : null);
    }

    /**
     * 根据Id数组批量查询EBook
     *
     * @param eBookIds EBook的主键Id数组
     */
    @PostMapping("/ebook/queryEBooks")
    public ResponseVo<List<EBookData>> queryEBooks(@RequestBody List<Long> eBookIds) {

        return ResponseVo.success(eBookService.queryEBooks(eBookIds));

    }

    /**
     * 根据Id删除EBook
     *
     * @param eBookId EBook的主键Id
     */
    @PostMapping("/ebook/deleteEBook")
    public ResponseVo<Long> deleteEBook(@RequestParam("eBookId") Long eBookId) {
        eBookService.deleteEBooks(Collections.singletonList(eBookId));

        return ResponseVo.success();
    }

    /**
     * 根据Id数组批量删除EBook
     *
     * @param eBookIds EBook的主键Id数组
     */
    @PostMapping("/ebook/deleteEBooks")
    public ResponseVo<Void> deleteEBooks(@RequestBody List<Long> eBookIds) {

        eBookService.deleteEBooks(eBookIds);

        return ResponseVo.success();
    }
}
