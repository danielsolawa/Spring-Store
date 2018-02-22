application.controller('ordersController',['$routeParams','ordersService', function ($routeParams, ordersService) {
   var self =  this;

   self.orderDetail = {};
   self.showProducts = false;


   self.fetchOrders = function(){
       var userId = $routeParams.id;
       ordersService.get({id: userId}, function(response){
          self.orders = response.orders
       }, function(error){
           console.log("an error has occurred");
       });
   }


   self.showDetails = function(index){
       self.showProducts = true;
       var sortedDetails = getSortedOrders(self.orders[index].products);

       self.orderDetail = self.orders[index];
       self.orderDetail.products = sortedDetails;


   }

   var getSortedOrders = function(orders){




       var sortedOrders = [];

       sortedLoop:
           for(var i = 0; i < orders.length; i++){
               for(var j = 0; j < sortedOrders.length; j++){
                   if(orders[i].id == sortedOrders[j].id){
                       sortedOrders[j].amount++;
                       continue sortedLoop;
                   }
               }

               sortedOrders.push({id: orders[i].id, name: orders[i].name, price: orders[i].price, amount: 1});

           }

       return sortedOrders;
   }



}]);