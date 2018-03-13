application.directive('storePagination', function (paginationService) {

    var controller = ['$scope', function ($scope) {

            $scope.init = function(){
                var pagination = paginationService.generatePagination($scope.amount, $scope.size);
                $scope.minPagination = paginationService
                    .getMinimizedPagination(pagination, $scope.page, $scope.pageLimit);
            }

            $scope.changePage = function(value){
                var destinationPage = parseInt($scope.page);

                return destinationPage + value;
            }

            $scope.setActivePage = function(page){
                $scope.activePage = page;
            }

            $scope.isActive = function(page){
                return $scope.page == page;
            }

            $scope.getUrl = function(currentValue){
                var keys = $scope.url.keys;
                var values = $scope.url.values;
                var paramSize = $scope.url.paramSize;

                if(paramSize == 1){
                    return "/#!" +  keys[0] + currentValue;
                }


                return "/#!" + keys[0] + values[0] + keys[1] + currentValue;

            }


        }];

    return {
        restrict: 'EA',
        scope: {
            url: '=',
            amount: '=',
            size: '=',
            page: '=',
            pageLimit: '='
        },
        controller: controller,
        templateUrl: 'store-pagination.html'
    };
});