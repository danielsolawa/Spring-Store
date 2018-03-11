application.factory('paginationService', function(){

    var generatePagination = function(amount, size){
        var numberOfPages = countPages(amount, size);
        var pagination = {numberOfPages: numberOfPages, size: size, pages: []};

        for(var i = 0; i < numberOfPages; i++){
            pagination.pages.push(i);
        }

        return pagination;
    }

    var getMinimizedPagination = function(pagination, page, pageLimit){
        var minimizedPagination = {numbersOfPages: pagination.numberOfPages,
            size: pagination.size, pages: []};

        var currentPage = parseInt(page);


        var firstPage = 0;
        var lastPage = pagination.pages[pagination.pages.length - 1];

        switch(currentPage){
            case firstPage:
                var actual = currentPage + (pageLimit - 1);
                var max = lastPage;

                var pagesToAdd = actual > max ?(max - currentPage) : actual;

                for(var i = firstPage; i <= pagesToAdd; i++){
                    minimizedPagination.pages.push(i);
                }

                break;
            case lastPage:
                var actual = firstPage + (pageLimit - 1);
                var max =  currentPage;
                var pagesToAdd = max < actual ? (currentPage - max) : (currentPage - actual);

                for(var i = pagesToAdd; i <= lastPage; i++){
                    minimizedPagination.pages.push(i);
                }

                break;
            default:
                var n = Math.floor(pageLimit / 2);
                var leftSide = firstPage > (currentPage - n) ? firstPage : (currentPage - n);
                var rightSide = lastPage < (currentPage + n) ? lastPage : (currentPage + n);

                for(var i = leftSide; i <= rightSide; i++ ){
                    minimizedPagination.pages.push(i);
                }

                break;

        }

        return minimizedPagination;


    }


    var countPages = function(amount, size){
        var number = amount/size;


        return Math.ceil(number);
    }

    return{
        generatePagination: generatePagination,
        getMinimizedPagination: getMinimizedPagination
    }
});