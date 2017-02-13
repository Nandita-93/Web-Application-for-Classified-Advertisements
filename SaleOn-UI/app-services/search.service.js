(function () {
    'use strict';

    angular
        .module('app')
        .factory('SearchService', SearchService);

    SearchService.$inject = ['$http'];
    function SearchService($http) {
        var service = {};

        service.Retrieve = Retrieve;
        service.BidItem = BidItem;

        return service;

        function Retrieve(id,callback) {
            return $http.get('https://localhost:9009/search/retrieve',{params: {id: id}})
                            .success(function (response) {
                            callback(response);
                        }); 
        }

       	
        function BidItem(item,callback) {
            return $http.put('https://localhost:9009/bid/bidItems',item)
                       .success(function (response) {
                            callback(response);
                        });       
        }		
        // private functions

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
