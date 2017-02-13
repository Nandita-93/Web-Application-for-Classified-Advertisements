(function () {

    angular
        .module('app')
        .controller('DashboardController', DashboardController)
		.controller('ModalInstanceCtrl', ModalInstanceCtrl);
		
		DashboardController.$inject = ['CartService','PostService','FlashService','$rootScope','$scope','$modal','$filter','NgTableParams'];
		function DashboardController(CartService,PostService,FlashService,$rootScope,$scope,$modal,$filter,NgTableParams){

		    var vm = this;
			vm.postUserId = $rootScope.globals.currentUser.id;
						
			vm.LoadTable = function LoadTable(){
				
				
				PostService.GetBids(vm.postUserId, function (response) {
					if(response.length > 0) {
						
						var myDate = new Date(response[0].datePosted);
						angular.forEach(response, function(value,index){
							var myDate = new Date(value.datePosted);
							myDate = $filter('date')(myDate, "dd/MM/yyyy");
							value.datePosted = myDate;
						})
						$scope.bids = response;
						$scope.bidsTable = new NgTableParams({
							page: 1,
							count: 10
						}, {
							total: $scope.bids.length, 
							getData: function (params) {
								 $scope.data = params.sorting() ? $filter('orderBy')($scope.bids, params.orderBy()) : $scope.bids;
								 $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
								 $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
								 return $scope.data;
							}
						});
					}
					else{
						$scope.bids = response;
						$scope.bidsTable = new NgTableParams({
							page: 1,
							count: 10
						}, {
							total: $scope.bids.length, 
							getData: function (params) {
								 $scope.data = params.sorting() ? $filter('orderBy')($scope.bids, params.orderBy()) : $scope.bids;
								 $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
								 $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
								 return $scope.data;
							}
						});
					}
				});
			};
			
			vm.LoadTable();
			
			vm.openModal = function() {
				
				var modalInstance = $modal.open({
					templateUrl: 'dashboard/modal.html',
					controller: 'ModalInstanceCtrl',
				});

			};
			
			vm.addToCart = function(itemId,bidUserId,basePrice){
				
				vm.bid = {"itemId": itemId,
						  "bidUserId": bidUserId,
						  "postUserId": vm.postUserId,
						  "basePrice": basePrice
						 };

				CartService.AddToCart(vm.bid, function (response) {
					if (response.success) {
						vm.LoadTable();
						FlashService.Success('Moved To Cart Successfully', true);
					} else {
						FlashService.Error(response.message);
						$scope.dataLoading = false;
					}
				});
								
				
			}
			
			
		};

		ModalInstanceCtrl.$inject = ['$scope','$location', '$rootScope','$modalInstance','PostService','FlashService'];
		function ModalInstanceCtrl($scope,$location, $rootScope,$modalInstance,PostService,FlashService) {
			
			$scope.post = function() {
				
				$scope.item.postUserId = $rootScope.globals.currentUser.id;
				PostService.PostItem($scope.item, function (response) {
					if (response.success) {
						FlashService.Success('Item Posted Successfully', true);
						$modalInstance.dismiss('cancel');
						} else {
						FlashService.Error(response.message);
						$scope.dataLoading = false;
					}
				});
			};

						
			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};

			$scope.cancel = function () {
				$scope.item = null;
			};

		};


})();
