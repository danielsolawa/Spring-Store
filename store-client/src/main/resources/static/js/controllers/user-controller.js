

application.controller('userAdd', ['$location', 'userService', '$http', function($location, userService, $http){
   var self = this;

   self.error = false;
   self.user= {};
   self.errorMessage ="";

   self.register = function(){
       self.error = false;

       if(isValid()){
           userService.save(self.user, function(response){
               $location.path("/users/registered/" + response.username);
           }, function(response){
               self.errorMessage = response.data.message;
               self.error = true;
           });
       }else{
           self.error = true;
       }



   }

    var isValid = function () {
        console.log(self.user.username.length);
        if(self.user.username.length < 8 || self.user.username.length > 30){
            self.errorMessage = "Username should have 8-30 characters"

            return false;
        }else if(self.user.password.length < 8 || self.user.password.length > 30){
            self.errorMessage = "Password should have 8-30 characters"

            return false;
        }

        return true;
    }
}]).controller('userRegistered', ['$routeParams', function($routeParams){
   var self = this;

   self.username = $routeParams.username;
}]);