app.controller('brandController',function($scope,$controller,brandService){
	
	// 继承，并注入$$controller
	$controller('baseController',{$scope:$scope})
	
	// 查询所有
	$scope.findAll=function(){
		brandService.findAll().success(
						function(response){
							$scope.list=response;
						}
					)
	}
	
	// 分页查询
	$scope.findPage=function(){
		brandService.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).success(
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
			method=brandService.add($scope.entity);
		}else{
			//修改操作
			method=brandService.update($scope.entity);
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
		brandService.findOne(id).success(
			function(response){
				$scope.entity=response;
			}
		);
	}
	
	// 删除操作
	$scope.dele = function(){
		if (confirm('确定要删除吗？')) {
			brandService.dele($scope.selectIds).success(
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
		brandService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows; //当前页数据
				$scope.paginationConf.totalItems = response.total; // 总记录数
				}		
			)
			
	}
		
});