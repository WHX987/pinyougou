app.controller('typeTemplateController',function($scope,$controller,typeTemplateService,brandService,specificationService){
	
	// 继承，并注入$$controller
	$controller('baseController',{$scope:$scope})
	
	// 查询所有
	$scope.findAll=function(){
		typeTemplateService.findAll().success(
						function(response){
							$scope.list=response;
						}
					)
	}
	
	// 分页查询
	$scope.findPage=function(){
		typeTemplateService.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).success(
			function(response){
				$scope.list=response.rows;//当前页数据
				$scope.paginationConf.totalItems=response.total;//总记录数
			}
		);
	}
	
	//保存品牌数据
	$scope.save=function(){
		var method;
		if($scope.entity.id==null){
			//添加操作
			method=typeTemplateService.add($scope.entity);
		}else{
			//修改操作
			method=typeTemplateService.update($scope.entity);
		}
		
		method.success(
			function(response){
				if(response.success){
					$scope.findPage();//成功，重新查询数据
				}else{
					alert(response.message);
				}
			}
		);
	}
	//基于id查询品牌数据
	$scope.findOne=function(id){
		typeTemplateService.findOne(id).success(
			function(response){
				$scope.entity=response;
				
				//将品牌、规格和扩展数据返回的字符串数据转为json对象
				$scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
				
				$scope.entity.specIds = JSON.parse($scope.entity.specIds);
				
				$scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
			}
		);
	}
	
	// 删除操作
	$scope.dele = function(){
		if (confirm('确定要删除吗？')) {
			typeTemplateService.dele($scope.selectIds).success(
				function(response){
					if(response.success){
						$scope.findPage();
						$scope.selectIds=[]; // 清空所有id选择
					}
					alert(response.message);
				}		
			)
		}
	}
	
	
	// 条件查询
	$scope.searchEntity = {}  // 初始化searchEntity
	
	// 查询结果分页展示
	$scope.search = function(page,rows){
		typeTemplateService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows; //当前页数据
				$scope.paginationConf.totalItems = response.total; // 总记录数
				}		
			)
			
	}
	// 品牌下拉列表展示数据
	$scope.brandList={
		data:[]	
	};
	
	$scope.findBrandList = function(){
		brandService.selectOptionList().success(
			function(response){
				$scope.brandList.data=response;
			}
		)
	}
	
	//规格下拉列表展示数据
	$scope.specList={
	        data: []
	};
	
	$scope.findSpecList=function(){
		specificationService.selectOptionList().success(
			function(response){
				$scope.specList.data=response;
			}
		);
	}
	
	//初始化模板实体对象
	$scope.entity={customAttributeItems:[]};
	
	//添加扩展属性
	$scope.addRow=function(){
		$scope.entity.customAttributeItems.push({});
	}
	
	//删除扩展属性
	$scope.deleRow=function(index){
		$scope.entity.customAttributeItems.splice(index,1);
	}
		
});