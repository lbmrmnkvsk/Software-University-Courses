function lockedProfile() {
    let buttons = document.querySelectorAll('.profile button');
    for (let button of buttons) {
        button.addEventListener('click', onClick);
    }

    function onClick(e) {
        let button = e.currentTarget;
        let profile = button.parentNode;
        let isLocked = profile.querySelector("input[type='radio'][value='lock']").checked;
        let content = profile.querySelector('div');

        if (button.textContent === 'Show more') {
            if (!isLocked) {
                content.style.display = 'block';
                button.textContent = 'Hide it';
            }
        } else if (button.textContent === 'Hide it') {
            if (!isLocked) {
                content.style.display = 'none';
                button.textContent = 'Show more';
            }
        }
    }
}