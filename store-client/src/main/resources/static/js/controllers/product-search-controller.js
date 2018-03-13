application.controller('productSearchController',
    function($stateParams, productSearchService, productSortService, paginationService){
    var self = this;

    var size = 5;

    self.loadData = function(){
        self.word = $stateParams.word;
        self.page = $stateParams.page;


        productSearchService.get({word: self.word, page: self.page, size: size}, function(response){
            self.products = productSortService.sort(response.products);
            setUpPagination(response.amount, size, self.page);
        }, function(error){
            console.log("an error has occurred " + "\n" + error);
            console.log(error);
        });
        }


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
        self.url = {paramSize: 2, keys: ['/search/', '/page/'], values:[self.word]};
        self.amount = amount;
        self.size = size;
        self.page = activePage;
    }



});