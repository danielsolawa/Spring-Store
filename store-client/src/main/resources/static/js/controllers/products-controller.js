application
    .controller('productView',['$rootScope', '$routeParams', '$location', "$mdDialog", 'productsService', 'inventoryService',
        'LoginService',
        function($rootScope, $routeParams, $location, $mdDialog, productsService, inventoryService,
                 LoginService){
        var self = this;
        var user = LoginService.getCurrentUser();

        self.customFullscreen = false;
        self.amount = 1;


        var product = productsService.get({id: $routeParams.id, prodId: $routeParams.prodId},function(){
            self.product = product;
        });



        self.showConfirmDialog = function(ev) {

            if(LoginService.getCurrentUser() == null){
                location.href = "/login";
            }


            for(var i = 0; i < self.amount; i++){
                inventoryService.addToInventory(product);
            }

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
                    inventoryService.removeLastProduct(self.amount);
                });
        };


    }]);