application.controller('home', 'categoryService', function($http, categoryService){
    var self = this;
    

    categoryService.getCategoryList().then(function(response) {
        self.categories = response.data.categories;
    }, function() {
        console.log('an error has occured');
    });

});