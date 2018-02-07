application.controller('adminPanel',
    ['categoryService', 'userService', 'productsService',  function (categoryService, userService, productsService) {
   var self = this;

   self.error = false;
   self.errorMessage = "";
   self.users = false;
   self.categories = false;
   self.products = false;
   self.addingProduct = false;




   self.userData = [];
   self.categoryData = [];
   self.productData = [];

   self.category = {};
   self.product = {};

   self.toggle = function(val, index){
       disableAll();
       switch(val){
           case 'users':
              self.users = true;
              userService.get(function(response){
                 self.userData = response.users;
                 setUpUsersEdit();
              });
              break;
           case 'categories':
              self.categories = true;
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
       }

   }




    var disableAll = function(){
        self.users = false;
        self.categories = false;
        self.products = false;
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




}]);