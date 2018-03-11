application.controller('productSearchController',
    function($transition$, productSearchService, productSortService, paginationService){
    var self = this;

    var size = 5;

    self.loadData = function(){
        self.keyword = $transition$.params().keyword;
        self.page = $transition$.params().page;


        productSearchService.get({keyword: self.keyword, page: self.page, size: size}, function(response){
            self.products = productSortService.sort(response.products);
            setUpPagination(response.amount, size, self.page);
        }, function(error){
            console.log("an error has occurred " + "\n" + error);
        });

        self.changePage = function(value){
            var destinationPage = parseInt(self.page);

            return destinationPage + value;
        }


        self.setActivePage = function(page){
            self.activePage = page;
        }

        self.isActive = function(page){
            return self.page == page;
        }


        var setUpPagination = function(amount, size, activePage){
            self.pagination = paginationService.generatePagination(amount, size);

            self.minPagination = paginationService.getMinimizedPagination(self.pagination, activePage, 5);

        }
    }



});