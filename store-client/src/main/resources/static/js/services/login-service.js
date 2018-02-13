application.service('LoginService', function(store) {
    var self = this,
        currentUser = null;
    self.setCurrentUser = function(user) {
        currentUser = user;
        store.set('user', user);
        return currentUser;
    };
    self.getCurrentUser = function() {
        if (!currentUser) {
            currentUser = store.get('user');
        }
        return currentUser;
    };


});