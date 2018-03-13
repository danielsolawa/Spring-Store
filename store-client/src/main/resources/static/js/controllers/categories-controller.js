application
    .controller('categories',[ 'categoryService', function(categoryService){
        var self = this;


        self.loadData = function(){
            categoryService.get(function(response){
                self.categories = response.categories;

            }, function(error){
                console.log("an error has occurred.")
        });
    }


    //categories controller
}])
    .controller('categoriesView', ['$transition$', 'productsService', 'productSortService', 'paginationService',
        function($transition$,  productsService, productSortService, paginationService){
        var self = this;

        var size = 5;


            self.loadData = function(){
                self.page = $transition$.params().page;
                self.categoryId = $transition$.params().categoryId;

                productsService.get({id: self.categoryId, page: self.page, size: size}, function(response){

                    self.category = response.products[0].category;
                    self.products = productSortService.sort(response.products);
                    setUpPagination(response.amount, size, self.page);
                }, function(error){
                    console.log("an error has occurred.")
                });

             }



            var setUpPagination = function(amount, size, activePage){
                self.url = {paramSize: 2, keys: ['/categories/', '/page/'], values:[self.categoryId]};
                self.amount = amount;
                self.size = size;
                self.page = activePage;
            }



        //categoriesView controller
}]);