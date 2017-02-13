(function () {
    'use strict';

    angular
        .module('app')
        .controller('SearchController', SearchController)
        .controller('BidController', BidController);

    SearchController.$inject = ['SearchService','$rootScope','$scope','$modal','$filter','AuthenticationService','NgTableParams'];
    function SearchController(SearchService,$rootScope,$scope,$modal,$filter,AuthenticationService,NgTableParams) {
        

			var id = $rootScope.globals.currentUser.id;
            SearchService.Retrieve(id,function (response) {

                
                if(response) {
                    var myDate = new Date(response[0].datePosted);
                    angular.forEach(response, function(value,index){
                        var myDate = new Date(value.datePosted);
                        myDate = $filter('date')(myDate, "dd/MM/yyyy");
                        value.datePosted = myDate;
                    })
                    $scope.items = response;
                    $scope.itemsTable = new NgTableParams({
                        page: 1,
                        count: 10
                    }, {
                        total: $scope.items.length, 
                        getData: function (params) {
                             $scope.data = params.sorting() ? $filter('orderBy')($scope.items, params.orderBy()) : $scope.items;
                             $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
                             $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
                             return $scope.data;
                        }
                    });
                }
            });

            $scope.openModal = function(id) {
                $rootScope.itemId = id;
                var modalInstance = $modal.open({
                    templateUrl: 'search/bid.html',
                    controller: 'BidController',
                });

            };


    }

        BidController.$inject = ['$scope','$location','$rootScope','$modalInstance','SearchService','FlashService'];
        function BidController($scope,$location,$rootScope,$modalInstance,SearchService,FlashService) {
            
            $scope.post = function() {
                
                $scope.item.bidUserId = $rootScope.globals.currentUser.id;
                $scope.item.emailId = $rootScope.globals.currentUser.emailId;
                $scope.item.itemId = $rootScope.itemId;
                SearchService.BidItem($scope.item, function (response) {
                    if (response.success) {
                        FlashService.Success('Your bid was done Successfully', true);
                        $modalInstance.dismiss('cancel');
                    } else {
                        FlashService.Error(response.message);
                        $scope.dataLoading = false;
                    }
                });0
            };

            $scope.close = function () {
                $modalInstance.dismiss('cancel');
            };

            $scope.cancel = function () {
                $scope.item = null;
            };

        };

})();