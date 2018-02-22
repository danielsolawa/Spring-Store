application.factory('addressService', function($resource){
   return $resource('users/:id/address', {id: '@_id'}, {
       update:{
           method: 'PUT'
       }
   })
});