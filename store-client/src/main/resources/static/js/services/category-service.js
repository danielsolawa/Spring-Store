application.factory('categoryService', function($resource){

    return $resource('categories/:id', { id: '@_id' }, {
        update: {
            method: 'PUT'
        }
    });

});