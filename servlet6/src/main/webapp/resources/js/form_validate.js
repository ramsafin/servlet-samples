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