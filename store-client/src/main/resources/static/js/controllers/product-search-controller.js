application.controller('productSearchController', function($routeParams, productSearchService, productSortService){
    var self = this;

    self.loadData = function(){
        self.keyword = $routeParams.keyword;
        productSearchService.get({keyword: self.keyword}, function(response){
            self.products = productSortService.sort(response.products);
        }, function(error){
            console.log("an error has occurred " + "\n" + error);
        });
    }



});