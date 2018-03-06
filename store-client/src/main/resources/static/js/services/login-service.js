application.service('LoginService', function(store) {
    var self = this,
        currentUser = null,
        wasLogged = false,
        redirectUrl = null;

    var protectedUrls = ['inventory.html', 'user-profile.html',
        'user-contact-view.html', 'user-conversation-view.html', 'orders.html'];

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

    self.setRedirectUrl = function(url){
        redirectUrl = url;
        store.set('redirectUrl', redirectUrl);

        return redirectUrl;
    }

    self.getRedirectUrl = function(){
        if(!redirectUrl){
            redirectUrl = store.get('redirectUrl');
        }

        return redirectUrl;
    }

    self.isUrlProtected = function(url){
        for(var i = 0; i < protectedUrls.length; i++){
            if(protectedUrls[i] == url){
                return true;
            }
        }

        return false;
    }





});