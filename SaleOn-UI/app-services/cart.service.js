
(function () {
    'use strict';

    angular
        .module('app')
        .factory('CartService', CartService);

    CartService.$inject = ['$http'];
    function CartService($http) {
        var service = {};

        service.AddToCart = AddToCart;
		service.GetCartItem = GetCartItem;
		service.CheckOut = CheckOut;
		service.RemoveFromCart = RemoveFromCart;
		
        return service;

        function AddToCart(item,callback) {
            return $http.put('https://localhost:9009/cart/addtocart',item)
					   .success(function (response) {
							callback(response);
						});       
		}
		
		function GetCartItem(id,callback) {
			return $http.get('https://localhost:9009/cart/getcartitems',{params: {postUserId: id}})
					   .success(function (response) {
							callback(response);
						});       
		}
		
        function CheckOut(checkedOutItem,callback) {
            return $http.post('https://localhost:9009/cart/checkout',checkedOutItem)
					   .success(function (response) {
							callback(response);
						});       
		}		

        function RemoveFromCart(deletedItem,callback) {
            return $http.post('https://localhost:9009/cart/removefromcart',deletedItem)
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
