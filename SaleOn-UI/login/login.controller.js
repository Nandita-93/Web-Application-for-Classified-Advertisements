(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope','$location','$filter', 'AuthenticationService', 'FlashService'];
    function LoginController($rootScope,$location,$filter, AuthenticationService, FlashService,IsUserLoggedInService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.id != null) {
                    AuthenticationService.SetCredentials(response.id, response.firstName, response.lastName, response.userName, response.password, response.emailId);
					$rootScope.IsUserLoggedIn = true;
					var myDate = new Date(  response.loginTime); 
                    myDate = $filter('date')(myDate, 'MMM d, y h:mm:ss a');
                    $rootScope.loginTime = 'Last Login: '+' ' + myDate+ ' at '+response.loginLocation ;  
					$rootScope.displayText = 'Welcome' +' '+ response.firstName +'!';
                    $location.path('/default');
                } else {
                    FlashService.Error("Invalid Username or Password");
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
