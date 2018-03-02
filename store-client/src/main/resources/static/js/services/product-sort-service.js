application.factory('productSortService', function(){

    var sortProducts = function(products){
        for(var i = 0; i < products.length; i++){
            var descriptionLength = products[i].description.length;
            if(descriptionLength > 100){
                var tempDescrittion = products[i].description.slice(0, 100);
                products[i].description = tempDescrittion + "...";
            }
        }

        return products;
    }



    return{
        sort: sortProducts
    }
});