application.controller('inventoryController',['$rootScope', '$location', '$mdDialog','inventoryService', 'ordersService',
    'LoginService',
    function($rootScope, $location, $mdDialog, inventoryService, ordersService, LoginService){

    var self = this;
    var userId = LoginService.getCurrentUser().id;


    self.init = function(){
        self.totalPrice = inventoryService.getTotalPrice();
        self.filteredInventory = inventoryService.getSortedInventory();
    }




    self.order = function(){
        var newOrder = {products: inventoryService.getLocalInventory()};
        self.filteredInventory = [];
        inventoryService.updateLocalInventory(self.filteredInventory, function(){
            ordersService.save({id: userId}, newOrder, function(response){
                $location.path("/users/" + userId + "/orders");
            }, function(error){

            });
        });


    }


    self.updateInventory = function(){
        inventoryService.updateLocalInventory(self.filteredInventory, function(){
            self.init();
        });

    }



    self.deleteDialog = function(ev, id){
        var confirm = $mdDialog.confirm()
            .title('Do you really want to delete all these products?')
            .ariaLabel('Lucky day')
            .targetEvent(ev)
            .ok('Delete')
            .cancel('Cancel');

        $mdDialog.show(confirm).then(function(){
            removeOneType(id);
        }, function(){

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

        });
    };

    var removeOneType = function(id){
        var index = -1;
        for(var i = 0; i < self.filteredInventory.length; i++){
            if(self.filteredInventory[i].id == id){
                index = i;
                break;
            }
        }

        self.filteredInventory.splice(index ,1);
        inventoryService.updateLocalInventory(self.filteredInventory, function(){
            self.init();
        });
    }








    }]);