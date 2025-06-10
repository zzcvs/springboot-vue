package com.gec.ocean.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.ocean.entity.Ebook;
import com.gec.ocean.mapper.EbookMapper;
import com.gec.ocean.req.EbookQueryReq;
import com.gec.ocean.req.EbookSaveReq;
import com.gec.ocean.resp.EbookQueryResp;
import com.gec.ocean.resp.PageResp;
import com.gec.ocean.service.IEbookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.ocean.utils.CopyUtil;
import com.gec.ocean.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 电子书 服务实现类
 * </p>
 *
 * @author zhuzec
 * @since 2025-05-22
 */
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements IEbookService {


    @Override
    public PageResp<EbookQueryResp> listByname(EbookQueryReq req) {
        QueryWrapper<Ebook> queryWrapper = new QueryWrapper<Ebook>();
        //第一个参数：该参数是一个布尔类型，只有该参数是true时，才将like条件拼接到sql中；本例中，如果name字段不为空，则拼接name字段的like查询条件；
        queryWrapper.like(StringUtils.isNotBlank(req.getName()),"name",req.getName());
        queryWrapper.eq(ObjectUtils.isNotEmpty(req.getCategoryId2()),"category2_id",req.getCategoryId2());
        //创建分页对象
        Page<Ebook> page = new Page<>(req.getPage(),req.getSize());
        page = this.baseMapper.selectPage(page, queryWrapper);


        List<EbookQueryResp> resps = CopyUtil.copyList(page.getRecords(), EbookQueryResp.class);
        //创建返回对象
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(page.getTotal());
        return pageResp;
    }

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 雪花算法生成id
            long id = snowFlake.nextId();
            ebook.setId(id);
            this.baseMapper.insert(ebook);
        } else {
            // 更新
            this.baseMapper.updateById(ebook);
        }
    }
    @Override
    public void delete(Long id) {
        this.baseMapper.deleteById(id);
    }
}



