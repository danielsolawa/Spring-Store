application.controller('adminPanel',
    ['categoryService', 'userService', 'productsService', function (categoryService, userService) {
   var self = this;

   self.users = false;
   self.categories = false;
   self.products = false;

   self.userData = [];
   self.categoryData = [];
   self.productData = [];

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
              });
              break;
           case 'products':
              self.products = true;
              categoryService.get({id: index}, function(response){
                  self.productData = response;
              });
       }

   }


   self.toggleProducts = function(index){
     console.log('index ' + index);
   };


   var disableAll = function(){
       self.users = false;
       self.categories = false;
       self.products = false;
   }

}]);