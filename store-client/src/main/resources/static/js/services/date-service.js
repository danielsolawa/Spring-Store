application.factory('dateService', function(){

    var formatDate = function(date){

        var hour = date.hour < 10 ?  "0" + date.hour : date.hour;
        var minute = date.minute < 10 ?  "0" + date.minute : date.minute;
        var second = date.second < 10 ?  "0" + date.second : date.second;

       return  hour + ":" + minute + ":" + second + " " + date.month + " " + date.dayOfMonth + " " + date.year;
    }

    return{
        formatDate: formatDate
    }
});