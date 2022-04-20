package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import junjie.fun.mywiki.entity.EBook;
import junjie.fun.mywiki.request.ebook.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.response.data.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EBookService extends IService<EBook> {

    Long createEBook(CreateEBookRequest request);

    void deleteEBooks(List<Long> eBookIds);

    Long updateEBook(UpdateEBookRequest request);

    PageData<EBookData> pageEBook(PageEBookRequest request);

    List<EBookData> queryEBooks(List<Long> ebookIds);

}
