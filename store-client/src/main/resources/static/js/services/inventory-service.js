application.service('inventoryService', function($resource, $rootScope, LoginService, InventoryResource, store){

    var self = this,
        inventory = null;


    self.getInventorySize = function(){
        if(!isEmpty()){
            inventory = store.get('inventory');

            return inventory.length;
        }

        return 0;
    }


    self.getLocalInventory = function(){
        if(!isEmpty()){
            return store.get('inventory');
        }

        return null;
    }


    self.updateInventory = function(callback){
        InventoryResource.update({id: LoginService.getCurrentUser().id},
            {userId: LoginService.getCurrentUser().id, products: inventory}, function(response){
                setUpInventoryData(response);
                callback();
            }, function(error){
                console.log("an error has occurred during update.")
            });
    }


    self.getInventory = function(callback){
        InventoryResource.get({id: LoginService.getCurrentUser().id}, function(response){

            setUpInventoryData(response);
            callback();
        }, function(error){
            console.log("an error has occurred");
        });
    }

    var setUpInventoryData = function(response){
        inventory = response.products;
        store.set('inventory', response.products);
    }


    self.getTotalPrice = function(){
        if(!isEmpty()){
            var totalPrice = 0;
            inventory = store.get('inventory');

            for(var i = 0; i < inventory.length; i++){
                totalPrice += inventory[i].price;
            }
            return totalPrice;
        }

        return 0;


    }


    self.getSortedInventory = function(){

        if(!isEmpty()){
            inventory = store.get('inventory');
        }


        var filteredInventory = [];

        inventoryLoop:
            for(var i = 0; i < inventory.length; i++){
                for(var j = 0; j < filteredInventory.length; j++){
                    if(filteredInventory[j].id == inventory[i].id){
                        filteredInventory[j].amount++;
                        continue inventoryLoop;
                    }
                }

                filteredInventory.push({
                    id: inventory[i].id, name: inventory[i].name, price: inventory[i].price,
                    description: inventory[i].description, amount: 1
                });


            }


        return filteredInventory;
    }



    self.addToInventory = function(product){
        inventory = self.getLocalInventory();
        inventory.push({id: product.id, name: product.name,
                price: product.price, description: product.description});

    }

    self.updateLocalInventory = function(inv, callback){
        var inventoryToSave = [];
        for(var i = 0; i < inv.length; i++){
            for(var j = 0; j < inv[i].amount; j++){
                inventoryToSave.push({id: inv[i].id, name: inv[i].name,
                    price: inv[i].price, description: inv[i].description});
            }
        }

        inventory = inventoryToSave;
        self.updateInventory(callback);

    }


    self.removeLastProduct = function(amount){
        if(!isEmpty()){
            if(inventory.length > 0){
                var reduceTo = inventory.length -1 - amount;
                for(var i = inventory.length -1; i > reduceTo; i--){
                    inventory.splice(i, 1);
                }

            }
        }

    }

    self.clearInventory = function(){
        if(!isEmpty()){
            self.inventory = null;
            store.set('inventory', null);
        }

    }



    var isEmpty = function(){
        return store.get('inventory') == null;
    }









});