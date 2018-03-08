var application = angular.module('springstore', ['ngResource',
    'ngMessages', 'ngMaterial', 'angular-storage', 'ui.router']);

application.config(['$httpProvider', '$locationProvider', '$stateProvider', '$urlRouterProvider',
    function ($httpProvider, $locationProvider, $stateProvider, $urlRouterProvider) {


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
        templateUrl: 'category-view.html',
        controller: 'categoriesView',
        controllerAs: 'controller'
    }

    var productsState = {
        name: 'products',
        url: '/categories/{categoryId}/products/{productId}',
        templateUrl: 'product-view.html',
        controller: 'productView',
        controllerAs: 'controller'
    }

    var addUserState = {
        name: 'add-user',
        url: '/users/new',
        templateUrl: 'user-add.html',
        controller: 'userAdd',
        controllerAs: 'controller'
    }

    var registeredUserState = {
        name: 'registered-user',
        url: '/users/registered/{username}',
        templateUrl: 'user-registered.html',
        controller: 'userRegistered',
        controllerAs: 'controller'

    }

    var adminPanelState = {
        name: 'admin-panel',
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
        url: '/inventory',
        templateUrl: 'inventory.html',
        controller: 'inventoryController',
        controllerAs: 'controller'
    }

    var ordersState = {
        name: 'orders',
        url: '/users/{userId}/orders',
        templateUrl: 'orders.html',
        controller: 'ordersController',
        controllerAs: 'controller'
    }

    var activationTokenState = {
        name: 'activation-token',
        url: '/users/{username}/activate/{token}',
        templateUrl: 'activate-account.html',
        controller: 'activateAccountController',
        controllerAs: 'controller'
    }

    var addAddressState = {
        name: 'add-address',
        url: '/users/{id}/address/add/{type}',
        templateUrl: 'add-address.html',
        controller: 'addressController',
        controllerAs: 'controller'
    }

    var userProfileState = {
        name: 'user-profile',
        url: '/users/{id}/profile',
        templateUrl: 'user-profile.html',
        controller: 'userProfile',
        controllerAs: 'controller'
    }

    var contactState = {
        name: 'contact',
        url: '/users/{id}/contact',
        templateUrl: "user-contact-view.html",
        controller: 'userContactController',
        controllerAs: 'controller'
    }

    var conversationState = {
        name: 'conversation',
        url: '/users/{id}/contact/{conversationId}',
        templateUrl: "user-conversation-view.html",
        controller: 'userConversationController',
        controllerAs: 'controller'
    }

    var productSearchState = {
        name: 'product-search',
        url: '/product-search/{keyword}',
        templateUrl: 'product-search.html',
        controller: 'productSearchController',
        controllerAs: 'controller'
    }

    var error401 = {
        name: 'error401',
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


    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.common['Accept'] = 'application/json';



    $locationProvider.html5Mode(false);
}]);





