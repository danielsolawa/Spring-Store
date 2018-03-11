application
    .controller('categories',['categoryService', function(categoryService){
    var self = this;


    var categories = categoryService.get(function(){
        self.categories = categories.categories;
    });

    //categories controller
}])
    .controller('categoriesView', ['$transition$', 'productsService', 'productSortService', 'paginationService',
        function($transition$, productsService, productSortService, paginationService){
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

    self.changePage = function(value){
        var destinationPage = parseInt(self.page);

        return destinationPage + value;
    }


    self.setActivePage = function(page){
        self.activePage = page;
    }

    self.isActive = function(page){
        return self.page == page;
    }


    var setUpPagination = function(amount, size, activePage){
        self.pagination = paginationService.generatePagination(amount, size);

        self.minPagination = paginationService.getMinimizedPagination(self.pagination, activePage, 5);

    }



    //categoriesView controller
}]);