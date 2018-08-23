package com.pinyougou.grouppojo;

import java.io.Serializable;
import java.util.List;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;

public class Specification implements Serializable{
	
	private TbSpecification specification;
	
	private List<TbSpecificationOption> specificationOptions;

	public TbSpecification getSpecification() {
		return specification;
	}

	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}

	public List<TbSpecificationOption> getSpecificationOptions() {
		return specificationOptions;
	}

	public void setSpecificationOptions(List<TbSpecificationOption> specificationOptions) {
		this.specificationOptions = specificationOptions;
	}
	
	
}
