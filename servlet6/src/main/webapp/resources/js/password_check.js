$(document).ready(function(){

    $('#password').blur(function(){

        var pass = $(this).val();
        if(pass.length <= 4 || pass.length > 24){
            $('#pass_msg').find('p').css("color","red").text('bad password');
        }else{
            $('#pass_msg').find('p').css("color","green").text('good password');
        }
        return false;
    })
});