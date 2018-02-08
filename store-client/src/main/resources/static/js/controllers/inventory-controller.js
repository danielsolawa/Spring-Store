application.controller('inventoryController',['$rootScope','inventoryService', function($rootScope, inventoryService){
    var self = this;
    self.inventory = [];


    self.getTotalPrice = function(){
        for(var i = 0; i < self.filteredInventory.length; i++){
            self.totalPrice += (self.filteredInventory[i].price * self.filteredInventory[i].amount);
        }
    }

    self.fetchInventory = function(){
        self.filteredInventory = [];
        self.totalPrice = 0;
        inventoryService.get({id: $rootScope.user.id}, function(response){
            $rootScope.inventory = response;
            self.inventory = response.products;
            prepareInventory();

        }, function(error){
            console.log("error");
        });
    }


    self.updateInventory = function(){

      var updatedInventory = {userId: $rootScope.user.id ,products:[]};
      for(var i = 0; i < self.filteredInventory.length; i++){
          for(var j = 0; j < self.filteredInventory[i].amount; j++){
              updatedInventory.products.push({id: self.filteredInventory[i].id,
              name: self.filteredInventory[i].name, price: self.filteredInventory[i].price});
          }
      }


      inventoryService.update({id: $rootScope.user.id}, updatedInventory, function(response){

          self.fetchInventory();
      }, function(error){
          console.log(error);
      });
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





}]);