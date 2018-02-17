application.factory('InventoryResource', function($resource){
    return $resource('users/:id/inventory', { id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });
});