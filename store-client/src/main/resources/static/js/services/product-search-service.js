application.factory('productSearchService', function($resource){
   return $resource('product-search/:keyword', {keyword: '@_keyword'});
});