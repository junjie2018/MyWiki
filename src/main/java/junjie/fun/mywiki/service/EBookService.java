package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import junjie.fun.mywiki.entity.EBook;
import junjie.fun.mywiki.request.ebook.CreateEBookRequest;
import junjie.fun.mywiki.request.ebook.PageEBookRequest;
import junjie.fun.mywiki.request.ebook.UpdateEBookRequest;
import junjie.fun.mywiki.response.PageData;
import junjie.fun.mywiki.response.data.EBookData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EBookService extends IService<EBook> {

    Long createEBook(CreateEBookRequest request);

    Long updateEBook(UpdateEBookRequest request);

    PageData<EBookData> pageEBook(PageEBookRequest request);

    List<EBookData> queryEBooks(List<Long> ebookIds);

    void deleteEBooks(List<Long> eBookIds);
}
