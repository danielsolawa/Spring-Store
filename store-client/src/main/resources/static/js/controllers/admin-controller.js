application.controller('adminPanel',
    ['categoryService', 'userService', 'productsService' , 'contactService', 'dateService',
        function (categoryService, userService, productsService, contactService, dateService) {
   var self = this;


   self.init = function(){
       self.activeTab = 'users';
       self.error = false;
       self.errorMessage = "";
       self.users = false;
       self.categories = false;
       self.products = false;
       self.addingProduct = false;
       self.conversation = false;
       self.conversationDetail = false;

       self.userData = [];
       self.categoryData = [];
       self.productData = [];

       self.category = {};
       self.product = {};

       self.toggle('users', -1);

   }



   self.toggle = function(val, index){
       disableAll();
       switch(val){
           case 'users':
              self.users = true;
               self.activeTab = 'users';
              userService.get(function(response){
                 self.userData = response.users;
                 setUpUsersEdit();
              });
              break;
           case 'categories':
              self.categories = true;
              self.activeTab = 'categories';
              categoryService.get(function(response){
                  self.categoryData = response.categories;
                  setUpCategoryEdit();
              });
              break;
           case 'products':
              self.products = true;
              categoryService.get({id: index}, function(response){
                  self.productData = response;
                  setUpProductEdit();
              });
              break;
           case 'messages':
               self.messages = true;
               self.activeTab = 'messages';
               contactService.getResource().get(function(response){
                  self.messageData = contactService.sortByUsers(response.contacts);
               });
               break;
           case 'conversations':
               self.messages = true;
               self.conversation = true;
               contactService.getResource().get({id: 'non', userId: index}, function(response){
                   self.conversationData = contactService.sortByConversations(response.contacts);
               });
               break;
           case 'conversationDetail':
               self.messages = true;
               self.conversationDetail = true;
               contactService.getResource().get({id: index}, function(response){
                   self.conversationDetailData = response.contacts;
                   self.messageTemplate = {userId: self.conversationDetailData[0].userId,
                       subject: self.conversationDetailData[0].subject,
                       conversationId: self.conversationDetailData[0].conversationId};
                   window.setTimeout(scrollToBottom, 1000);


               });
               break;


       }

   }

   self.formatDate = function(date){
       return dateService.formatDate(date);
   }

   self.isActive = function(tab){
       return self.activeTab == tab;
   }

   var scrollToBottom = function(){
       window.scrollTo(0,document.body.scrollHeight);
   }

    var disableAll = function(){
        self.users = false;
        self.categories = false;
        self.products = false;
        self.messages = false;
        self.conversation = false;
        self.conversationDetail = false;
    }




   /*
    *
    * CATEGORIES
    */

   self.addCategory = function(){
       self.error = false;

       categoryService.save(self.category, function(response){
          self.toggle('categories', -1);
       }, function(error){
           self.error = true;
           self.errorMessage = error.data.message;
       });
   }




   self.deleteCategory = function(categoryId){

     categoryService.delete({id: categoryId}, function(response){
        console.log("successfully deleted");
         self.toggle('categories', -1);
     }, function(error){
         console.log("error has occurred")
     });

   }

   self.updateCategories = function(index){

       categoryService.update({id: self.categoryData[index].id}, self.categoryData[index], function(response){
           self.toggle('categories', -1);
       }, function (response) {
           console.log("error occured");
       });

   }


   self.toggleProducts = function(index){
     console.log('index ' + index);
   };

        self.categoryEdit = function(index, edit){
            self.categoryData[index].edit = edit;
        };

   var setUpCategoryEdit = function(){
       for(var i = 0; i < self.categoryData.length; i++){
           self.categoryData[i].edit = false;

       }
   }


   /*
    *
    * Products
    */



    function setUpProductEdit() {
        for(var i = 0; i < self.productData.products.length; i++){
            self.productData.products[i].edit = false;
        }
    }

   self.addProduct = function(categoryId){
       productsService.save({id: categoryId}, self.product, function(response){
           self.product = {};
           self.addingProduct = false;
           self.toggle('products', categoryId);
       }, function(error){
           console.log("error");
       });
   }

   self.deleteProduct = function(categoryId, productId){
       productsService.delete({id: categoryId, prodId: productId}, function(response){
           console.log(response);
           self.toggle('products', categoryId);
       }, function(error){
           console.log("error has occurred");
       });
   }

   self.updateProduct = function(categoryId, productId, index){
        productsService.update({id: categoryId, prodId: productId}, self.productData.products[index], function(response){
        console.log("successfully updated");
        self.toggle('products', categoryId);
    }, function(error){
        console.log("error has occurred");
    });
   }



   /*
    * Users
    *
    */

   var setUpUsersEdit = function(){
       for(var i = 0; i <  self.userData.length; i++){
           self.userData[i].edit = false;
       }
   }


   self.updateUser = function(userId, index){
       var userToUpdate = {id: self.userData[index].id, password: self.userData[index].password,
           username: self.userData[index].username, role: self.userData[index].role}

       userService.update({id: userId}, userToUpdate, function(response){
           console.log("successfully updated");
           self.toggle('users', -1);
       }, function(error){
           console.log("an error has occurred");
           console.log(error);
       });

   }


   self.deleteUser = function(userId){
       userService.delete({id: userId}, function(response){
           console.log("deleted successfully");
           self.toggle('users', -1);
       }, function(error){
           console("an error has occurred");
       });

   }

   /*
    *Messages
    *
    */

   self.respond = function(){
       self.messageTemplate.content = self.message.content;
       contactService.getResource().update({id: self.messageTemplate.conversationId}, self.messageTemplate,
           function(response){
           self.toggle('conversationDetail', self.messageTemplate.conversationId);
       }, function(error){
               console.log("an error has occurred.");
           });
   }




}]);