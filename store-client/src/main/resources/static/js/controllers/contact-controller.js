application.controller('userContactController', function($routeParams, contactService, dateService){
    var self = this;

    self.write = false;

    self.loadData = function(){
        contactService.getResource().get({id: 'all', userId: $routeParams.id}, function(response){
            self.messages = contactService.sortByConversations(response.contacts);
        }, function(error){
            console.log("an error has occurred");
        });
    }

    self.sendMessage = function(){
        var message = {subject: self.message.subject, content: self.message.content, userId: $routeParams.id};
        contactService.getResource().save(message, function(response){
            self.loadData();
            self.write = false;
        }, function(error){
            console.log("an error has occurred.");
        })
    }


    self.formatDate = function(date){
        return dateService.formatDate(date);
    }
});