// 品牌服务层
app.service('specificationService',function($http){
	
	// 条件分页查询
	this.search = function(page,rows,searchEntity){
		return $http.post("../specification/search.do?pageNum="+page+"&pageSize="+rows,searchEntity);
	}
	
	// 保存
	this.add = function(entity){
		return $http.post("../specification/add.do",entity);
	}
	
	// 规格查询
	this.selectOptionList = function(){
		return $http.get('../specification/selectOptionList.do');
	}
})