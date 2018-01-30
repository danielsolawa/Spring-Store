application.controller('home',['$http', 'categoryService', function($http, categoryService){
    var self = this;


    var categories = categoryService.get(function(){
        self.categories = categories.categories;
    });



}]);