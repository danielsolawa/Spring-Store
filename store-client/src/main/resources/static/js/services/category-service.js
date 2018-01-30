application.factory('categoryService', function($resource){

    return $resource('categories/:id');


  /*  var categoryList = function(){
        return $http({
            method: 'GET',
            url: '/categories'
        });
    }


    return{
        getCategoryList : categoryList
    }
    */
    
});