application.controller('addressController', function($location, addressService, LoginService){
    var self = this;
    self.address = {};


    self.saveAddress = function(){
        var userId = LoginService.getCurrentUser().id;

        addressService.save({id: userId}, self.address, function(response){
          $location.path("/inventory");
        }, function(error){

        });
    }
});