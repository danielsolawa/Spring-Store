application.run(function ($rootScope, $location, $http, LoginService, inventoryService) {

    $rootScope.$on("$routeChangeStart", function (event, next, current) {
        $http.get('principal').then(function(response){

            if(LoginService.getCurrentUser() == null){
                LoginService.setCurrentUser(response.data);
                LoginService.setWasLogged(true);
                $rootScope.$broadcast('authorized');
                $rootScope.user = response.data;
            }



        }, function(error){
            $rootScope.$broadcast('unauthorized');
        });
    });
});