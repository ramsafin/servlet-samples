$(document).ready(function(){

    $("#about").keyup(function(){

        var box=$(this).val();

        var main = box.length *100;
        var value= (main / 50);
        var box_length = box.length;

        if(box_length <= 50) {

            if(box_length <= 40 && box_length >= 30){

                $('#count').css('background','#c69125').css('width',value*2).text(value/2+'/50');

            }else if(box_length > 40){

                $('#count').css('background','#b92117').css('width',value*2).text(value/2+'/50');

            }else{
            //#337ab7
                $('#count').css('width',value*2).text(value/2+'/50').css('background','green');
            }
        }
        return false;
    });
});
