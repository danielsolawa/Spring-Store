var application = angular.module('springstore', ['ngRoute', 'ngResource',
    'ngMessages', 'ngMaterial', 'ngCookies']);

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
        .when('/admin', {
            templateUrl: 'admin-panel.html',
            controller: 'adminPanel',
            controllerAs: 'controller'
        })
        .when('/inventory', {
            templateUrl: 'inventory.html',
            controller: 'inventoryController',
            controllerAs: 'controller'
        })
        .when("/users/:id/orders", {
            templateUrl: 'orders.html',
            controller: 'ordersController',
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
    .factory('authHttpResponseInterceptor',['$q','$location',function($q,$location){
    return {
        response: function(response){
            if (response.status === 401) {
                console.log("Response 401");
            }
            return response || $q.when(response);
        },
        responseError: function(rejection) {
            if (rejection.status === 401) {
                console.log("Response Error 401",rejection);
                $location.path('/').search('returnTo', $location.path());
            }
            return $q.reject(rejection);
        }
    }
}])
    .config(['$httpProvider',function($httpProvider) {
    
        $httpProvider.interceptors.push('authHttpResponseInterceptor');
    }]);