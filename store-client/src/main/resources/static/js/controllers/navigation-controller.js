application.controller('navigation', function ($rootScope, $http, $location, inventoryService, LoginService) {
    var self = this;


    self.getUserId = function(){
        if(LoginService.getCurrentUser() != null){
            return LoginService.getCurrentUser().id;
        }

        return -1;
    }

    self.getRole = function(){
        if(LoginService.getCurrentUser() != null){
            return LoginService.getCurrentUser().authorities[0].authority;
        }

        return "UNAUTHORIZED";
    }

    $rootScope.$on('authorized', function(){
        inventoryService.getInventory();
    });

    $rootScope.$on('unauthorized', function(){
        console.log("unauthorized broadcast");
        LoginService.setCurrentUser(null);
        inventoryService.clearInventory();
    });

    self.isAuthenticated = function(){
        return LoginService.getCurrentUser() != null;
    }


    self.logout = function (){
        $http.post('logout', {}).finally(function () {
           $rootScope.authenticated = false;
           $rootScope.user = {};
           $location.path('/');
        });
    }



});