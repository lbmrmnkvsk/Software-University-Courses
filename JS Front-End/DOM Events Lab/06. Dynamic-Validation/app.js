function validate() {
    let inputField = document.getElementById("email");
    inputField.addEventListener("change", onChange);

    function onChange(e) {
        let element = e.currentTarget;
        let pattern = /[a-z]+@[a-z]+\.[a-z]+/gm;

        if (pattern.test(element.value)) {
            element.classList.remove("error");
        } else {
            element.classList.add("error");
        }
    }
}