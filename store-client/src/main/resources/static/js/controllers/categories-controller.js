application
    .controller('categories',['categoryService', function(categoryService){
    var self = this;


    var categories = categoryService.get(function(){
        self.categories = categories.categories;
    });

    //categories controller
}])
    .controller('categoriesView', ['$routeParams', 'categoryService', 'productSortService',
        function($routeParams, categoryService, productSortService){
    var self = this;

    var category = categoryService.get({id: $routeParams.id}, function(){
        self.category = category;
        self.products = productSortService.sort(self.category.products);
    });


    //categoriesView controller
}]);