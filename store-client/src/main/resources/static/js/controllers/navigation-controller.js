application.controller('navigation', function ($rootScope, $http, $location, inventoryService) {
    var self = this;


    $http.get('principal').then(function(response){
      // $rootScope.user = response.data;

       if(response.data.username){
           $rootScope.authenticated = true;
           $rootScope.user = {'id': response.data.id,
               'username': response.data.username, 'authority': response.data.authorities[0].authority};
           inventoryService.get({id: response.data.id}, function(response){
              $rootScope.inventory = response;
           });


       }else{
           $rootScope.authenticated = false;
       }

    }, function () {
        $rootScope.authenticated = false;
    });

    self.logout = function (){
        $http.post('logout', {}).finally(function () {
           $rootScope.authenticated = false;
           $rootScope.user = {};
           $location.path('/');
        });
    }



});