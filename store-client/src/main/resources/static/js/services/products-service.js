application.factory('productsService', function($resource){

    return $resource('categories/:id/products/:prodId', { id: '@_id', prodId: '@_prodId' }, {
        update: {
            method: 'PUT'
        }
    });

});