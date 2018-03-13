application.controller('ordersController',['$transition$','ordersService', 'dateService',
    function ($transition$, ordersService, dateService) {
   var self =  this;

   self.orderDetail = {};
   self.showProducts = false;


   self.loadData = function(){
       self.userId = $transition$.params().userId;

       ordersService.get({id: self.userId}, function(response){
          self.orders = response.orders;
       }, function(error){
           console.log("an error has occurred");
       });
   }




   self.formatDate = function(index){
       var date = self.orders[index].orderDate;

       return dateService.formatDate(date);
   }




}]).controller('ordersProductController', function($transition$, ordersService, dateService){
    var self = this;

    self.loadData = function(){
        self.userId = $transition$.params().userId;
        self.orderId = $transition$.params().orderId;

        ordersService.get({id: self.userId, orderId: self.orderId}, function(response){
            self.order = response;
            self.products = sortProducts(self.order.products)
            self.date = self.order.orderDate;

        }, function(error){
            console.log("an error has occurred.");
        })
    }

    var sortProducts = function(products){

        var sortedProducts = [];

        sortedLoop:
            for(var i = 0; i < products.length; i++){
                for(var j = 0; j < sortedProducts.length; j++){
                    if(products[i].id == sortedProducts[j].id){
                        sortedProducts[j].amount++;
                        continue sortedLoop;
                    }
                }

                sortedProducts.push({id: products[i].id, name: products[i].name, price: products[i].price, amount: 1});

            }

        return sortedProducts;
    }


    self.formatDate = function(){
        return dateService.formatDate(self.date);
    }


});