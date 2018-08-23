package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.pojo.TbTypeTemplateExample.Criteria;
import com.pinyougou.sellergoods.service.TypeTemplateService;

import entity.PageResult;

@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
	
	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;
	
	@Autowired 
	private TbSpecificationOptionMapper specificationOptionMapper; 
	
	@Override
	public List<TbTypeTemplate> findAll() {
		return typeTemplateMapper.selectByExample(null);
	}
	

	@Override
	public PageResult findPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Page<TbTypeTemplate> pageData = (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(null);
		return new PageResult(pageData.getResult(), pageData.getTotal());
	}

	@Override
	public void add(TbTypeTemplate typeTemplate) {
		typeTemplateMapper.insert(typeTemplate);
	}

	@Override
	public TbTypeTemplate findOne(Long id) {
		return typeTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbTypeTemplate typeTemplate) {
		typeTemplateMapper.updateByPrimaryKey(typeTemplate);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			typeTemplateMapper.deleteByPrimaryKey(id);
		}		
	}

	@Override
	public PageResult findPage(TbTypeTemplate typeTemplate, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbTypeTemplateExample example = new TbTypeTemplateExample();
		Criteria criteria = example.createCriteria();
		
		if (typeTemplate != null) {
			String name = typeTemplate.getName();
			if (name != null && !"".equals(name)) {
				criteria.andNameLike("%"+name+"%");
			}
			
		}
		Page<TbTypeTemplate> pageData = (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(example);
		return new PageResult(pageData.getResult(), pageData.getTotal());
	}

	@Override
	public List<Map> findSpecList(Long id) {
		// 查询模板
		TbTypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
		List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
		for (Map map : list) {
			// 查询规格选项列表
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));
			List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
			map.put("options", options);
		}
		return list;
	}
	

}
