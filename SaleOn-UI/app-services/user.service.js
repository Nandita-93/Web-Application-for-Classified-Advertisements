(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
		service.Logout = Logout;

        return service;

        function Create(user,callback) {
            return $http.put('https://localhost:9009/ws/register', user)
					   .success(function (response) {
							callback(response);
						});    
        }

        function Update(user,callback) {
            return $http.put('https://localhost:9009/ws/profile',user)
                            .success(function (response) {
                            callback(response);
                        }); 
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

		function Logout(username,callback) {
            return $http.post('https://localhost:9009/ws/logout',{'userName': username})
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
