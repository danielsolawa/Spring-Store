application.controller('ConfirmController',
    ['$scope', '$mdDialog','inventoryService', 'LoginService',
    function($scope, $mdDialog, inventoryService, LoginService){

    $scope.inventory = inventoryService.getSortedInventory();


    $scope.accept = function(answer){
        inventoryService.updateInventory(function () {
            console.log("updated");
        });
        $mdDialog.hide(answer);
    }

    $scope.cancel = function () {
        $mdDialog.cancel();
    }


}]);