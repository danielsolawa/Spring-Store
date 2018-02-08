application.controller('inventoryController',['$rootScope','inventoryService', function($rootScope, inventoryService){
    var self = this;
    self.inventory = [];
    self.filteredInventory = [];


    self.fetchInventory = function(){
        inventoryService.get({id: $rootScope.user.id}, function(response){

            self.inventory = response.products;
            prepareInventory();

        }, function(error){
            console.log("error");
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

    }


    var addToInventory = function(product){
        self.filteredInventory.push({
            id: product.id, name: product.name, price: product.price,
            description: product.description, amount: 1
        });
    }



}]);