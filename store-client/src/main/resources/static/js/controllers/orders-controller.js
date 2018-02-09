application.controller('ordersController',['$routeParams','ordersService', function ($routeParams, ordersService) {
   var self =  this;

   self.orderDetail = {};
   self.showProducts = false;


   self.fetchOrders = function(){
       var userId = $routeParams.id;
       ordersService.get({id: userId}, function(response){
          self.orders = response.orders;
       }, function(error){
           console.log("an error has occurred");
       });
   }


   self.showDetails = function(index){
       self.showProducts = true;
       self.orderDetail = self.orders[index];
   }



}]);