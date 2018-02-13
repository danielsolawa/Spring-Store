application.service('LoginService', function(store) {
    var self = this,
        currentUser = null,
        wasLogged = false;
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
    self.setWasLogged = function(logged){
        wasLogged = logged;
        store.set('wasLogged', wasLogged);
        return wasLogged;
    };
    self.getWasLogged = function(){
        if(store.get('wasLogged') != null){
            wasLogged = store.get('wasLogged');
        }

        return wasLogged;
    };





});