//function validate_form(){
//
//    var reg = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;
//
//    //if(!reg.test(document.reg_form.email.value)){
//    //    alert("Некорректный email! Введите еще раз!")
//    //    return false;
//    //}
//
//    if(!reg.test($('#email').val())){
//        alert("Некорректный email! Введите еще раз!")
//        return false;
//    }
//
//    var inp = document.getElementsByName('sex');
//    if (!inp[0].checked && !inp[1].checked) {
//        alert("Выберите свой пол!!!");
//        return false;
//    }
//
//    return true;
//}

function validate_form(){

    $('document').ready(function(){

        var status = true;

        var regExp = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;

        if(!regExp.test($('#email').val())){
            alert("Некорректный email! Введите другой!");
            status = false;
        }

        if (!$("#radio1").prop("checked") && !$("#radio2").prop("checked")) {
            alert("Выберите свой пол!!!");
            status = false;
        }

        return status;

    });

}