application.controller('navigation', function ($rootScope, $http, $location, $route) {
    var self = this;

    $http.get('principal').then(function(response){
       $rootScope.user = response.data;

       if(response.data.username){
           $rootScope.authenticated = true;
       }else{
           $rootScope.authenticated = false;
       }

    }, function () {
        $rootScope.authenticated = false;
    });

    self.logout = function (){
        $http.get('logout', {}).finally(function () {
           $rootScope.authenticated = false;
           $location.path('/');
        });
    }

});