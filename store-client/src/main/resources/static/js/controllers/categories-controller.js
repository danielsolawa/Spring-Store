application
    .controller('categories',['categoryService', function(categoryService){
    var self = this;


    var categories = categoryService.get(function(){
        self.categories = categories.categories;
    });

    //categories controller
}])
    .controller('categoriesView', ['$routeParams', 'categoryService', function($routeParams, categoryService){
    var self = this;

    var category = categoryService.get({id: $routeParams.id}, function(){
        self.category = category;
    });


    //categoriesView controller
}]);