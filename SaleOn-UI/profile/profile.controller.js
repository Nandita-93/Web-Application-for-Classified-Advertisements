(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProfileController', ProfileController);

    ProfileController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function ProfileController(UserService, $location, $rootScope, FlashService) {
        var vm = this;
		vm.user = $rootScope.globals.currentUser;
        vm.profile = function(){
            vm.dataLoading = true;
            vm.user.id = $rootScope.globals.currentUser.id;
            UserService.Update(vm.user, function (response)
                {
                    if (response.success) {

                        FlashService.Success('Successfully modified', true);
                        $location.path('/default');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();
