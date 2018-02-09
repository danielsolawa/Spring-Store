application.factory('ordersService', function($resource){
   return $resource('users/:id/orders/:orderId', {id: '@_id', orderId: '@_orderId'},{
       update: {
           method: 'PUT'
       }
   });
});