package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import junjie.fun.mywiki.entity.EBook;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageEBookCondition;
import junjie.fun.mywiki.request.ebook.CreateOrUpdateEBookRequest;
import junjie.fun.mywiki.response.data.EBookData;
import org.springframework.stereotype.Service;

@Service
public interface EBookService extends IService<EBook> {

    Long createOrUpdate(CreateOrUpdateEBookRequest request);

    Page<EBookData> pageEBook(PageRequest<PageEBookCondition> request);

    Long deleteEBook(Long eBookId);
}
