application.run(function ($rootScope, $location, $http, LoginService) {

    $rootScope.$on("$routeChangeStart", function (event, next, current) {

        $http.get('principal').then(function(response){

            if(LoginService.getCurrentUser() == null){
                LoginService.setCurrentUser(response.data);
                LoginService.setWasLogged(true);
                $rootScope.$broadcast('authorized');
                console.log("sending broadcast");
            }


        }, function(error){
            $rootScope.$broadcast('unauthorized');
        });

    });
});