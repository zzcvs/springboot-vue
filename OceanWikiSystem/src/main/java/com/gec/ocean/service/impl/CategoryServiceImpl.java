package com.gec.ocean.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.ocean.entity.Category;
import com.gec.ocean.mapper.CategoryMapper;
import com.gec.ocean.req.CategoryQueryReq;
import com.gec.ocean.req.CategorySaveReq;
import com.gec.ocean.resp.CategoryQueryResp;
import com.gec.ocean.resp.PageResp;
import com.gec.ocean.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.ocean.utils.CopyUtil;
import com.gec.ocean.utils.SnowFlake;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author cr
 * @since 2023-11-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public PageResp<CategoryQueryResp> listByname(CategoryQueryReq req) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
        //第一个参数：该参数是一个布尔类型，只有该参数是true时，才将like条件拼接到sql中；本例中，如果name字段不为空，则拼接name字段的like查询条件；
        queryWrapper.like(StringUtils.isNotBlank(req.getName()),"name",req.getName());
        //创建分页对象
        Page<Category> page = new Page<>(req.getPage(),req.getSize());
        page = this.baseMapper.selectPage(page, queryWrapper);

        List<CategoryQueryResp> resps = CopyUtil.copyList(page.getRecords(), CategoryQueryResp.class);
        //创建返回对象
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(page.getTotal());
        return pageResp;
    }

    @Override
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            long id = snowFlake.nextId();
            category.setId(id);
            this.baseMapper.insert(category);
        } else {
            // 更新
            this.baseMapper.updateById(category);
        }
    }

    @Override
    public void delete(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<CategoryQueryResp> all() {
        //使用 sort 列进行排序
        List<Category> categories = this.baseMapper.selectList(new QueryWrapper<Category>().orderByAsc("sort"));
        List<CategoryQueryResp> list = CopyUtil.copyList(categories, CategoryQueryResp.class);
        return list;
    }
}
