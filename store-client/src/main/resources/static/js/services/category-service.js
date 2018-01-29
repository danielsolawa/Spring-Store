application.factory('categoryService', function($http){
    
    var categoryList = function(){
        return $http({
            method: 'GET',
            url: '/categories'
        });
    }


    return{
        getCategoryList : categoryList
    }
    
    
});