

application.controller('userAdd', ['$location', 'userService', function($location, userService){
   var self = this;

   self.error = false;
   self.user= {};
   self.errorMessage ="";

   self.register = function(){

       if(isValid()){
            //register logic here
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
}]);