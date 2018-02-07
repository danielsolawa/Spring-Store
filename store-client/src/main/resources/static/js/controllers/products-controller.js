application
    .controller('productView',['$rootScope', '$routeParams', 'productsService', 'inventoryService' ,
        function($rootScope, $routeParams, productsService, inventoryService){
        var self = this;


        var product = productsService.get({id: $routeParams.id, prodId: $routeParams.prodId},function(){
            self.product = product;
        });

        self.addToInventory = function(){
           inventoryService.get({id: $rootScope.user.id}, function(response){
               var inventory = response;
               inventory.products.push(product);
               inventoryService.update({id: $rootScope.user.id}, inventory, function(response){
                   $rootScope.inventory = response;
               }, function(error){
                   console.log("an error has occurred");
               });
           }, function(error){
              console.log("an error has occurred");
           });
        }
    }]);