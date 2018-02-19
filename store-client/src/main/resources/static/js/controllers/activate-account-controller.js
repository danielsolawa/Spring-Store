application.controller('activateAccountController',['$routeParams', 'activateAccountService',
    function($routeParams, activateAccountService){
    var self = this;

    self.checkToken = function(){
        console.log("checking!!");
        var userId = $routeParams.id;
        var token = $routeParams.token;

        activateAccountService.get({id: userId, token: token}, function(){
            self.message = "Account successfully activated!";
        }, function(error){
            console.log("an error has occurred " + error);
        })
    }
}]);