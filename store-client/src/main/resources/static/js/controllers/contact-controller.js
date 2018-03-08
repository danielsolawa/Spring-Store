application.controller('userContactController', function($transition$, contactService, dateService){
    var self = this;

    self.userId = $transition$.params().id;
    self.write = false;

    self.loadData = function(){
        contactService.getResource().get({id: 'all', userId: self.userId}, function(response){
            self.messages = contactService.sortByConversations(response.contacts);
        }, function(error){
            console.log("an error has occurred");
        });
    }

    self.sendMessage = function(){
        var message = {subject: self.message.subject, content: self.message.content, userId: self.userId};
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
}).controller('userConversationController', function($transition$, contactService, dateService){
    var self = this;


    self.converationId = $transition$.params().conversationId;

    self.loadData = function(){
        contactService.getResource().get({id: self.converationId}, function(response){
            self.conversations = response.contacts;
            self.messageTemplate = {userId: self.conversations[0].userId,
                subject: self.conversations[0].subject,
                conversationId: self.conversations[0].conversationId};
            window.setTimeout(scrollToBottom, 1000);
        }, function(error){
           console.log("an error has occurred.");
        });
    }

    self.formatDate = function(date){
        return dateService.formatDate(date);
    }

    self.respond = function(){
        self.messageTemplate.content = self.message.content;
        contactService.getResource().save({id: self.messageTemplate.conversationId}, self.messageTemplate,
            function(response){
            self.loadData();
        }, function(error){
               console.log("an error has occurred.");
            });
    }

    var scrollToBottom = function(){
        window.scrollTo(0,document.body.scrollHeight);
    }

});