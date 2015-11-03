function validate_form(){

    var reg = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;
    if(!reg.test(document.reg_form.email.value)){
        alert("Некорректный email! Введите еще раз!")
        return false;
    }

    var inp = document.getElementsByName('sex');
    if (!inp[0].checked && !inp[1].checked) {
        alert("Выберите свой пол!!!");
        return false;
    }

    return true;
}

//function validate_form(){
//
//    var error = true;
//
//    var regExp = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;
//
//    alert(($('#reg_form').find('#email').value()));
//
//    if(!regExp.test($('#reg_form').find('#email').value())){
//        alert("Некорректный email! Введите еще раз!");
//        error = false;
//    }
//
//    if($("#radio1").prop("checked")){
//        alert('radio1');
//    }
//
//    if ($("#radio1").prop("checked") && $("#radio2").prop("checked")) {
//        alert("Выберите свой пол!!!");
//        error = false;
//    }
//
//    return error;
//
//}