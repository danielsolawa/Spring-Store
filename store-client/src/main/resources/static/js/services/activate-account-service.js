application.factory('activateAccountService', function($resource){
   return $resource("users/:id/activate/:token", {id: '@_id', token: '@_token'},{
       update:{
           method: 'PUT'
       }
   });
});