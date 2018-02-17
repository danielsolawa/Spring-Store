application.factory('inventorySortService', function(){
   var filteredInventory = [];

   var sortInventory = function(inventory){
       inventoryLoop:
           for(var i = 0; i < inventory.length; i++){
               for(var j = 0; j < filteredInventory.length; j++){
                   if(filteredInventory[j].id == inventory[i].id){
                       filteredInventory[j].amount++;
                       continue inventoryLoop;
                   }
               }

               addToInventory(inventory[i]);

           }

       return filteredInventory;
   }



   var addToInventory = function(product){
        filteredInventory.push({
            id: product.id, name: product.name, price: product.price,
            description: product.description, amount: 1
        });



    }

    var removeLastProduct = function(){
       if(filteredInventory.length > 0){
           filteredInventory.splice(filteredInventory.length -1, 1);
       }
    }

    return{
        sort: sortInventory,
        add: addToInventory,
        remove: removeLastProduct
    }
});