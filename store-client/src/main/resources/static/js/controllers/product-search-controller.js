application.controller('productSearchController', function($routeParams, productSearchService){
    var self = this;

    self.loadData = function(){
        var key = $routeParams.keyword;
        productSearchService.get({keyword: key}, function(response){

            sortProducts(response.products);
        }, function(error){
            console.log("an error has occurred " + "\n" + error);
        });
    }


    var sortProducts = function(products){
        for(var i = 0; i < products.length; i++){
            var descriptionLength = products[i].description.length;
            if(descriptionLength > 100){
                var tempDescrittion = products[i].description.slice(0, 100);
                products[i].description = tempDescrittion + "...";
            }
        }

        self.products = products;
    }
});