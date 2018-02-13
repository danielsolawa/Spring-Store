application.controller('navigation', function ($rootScope, $http, $location, inventoryService, LoginService) {
    var self = this;


    $rootScope.$on('authorized', function(){
        console.log("received authorized broadcast");
        var user =  LoginService.getCurrentUser();
        $rootScope.user = user;
        $rootScope.authenticated = true;
        inventoryService.get({id: user.id}, function(response){
            $rootScope.inventory = response;
        });
    });

    $rootScope.$on('unauthorized', function(){
        console.log("unauthorized broadcast");
        $rootScope.user = {};
        $rootScope.authenticated = false;
        LoginService.setCurrentUser(null);
    });



    self.logout = function (){
        $http.post('logout', {}).finally(function () {
           $rootScope.authenticated = false;
           $rootScope.user = {};
           $location.path('/');
        });
    }



});