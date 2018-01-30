application.factory('userService', function($resource){

    return $resource('users/:id', { id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

});