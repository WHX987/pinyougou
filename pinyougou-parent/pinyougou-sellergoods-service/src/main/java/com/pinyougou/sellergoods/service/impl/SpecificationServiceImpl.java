package com.pinyougou.sellergoods.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.grouppojo.Specification;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;


@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
	
	@Autowired
	private TbSpecificationMapper specificationMapper;
	
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	@Override
	public PageResult findPage(TbSpecification specification, Integer pageNum, Integer pageSize) {
		// 设置分页查询条件
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example = new TbSpecificationExample();
		
		// 条件封装对象
		Criteria criteria = example.createCriteria();
		
		if (specification != null) {
			String specName = specification.getSpecName();
			if (specName != null && !"".equals(specName)) {
				criteria.andSpecNameLike("%"+specName+"%");
			}
		}
		
		Page<TbSpecification> pageData = (Page<TbSpecification>) specificationMapper.selectByExample(example);
		return new PageResult(pageData.getResult(), pageData.getTotal());
	}

	@Override
	public void add(Specification specification) {
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.insert(tbSpecification);	
		
		// 保存规格选项
		List<TbSpecificationOption> specificationOptions = specification.getSpecificationOptions();
		for (TbSpecificationOption tbSpecificationOption : specificationOptions) {
			// 关联规格选项的Id
			tbSpecificationOption.setSpecId(tbSpecification.getId());
			specificationOptionMapper.insert(tbSpecificationOption);
		}
	}

	@Override
	public List<Map> selectOptionList() {
		return specificationMapper.selectOptionList();
	}

	

}
