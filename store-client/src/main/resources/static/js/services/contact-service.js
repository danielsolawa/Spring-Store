application.factory('contactService', function($resource){

    var getResource = function(){
        return $resource('contacts/:id/users/:userId', {id: "@_id", userId: '@_userId'}, {
            update:{
                method: 'PUT'
            }
        });
    }

    var sortByConversations = function(messages){
        var conversations = [];
        var tempConversation = null;


        for(var i = 0; i < messages.length; i++){
            if(tempConversation == null){
              tempConversation = {conversationId: messages[i].conversationId, amount: 1, date: messages[i].date,
              subject: messages[i].subject};

              continue;
            }


            if(messages[i].conversationId == tempConversation.conversationId){
                tempConversation.amount++;
            }else{
                conversations.push(tempConversation);
                tempConversation = {conversationId: messages[i].conversationId, amount: 1, date: messages[i].date,
                    subject: messages[i].subject};
            }



        }

        conversations.push(tempConversation);


        return conversations;
    }


    var sortByUsers = function(messages){
        var conversations = [];
        var tempConversation = null;

        for(var i = 0; i < messages.length; i++){
            if(tempConversation == null){
                tempConversation = {username: messages[i].users[0].username, userId: messages[i].users[0].id};
                continue;
            }

            if(messages[i].users[0].username != tempConversation.username){
                conversations.push(tempConversation);
                tempConversation = {username: messages[i].users[0].username, userId: messages[i].users[0].id};
            }


        }

        conversations.push(tempConversation);


        return conversations;
    }

    return{
        getResource: getResource,
        sortByConversations: sortByConversations,
        sortByUsers: sortByUsers
    }

});