(function () {
    'use strict';

    angular
        .module('app')
        .controller('CartController', CartController);

	CartController.$inject = ['CartService','FlashService','$rootScope','$scope'];
    function CartController(CartService,FlashService,$rootScope,$scope) {
		var vm = this;
			vm.postUserId = $rootScope.globals.currentUser.id;
		
		vm.getCartItem = function (){
			CartService.GetCartItem(vm.postUserId, function (response) {
				if(response.length > 0) {
					vm.items = response;	
				}		
				else{
					vm.items = null;
				}
			});
		}
		
		vm.getCartItem();
		
		vm.removeItem = function(itemId,index) {
			
			var deletedItem ={ 
								"itemId" : itemId
							 };
			CartService.RemoveFromCart(deletedItem, function (response) {
				if(response.success) {
					vm.items.splice(index, 1);
				}
			});
		},

		vm.checkOut = function(itemId,quantity,index) {
			var checkedOutItem ={ 
								  "itemId" : itemId,
			                      "quantity" : quantity
								};
		
			CartService.CheckOut(checkedOutItem, function (response) {
				if(response.success) {
					vm.items.splice(index, 1);
				}
			
		});					
			
			
			
		},
		vm.total = function() {
			var total = 0;
			angular.forEach(vm.items, function(item) {
				total += item.quantity * item.basePrice;
			})

			return total;
		}

	}

})();
