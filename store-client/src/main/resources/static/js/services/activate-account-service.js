application.factory('activateAccountService', function($resource){
   return $resource("users/:username/activate/:token", {username: '@_username', token: '@_token'},{
       update:{
           method: 'PUT'
       }
   });
});