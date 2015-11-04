
$( document ).ready(function() {


    $('#scrollup').find('img').mouseover( function(){
        $( this ).animate({opacity: 0.50},{},100);
    }).mouseout( function(){
        $( this ).animate({opacity: 1},100);
    }).click( function(){
        $("html,body").animate({scrollTop:0},"slow");
        return false;
    });


    $('#scrolldown').find('img').mouseover( function(){
        $( this ).animate({opacity: 0.50},100);
    }).mouseout( function(){
        $( this ).animate({opacity: 1},100);
    }).click( function(){
        $("html,body").animate({scrollTop:$(document).height()},"slow");
        return false;
    });


    //scrolling func-s
    $(window).scroll(function(){
        if ( $(document).scrollTop() > 0 ) {
            $('#scrollup').stop().fadeIn('slow');
        } else {
            $('#scrollup').stop().fadeOut('slow');
        }

        if ( $(document).scrollTop() > 0 && $(document).scrollTop() < $(document).height()-760 ) {
            $('#scrolldown').stop().fadeIn('slow');
        } else {
            $('#scrolldown').stop().fadeOut('slow');
        }
    });
});

