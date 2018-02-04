application.controller('adminPanel',
    ['categoryService', 'userService', 'productsService',  function (categoryService, userService) {
   var self = this;

   self.error = false;
   self.errorMessage = "";
   self.users = false;
   self.categories = false;
   self.products = false;



   self.userData = [];
   self.categoryData = [];
   self.productData = [];

   self.category = {};

   self.toggle = function(val, index){
       disableAll();
       switch(val){
           case 'users':
              self.users = true;
              userService.get(function(response){
                 self.userData = response.users;
              });
              break;
           case 'categories':
              self.categories = true;
              categoryService.get(function(response){
                  self.categoryData = response.categories;
                  setUpEdit();
              });
              break;
           case 'products':
              self.products = true;
              categoryService.get({id: index}, function(response){
                  self.productData = response;
              });
       }

   }


   self.addCategory = function(){
       self.error = false;

       categoryService.save(self.category, function(response){
          self.toggle('categories', -1);
       }, function(error){
           self.error = true;
           self.errorMessage = error.data.message;
       });
   }

   self.updateCategories = function(index){

       categoryService.update({id: self.categoryData[index].id}, self.categoryData[index], function(response){
           self.toggle('categories', -1);
       }, function (response) {
           console.log("error occured");
       });

   /*   self.category.$update(function () {
         console.log("updated");
      });*/
   }


   self.toggleProducts = function(index){
     console.log('index ' + index);
   };

   self.categoryEdit = function(index, edit){
       self.categoryData[index].edit = edit;
   };

   var setUpEdit = function(){
       for(var i = 0; i < self.categoryData.length; i++){
           self.categoryData[i].edit = false;
          // console.log(self.categoryData[i]);
       }
   }


   var disableAll = function(){
       self.users = false;
       self.categories = false;
       self.products = false;
   }

}]);