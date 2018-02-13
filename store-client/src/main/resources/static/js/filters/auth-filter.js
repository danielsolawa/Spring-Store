application.factory('authHttpResponseInterceptor',['$q','$location', 'LoginService'
    ,function($q, $location, LoginService){

    return {
        response: function(response){

            if (response.status === 401) {
                console.log("response");
            }
            return response || $q.when(response);
        },
        responseError: function(rejection) {
            if(LoginService.getWasLogged()){
                if(rejection.status == 401){
                    LoginService.setWasLogged(false);
                    $location.path('/error401').search('returnTo', $location.path());
                }
            }


            return $q.reject(rejection);
        }
    }
}])
    .config(['$httpProvider',function($httpProvider) {

        $httpProvider.interceptors.push('authHttpResponseInterceptor');
    }]);