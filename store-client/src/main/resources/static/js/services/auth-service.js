application.factory('AuthService', function($rootScope, $location){
    function getAuthority(){
        console.log($rootScope.user.authorities[0].authority)

    }


    return{
        getAuthority: getAuthority
    };
});