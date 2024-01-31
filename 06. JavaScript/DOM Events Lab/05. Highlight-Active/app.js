function focused() {
    let inputFields = document.querySelectorAll('input[type=text]');
    for (let field of inputFields) {
        field.addEventListener("focus", onFocus);
        field.addEventListener("blur", onBlur);
    }

    function onFocus(e) {
        let divElement = e.currentTarget.parentNode;
        divElement.classList.add("focused");
    }

    function onBlur(e) {
        let divElement = e.currentTarget.parentNode;
        divElement.classList.remove("focused");
    }
}