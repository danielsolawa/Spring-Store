var application = angular.module('springstore', ['ngRoute', 'ngResource']);

application.config(['$routeProvider', '$httpProvider', '$locationProvider', function ($routeProvider, $httpProvider, $locationProvider) {
    $routeProvider
        .when('/', {
        templateUrl: 'home.html',
        controller: 'home',
        controllerAs: 'controller'
        })
        .otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.common['Accept'] = 'application/json';

    $locationProvider.html5Mode(false);
}]);