package com.gec.ocean.service;

import com.gec.ocean.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.ocean.req.CategoryQueryReq;
import com.gec.ocean.req.CategorySaveReq;
import com.gec.ocean.resp.CategoryQueryResp;
import com.gec.ocean.resp.PageResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cr
 * @since 2023-11-08
 */
public interface ICategoryService extends IService<Category> {

    PageResp<CategoryQueryResp> listByname(CategoryQueryReq req);

    void save(CategorySaveReq req);

    void delete(Long id);

    List<CategoryQueryResp> all();
}
