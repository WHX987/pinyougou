package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.grouppojo.Specification;
import com.pinyougou.pojo.TbSpecification;

import entity.PageResult;

public interface SpecificationService {

	PageResult findPage(TbSpecification specification, Integer pageNum, Integer pageSize);

	void add(Specification specification);

	List<Map> selectOptionList();

}
