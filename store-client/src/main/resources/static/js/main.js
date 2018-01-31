var application = angular.module('springstore', ['ngRoute', 'ngResource']);

application.config(['$routeProvider', '$httpProvider', '$locationProvider', function ($routeProvider, $httpProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'home.html',
            controller: 'categories',
            controllerAs: 'controller'
        })
        .when('/categories/:id', {
            templateUrl: 'category-view.html',
            controller: 'categoriesView',
            controllerAs: 'controller'
        })
        .when('/categories/:id/products/:prodId', {
            templateUrl: 'product-view.html',
            controller: 'productView',
            controllerAs: 'controller'
        })
        .when('/users/new', {
            templateUrl: 'user-add.html',
            controller: 'userAdd',
            controllerAs: 'controller'
        })
        .when('/users/registered/:username', {
            templateUrl: 'user-registered.html',
            controller: 'userRegistered',
            controllerAs: 'controller'
        })
        .when('/error403', {
            templateUrl: 'error403.html'
        })
        .otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.common['Accept'] = 'application/json';


    $locationProvider.html5Mode(false);
}])
    .run(function ($rootScope, $location) {
        var protectedUrlPaths = [];

        $rootScope.$on("$routeChangeStart", function(event, next, current){

            if(!$rootScope.authenticated){
                for(var i =0; i < protectedUrlPaths.length; i++){
                    if(next.templateUrl == protectedUrlPaths[i]){
                        $location.path("/error403");
                    }
                }
            }else{
                for(var i =0; i < protectedUrlPaths.length; i++){
                    if(next.templateUrl == protectedUrlPaths[i]){
                        if($rootScope.user.authorities[0].authority == 'USER'){
                            $location.path("/error403");
                        }

                    }
                }

            }
        });



    });