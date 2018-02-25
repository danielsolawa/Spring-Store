application.controller('userProfile', function($routeParams, $location,  addressService, LoginService){
   var self = this;
   var userId = $routeParams.id;
   self.addressExist = false;

    addressService.get({id: userId}, function(response){
        self.addressExist = true;
        self.address = response;
    }, function(error){
        console.log("an error has occurred");
        self.addressExist = false;
    });


    self.addAddress = function(){
        $location.path("/users/" + userId + "/address/add/profile");
    }


    self.getUser = function(){
        return LoginService.getCurrentUser();
    }


});