package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import junjie.fun.mywiki.common.response.PageData;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;

@Service
public interface ContentService extends IService<Content> {

  /** 创建文档内容 */
  Long createContent(CreateContentRequest request);

  /** 删除文档内容 */
  void deleteContents(List<Long> contentIds);

  /** 编辑文档内容 */
  Long updateContent(UpdateContentRequest request);

  /** 分页查找文档内容 */
  PageData<ContentData> pageContent(PageContentRequest request);

  /** 根据Id数组查找文档内容 */
  List<ContentData> queryContents(List<Long> contentIds);
}
