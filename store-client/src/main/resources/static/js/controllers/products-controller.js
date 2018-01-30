application
    .controller('productView',['$routeParams', 'productsService', function($routeParams, productsService){
        var self = this;


        var product = productsService.get({id: $routeParams.id, prodId: $routeParams.prodId},function(){
            self.product = product;
        });

        //categories controller
    }]);