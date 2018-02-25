application.controller('addressController', function($location, $routeParams, addressService, LoginService){
    var self = this;
    self.address = {};


    self.saveAddress = function(){
        var userId = LoginService.getCurrentUser().id;

        addressService.save({id: userId}, self.address, function(response){
            var destination = $routeParams.type;
            if(destination == "order"){
                $location.path("/inventory");
            }else if(destination == "profile"){
                $location.path("/users/"+ userId + "/profile");
            }


        }, function(error){

        });
    }
});