(function () {
    'use strict';

    angular
        .module('app')
        .factory('PostService', PostService);

    PostService.$inject = ['$http'];
    function PostService($http) {
        var service = {};

        service.PostItem = PostItem;
		service.GetBids = GetBids;

        return service;

        function PostItem(item,callback) {
            return $http.put('https://localhost:9009/dashboard/post',item)
					   .success(function (response) {
							callback(response);
						});       
		}
		
		function GetBids(id,callback) {
            return $http.get('https://localhost:9009/dashboard/bids',{params: {id: id}})
					   .success(function (response) {
							callback(response);
						}); 
					
		}	

		
        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
