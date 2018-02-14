application
    .controller('productView',['$rootScope', '$routeParams', '$location', 'productsService', 'inventoryService',
        'LoginService',
        function($rootScope, $routeParams, $location, productsService, inventoryService, LoginService){
        var self = this;
        var user = LoginService.getCurrentUser();


        var product = productsService.get({id: $routeParams.id, prodId: $routeParams.prodId},function(){
            self.product = product;
        });


        self.addToInventory = function(){

          if(LoginService.getCurrentUser() == null){
              $location.path("/");
          }else{
              inventoryService.get({id: user.id}, function(response){
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

        }
    }]);