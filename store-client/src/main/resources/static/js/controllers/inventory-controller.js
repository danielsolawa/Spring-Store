application.controller('inventoryController',['$rootScope', '$location', '$mdDialog','inventoryService', 'ordersService',
    'LoginService',
    function($rootScope, $location, $mdDialog, inventoryService, ordersService, LoginService){
    var self = this;
    var userId = LoginService.getCurrentUser().id;
    self.inventory = [];




    self.getTotalPrice = function(){
        for(var i = 0; i < self.filteredInventory.length; i++){
            self.totalPrice += (self.filteredInventory[i].price * self.filteredInventory[i].amount);
        }
    }



    self.order = function(){
        var productsToOrder = $rootScope.inventory.products;
        var newOrder = {products: productsToOrder};

        var emptyInventory = {products:[]};


        ordersService.save({id: userId}, newOrder, function(response){

            inventoryService.update({id: userId}, emptyInventory, function(response){
                $rootScope.inventory.products = [];
                $location.path("/users/" + userId + "/orders");

            }, function(error){
                console.log("update inventory error");
            });
        }, function(error){
            console.log("an error has occurred");
        });
    }

    self.fetchInventory = function(){
        self.filteredInventory = [];
        self.totalPrice = 0;
        inventoryService.get({id: userId}, function(response){
            $rootScope.inventory = response;
            self.inventory = response.products;
            prepareInventory();

        }, function(error){
            console.log("error");
        });
    }


    self.updateInventory = function(){

      var updatedInventory = {userId: userId ,products:[]};
      for(var i = 0; i < self.filteredInventory.length; i++){
          for(var j = 0; j < self.filteredInventory[i].amount; j++){
              updatedInventory.products.push({id: self.filteredInventory[i].id,
              name: self.filteredInventory[i].name, price: self.filteredInventory[i].price});
          }
      }


      inventoryService.update({id: userId}, updatedInventory, function(response){

          self.fetchInventory();
      }, function(error){
          console.log(error);
      });
    }


    self.removeAllProducts = function(){
        if(self.filteredInventory != null){
            self.filteredInventory = [];
            self.updateInventory();
        }
    }

    var prepareInventory = function(){
        inventoryLoop:
        for(var i = 0; i < self.inventory.length; i++){
            for(var j = 0; j < self.filteredInventory.length; j++){
                if(self.filteredInventory[j].id == self.inventory[i].id){
                    self.filteredInventory[j].amount++;
                    continue inventoryLoop;
                }
            }

            addToInventory(self.inventory[i]);

        }

        self.getTotalPrice();

    }


    var addToInventory = function(product){
        self.filteredInventory.push({
            id: product.id, name: product.name, price: product.price,
            description: product.description, amount: 1
        });

    }


    self.showConfirm = function(ev) {

        var confirm = $mdDialog.confirm()
            .title('Confirm your order')
            .ariaLabel('Lucky day')
            .targetEvent(ev)
            .ok('Order now')
            .cancel('Cancel');

        $mdDialog.show(confirm).then(function() {
            self.order();
        }, function() {
            self.status = 'You decided to keep your debt.';
        });
    };





}]);