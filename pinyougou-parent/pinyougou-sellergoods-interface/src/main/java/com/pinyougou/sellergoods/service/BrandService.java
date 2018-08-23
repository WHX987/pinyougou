package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

public interface BrandService {
	
	/**
	 * 查询所有品牌
	 */
	List<TbBrand> findAll();

	PageResult findPage(Integer pageNum, Integer pageSize);

	void add(TbBrand brand);

	TbBrand findOne(Long id);

	void update(TbBrand brand);

	void delete(Long[] ids);

	PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize);

	List<Map> selectOptionList();
}
