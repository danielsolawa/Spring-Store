application
    .controller('productView',['$rootScope', '$routeParams', '$location', "$mdDialog", 'productsService', 'inventoryService',
        'LoginService',
        function($rootScope, $routeParams, $location, $mdDialog, productsService, inventoryService,
                 LoginService){
        var self = this;
        var user = LoginService.getCurrentUser();

        self.customFullscreen = false;

        var product = productsService.get({id: $routeParams.id, prodId: $routeParams.prodId},function(){
            self.product = product;
        });



        self.showConfirmDialog = function(ev) {

            inventoryService.addToInventory(product);


            $mdDialog.show({
                controller: 'ConfirmController',
                templateUrl: 'confirm.tmpl.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                fullscreen: true
            })
                .then(function(answer) {

                }, function() {
                    inventoryService.removeLastProduct();
                });
        };


    }]);