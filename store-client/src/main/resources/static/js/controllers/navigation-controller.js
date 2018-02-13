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

    /*
    $http.get('principal').then(function(response){

       if(response.data.username){
           $rootScope.authenticated = true;
           $rootScope.wasLoggedIn = true;
           $rootScope.user = {'id': response.data.id,
               'username': response.data.username, 'authority': response.data.authorities[0].authority};




       }else{
           $rootScope.authenticated = false;
       }

    }, function () {
        $rootScope.authenticated = false;
    });*/

    self.logout = function (){
        $http.post('logout', {}).finally(function () {
           $rootScope.authenticated = false;
           $rootScope.user = {};
           $location.path('/');
        });
    }



});