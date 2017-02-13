(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies','ui.bootstrap','ngTable','xeditable'])
		.controller('testCtrl', testCtrl)
		.config(config)
        .run(run);
		 

	testCtrl.$inject= ['$rootScope','$location','UserService'];
    function testCtrl($rootScope,$location,UserService){
		
		$rootScope.logout = function() {
			var vm = $rootScope.globals.currentUser;
			UserService.Logout(vm.userName, function (response) {
                if(response.success) {
                    $rootScope.loginTime = '';
					$rootScope.IsUserLoggedIn = false;
					$rootScope.displayText = 'Already have an account?';
					$location.path('/default');

                }
			});
		};
	
    }		 
		 
    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })

			.when('/search', {
                controller: 'SearchController',
                templateUrl: 'search/search.view.html',
                controllerAs: 'vm'
            })
			
            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register/register.view.html',
                controllerAs: 'vm'
            })
			
			.when('/default', {
				
				
				templateUrl: 'default/default.html',
                
				

            })
			
			.when('/profile', {
				
				controller: 'ProfileController',
				templateUrl: 'profile/profile.html',
                controllerAs: 'vm'
				

            })
			
			.when('/dashboard', {
				
				controller: 'DashboardController',
				templateUrl: 'dashboard/dashboard.view.html',
                controllerAs: 'vm'
	
            })
			
			.when('/cart', {
				
				controller: 'CartController',
				templateUrl: 'cart/cart.view.html',
                controllerAs: 'vm'
	
            })
			

            .otherwise({ redirectTo: '/default' });
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
		
		$rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
				$rootScope.IsUserLoggedIn = false;
                $location.path('/default');
            }
        });
    }
	
	
})();