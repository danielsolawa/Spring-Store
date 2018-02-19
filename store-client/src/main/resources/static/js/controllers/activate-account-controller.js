application.controller('activateAccountController',['$routeParams', 'activateAccountService',
    function($routeParams, activateAccountService){
    var self = this;

    self.checkToken = function(){
        console.log("checking!!");
        var username = $routeParams.username;
        var token = $routeParams.token;

        activateAccountService.get({username: username, token: token}, function(){
            self.message = "Account successfully activated!";
        }, function(error){
            console.log("an error has occurred " + error);
        })
    }
}]);