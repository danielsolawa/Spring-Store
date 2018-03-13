application.factory('productSearchService', function($resource){
   return $resource('product-search/:word', {keyword: '@_word'});
});