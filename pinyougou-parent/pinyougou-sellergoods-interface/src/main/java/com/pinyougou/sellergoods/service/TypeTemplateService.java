package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbTypeTemplate;

import entity.PageResult;

public interface TypeTemplateService {
	
	/**
	 * 查询所有品牌
	 */
	List<TbTypeTemplate> findAll();

	PageResult findPage(Integer pageNum, Integer pageSize);

	void add(TbTypeTemplate typeTemplate);

	TbTypeTemplate findOne(Long id);

	void update(TbTypeTemplate typeTemplate);

	void delete(Long[] ids);

	PageResult findPage(TbTypeTemplate typeTemplate, Integer pageNum, Integer pageSize);
	
	List<Map> findSpecList(Long id);
}
