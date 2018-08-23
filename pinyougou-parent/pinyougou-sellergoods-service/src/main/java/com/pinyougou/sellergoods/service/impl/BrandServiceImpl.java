package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private TbBrandMapper brandMapper;
	
	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Page<TbBrand> pageData = (Page<TbBrand>) brandMapper.selectByExample(null);
		return new PageResult(pageData.getResult(), pageData.getTotal());
	}

	@Override
	public void add(TbBrand brand) {
		brandMapper.insert(brand);
	}

	@Override
	public TbBrand findOne(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbBrand brand) {
		brandMapper.updateByPrimaryKey(brand);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}		
	}

	@Override
	public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		
		if (brand != null) {
			String name = brand.getName();
			if (name != null && !"".equals(name)) {
				criteria.andNameLike("%"+name+"%");
			}
			
			String firstChar = brand.getFirstChar();
			if (firstChar != null && !"".equals(firstChar)) {
				criteria.andFirstCharEqualTo(firstChar);
			}
		}
		Page<TbBrand> pageData = (Page<TbBrand>) brandMapper.selectByExample(example);
		return new PageResult(pageData.getResult(), pageData.getTotal());
	}

	@Override
	public List<Map> selectOptionList() {
		return brandMapper.selectOptionList();
	}

}
