application.run(function ($rootScope, $location, $http, $window,  LoginService, inventoryService) {
    $rootScope.$on("$locationChangeStart", function (event, toUrl, fromUrl) {

        handleLogin(toUrl);

    });



    var handleLogin = function(url){
        $http.get('principal').then(function(response){

            if(LoginService.getCurrentUser() == null){
                LoginService.setCurrentUser(response.data);
                LoginService.setWasLogged(true);
                $rootScope.$broadcast('authorized');
            }


            $rootScope.user = response.data;


        }, function(error){
            $rootScope.$broadcast('unauthorized');
            if(LoginService.isUrlProtected(url)){
                LoginService.setRedirectUrl($location.path());
                $window.location.href = "login";
            }
        });
    }
});