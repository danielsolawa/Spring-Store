application.controller('activateAccountController',['$transition$', '$location','activateAccountService',
    function($transition$, $location,activateAccountService){
    var self = this;

    self.sendNewToken = false;
    self.tokenExpired = false;

    self.checkToken = function(){
        self.message = "";
        self.error = false;
        self.username = $transition$.params().username;
        var token = $transition$.params().token;

        activateAccountService.get({username: self.username, token: token}, function(){
            self.message = "Account successfully activated!";
        }, function(error){
            self.message = error.data.message;
            self.error = true;

            if(error.status == 410){
                self.tokenExpired = true;
            }

        });
    }

    self.createNewToken = function(){
        self.sendNewToken = true;

        activateAccountService.get({username: self.username}, function(){
            self.newTokenMessage = "An email with activation token has been sent to your email " + self.username;
        }, function(error){
           console.log(error);
        });

    }


}]);