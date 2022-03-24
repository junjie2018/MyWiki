package junjie.fun.mywiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageEBookCondition;
import junjie.fun.mywiki.request.ebook.CreateOrUpdateEBookRequest;
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
    public ResponseVo<Page<EBookData>> pageEBook(@Valid @RequestBody PageRequest<PageEBookCondition> request) {
        return ResponseVo.success(eBookService.pageEBook(request));
    }

    @PostMapping("/ebook/createOrUpdateEBook")
    public ResponseVo<Long> createOrUpdateEBook(@Valid @RequestBody CreateOrUpdateEBookRequest request) {
        return ResponseVo.success(eBookService.createOrUpdate(request));
    }

    @PostMapping("/ebook/deleteEBook")
    public ResponseVo<Long> tmp(@RequestParam("eBookId") Long eBookId) {
        eBookService.removeById(eBookId);

        return ResponseVo.success(eBookId);
    }


}
