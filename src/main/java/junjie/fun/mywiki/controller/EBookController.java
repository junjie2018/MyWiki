package junjie.fun.mywiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.request.ebook.CreateEBookRequest;
import junjie.fun.mywiki.request.ebook.PageEBookRequest;
import junjie.fun.mywiki.request.ebook.UpdateEBookRequest;
import junjie.fun.mywiki.response.PageData;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.response.data.EBookData;
import junjie.fun.mywiki.service.EBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * EBook管理
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class EBookController {

    private final EBookService eBookService;

    @PostMapping("/ebook/pageEBook")
    public ResponseVo<PageData<EBookData>> pageEBook(@Valid @RequestBody PageEBookRequest request) {
        return ResponseVo.success(eBookService.pageEBook(request));
    }

    @PostMapping("/ebook/createEBook")
    public ResponseVo<Long> createEBook(@Valid @RequestBody CreateEBookRequest request) {
        return ResponseVo.success(eBookService.createEBook(request));
    }

    @PostMapping("/ebook/updateEBook")
    public ResponseVo<Long> updateEBook(@Valid @RequestBody UpdateEBookRequest request) {
        return ResponseVo.success(eBookService.updateEBook(request));
    }


    @PostMapping("/ebook/deleteEBook")
    public ResponseVo<Long> tmp(@RequestParam("eBookId") Long eBookId) {
        eBookService.deleteEBook(eBookId);

        return ResponseVo.success(eBookId);
    }


}
