var application = angular.module('springstore', ['ngResource',
    'ngMessages', 'ngMaterial', 'angular-storage', 'ui.router']);

application.config(['$httpProvider', '$locationProvider', '$stateProvider', '$urlRouterProvider',
    function ($httpProvider, $locationProvider, $stateProvider, $urlRouterProvider) {



        $stateProvider
            .state('home',{
                url: '/',
                views: {
                    'main': {
                        templateUrl: 'home.html',
                        controller: 'categories',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('categories',{
            url: '/categories/{categoryId}',
            views: {
                'main': {
                    templateUrl: 'category-view.html',
                    controller: 'categoriesView',
                    controllerAs: 'controller'
                }
            }
            })
            .state('products',{
            url: '/categories/{categoryId}/products/{productId}',
            views: {
                'main': {
                    templateUrl: 'product-view.html',
                    controller: 'productView',
                    controllerAs: 'controller'
                }
            }
            })
            .state('add-user',{
                url: '/users/new',
                views: {
                    'main': {
                        templateUrl: 'user-add.html',
                        controller: 'userAdd',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('registered-user',{
                url: '/users/registered/{username}',
                views: {
                    'main': {
                        templateUrl: 'user-registered.html',
                        controller: 'userRegistered',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('admin-panel',{
                url: '/admin',
                views: {
                    'main': {
                        templateUrl: 'admin-panel.html',
                        controller: 'adminPanel',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('admin-panel.users',{
                url: '/users/{page}',
                views: {
                    'admin-panel': {
                        templateUrl: 'admin-users.html',
                        controller: 'adminPanelUsers',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('admin-panel.categories',{
                url: '/categories',
                views: {
                    'admin-panel': {
                        templateUrl: 'admin-categories.html',
                        controller: 'adminPanelCategories',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('admin-panel.category',{
                url: '/categories/{categoryId}',
                views: {
                    'admin-panel': {
                        templateUrl: 'admin-products.html',
                        controller: 'adminPanelProducts',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('admin-panel.messages',{
                url: '/messages',
                views: {
                    'admin-panel': {
                        templateUrl: 'admin-messages.html',
                        controller: 'adminPanelMessages',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('admin-panel.conversation',{
                url: '/messages/{userId}',
                views: {
                    'admin-panel': {
                        templateUrl: 'admin-conversation.html',
                        controller: 'adminPanelMessages',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('admin-panel.conversation-detail',{
                url: '/messages/{userId}/conversation/{conversationId}',
                views: {
                    'admin-panel': {
                        templateUrl: 'admin-conversation-details.html',
                        controller: 'adminPanelMessages',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('inventory',{
                url: '/inventory',
                views: {
                    'main': {
                        templateUrl: 'inventory.html',
                        controller: 'inventoryController',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('orders',{
                url: '/users/{userId}/orders',
                views: {
                    'main': {
                        templateUrl: 'orders.html',
                        controller: 'ordersController',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('activation-token',{
                url: '/users/{username}/activate/{token}',
                views: {
                    'main': {
                        templateUrl: 'activate-account.html',
                        controller: 'activateAccountController',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('add-address',{
                url: '/users/{id}/address/add/{type}',
                views: {
                    'main': {
                        templateUrl: 'add-address.html',
                        controller: 'addressController',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('user-profile',{
                url: '/users/{id}/profile',
                views: {
                    'main': {
                        templateUrl: 'user-profile.html',
                        controller: 'userProfile',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('contact',{
                url: '/users/{id}/contact',
                views: {
                    'main': {
                        templateUrl: 'user-contact-view.html',
                        controller: 'userContactController',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('conversation',{
                url: '/users/{id}/contact/{conversationId}',
                views: {
                    'main': {
                        templateUrl: 'user-conversation-view.html',
                        controller: 'userConversationController',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('product-search',{
                url: '/product-search/{keyword}/page/{page}',
                views: {
                    'main': {
                        templateUrl: 'product-search.html',
                        controller: 'productSearchController',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('error401',{
                url: '/error401',
                views: {
                    'main': {
                        templateUrl: 'error401.html',
                    }
                }
            });




        $urlRouterProvider.otherwise('/');
/*


        var homeState = {
        name: 'home',
        url: '/',
        templateUrl: 'home.html',
        controller: 'categories',
        controllerAs: 'controller'
    }

    var categoriesState = {
        name: 'categories',
        url: '/categories/{categoryId}',
        views: 'main',
        templateUrl: 'category-view.html',
        controller: 'categoriesView',
        controllerAs: 'controller'
    }

    var productsState = {
        name: 'products',
        url: '/categories/{categoryId}/products/{productId}',
        views: 'main',
        templateUrl: 'product-view.html',
        controller: 'productView',
        controllerAs: 'controller'
    }

    var addUserState = {
        name: 'add-user',
        url: '/users/new',
        views: 'main',
        templateUrl: 'user-add.html',
        controller: 'userAdd',
        controllerAs: 'controller'
    }

    var registeredUserState = {
        name: 'registered-user',
        url: '/users/registered/{username}',
        views: 'main',
        templateUrl: 'user-registered.html',
        controller: 'userRegistered',
        controllerAs: 'controller'

    }

    var adminPanelState = {
        name: 'admin-panel',
        abstract: true,
        views: 'main',
        url: '/admin',
        templateUrl: 'admin-panel.html',
        controller: 'adminPanel',
        controllerAs: 'controller'
    }

    var adminPanelUsersState = {
        name: 'admin-panel.users',
        url: '/users',
        templateUrl: 'admin-users.html',
        controller: 'adminPanelUsers',
        controllerAs: 'controller'
    }

    var adminPanelUsersDetailState = {
        name: 'admin-panel.users/detail',
        url: '/detail',
        templateUrl: 'admin-user-detail.html',
        controller: 'adminPanelUserDetail',
        controllerAs: 'controller'
    }


    var adminPanelCategoriesState = {
        name: 'admin-panel.categories',
        url: '/categories',
        templateUrl: 'admin-categories.html',
        controller: 'adminPanelCategories',
        controllerAs: 'controller'
    }

    var adminPanelProductsState = {
        name: 'admin-panel.category',
        url: '/categories/{categoryId}',
        templateUrl: 'admin-products.html',
        controller: 'adminPanelProducts',
        controllerAs: 'controller'
    }


    var adminPanelMessagesState = {
        name: 'admin-panel.messages',
        url: '/messages',
        templateUrl: 'admin-messages.html',
        controller: 'adminPanelMessages',
        controllerAs: 'controller'
    }


    var adminPanelConversationState = {
        name: 'admin-panel.conversation',
        url: '/messages/{userId}',
        templateUrl: 'admin-conversation.html',
        controller: 'adminPanelMessages',
        controllerAs: 'controller'
    }

    var adminPanelConversationDetailState = {
        name: 'admin-panel.conversation-detail',
        url: '/messages/{userId}/conversation/{conversationId}',
        templateUrl: 'admin-conversation-details.html',
        controller: 'adminPanelMessages',
        controllerAs: 'controller'
    }

    var inventoryState = {
        name: 'inventory',
        views: 'main',
        url: '/inventory',
        templateUrl: 'inventory.html',
        controller: 'inventoryController',
        controllerAs: 'controller'
    }

    var ordersState = {
        name: 'orders',
        views: 'main',
        url: '/users/{userId}/orders',
        templateUrl: 'orders.html',
        controller: 'ordersController',
        controllerAs: 'controller'
    }


    var activationTokenState = {
        name: 'activation-token',
        views: 'main',
        url: '/users/{username}/activate/{token}',
        templateUrl: 'activate-account.html',
        controller: 'activateAccountController',
        controllerAs: 'controller'
    }


    var addAddressState = {
        name: 'add-address',
        views: 'main',
        url: '/users/{id}/address/add/{type}',
        templateUrl: 'add-address.html',
        controller: 'addressController',
        controllerAs: 'controller'
    }



    var userProfileState = {
        name: 'user-profile',
        views: 'main',
        url: '/users/{id}/profile',
        templateUrl: 'user-profile.html',
        controller: 'userProfile',
        controllerAs: 'controller'
    }


    var contactState = {
        name: 'contact',
        views: 'main',
        url: '/users/{id}/contact',
        templateUrl: "user-contact-view.html",
        controller: 'userContactController',
        controllerAs: 'controller'
    }

    var conversationState = {
        name: 'conversation',
        views: 'main',
        url: '/users/{id}/contact/{conversationId}',
        templateUrl: "user-conversation-view.html",
        controller: 'userConversationController',
        controllerAs: 'controller'
    }


    var productSearchState = {
        name: 'product-search',
        views: 'main',
        url: '/product-search/{keyword}',
        templateUrl: 'product-search.html',
        controller: 'productSearchController',
        controllerAs: 'controller'
    }
=======================
    var error401 = {
        name: 'error401',
        views: 'main',
        url: '/error401',
        templateUrl: 'error401.html'
    }


    $urlRouterProvider.otherwise('/');

    $stateProvider.state(homeState);
    $stateProvider.state(categoriesState);
    $stateProvider.state(productsState);
    $stateProvider.state(addUserState);
    $stateProvider.state(registeredUserState);
    $stateProvider.state(adminPanelState);
    $stateProvider.state(adminPanelUsersState);
    $stateProvider.state(adminPanelUsersDetailState);
    $stateProvider.state(adminPanelCategoriesState);
    $stateProvider.state(adminPanelProductsState);
    $stateProvider.state(adminPanelMessagesState);
    $stateProvider.state(adminPanelConversationState);
    $stateProvider.state(adminPanelConversationDetailState);
    $stateProvider.state(inventoryState);
    $stateProvider.state(ordersState);
    $stateProvider.state(activationTokenState);
    $stateProvider.state(addAddressState);
    $stateProvider.state(userProfileState);
    $stateProvider.state(contactState);
    $stateProvider.state(conversationState);
    $stateProvider.state(productSearchState);
    $stateProvider.state(error401);
*/

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.common['Accept'] = 'application/json';



    $locationProvider.html5Mode(false);
}]);





