function toggle() {
    let button = document.querySelector('.button');
    let extra = document.querySelector('#extra');

    if (extra.style.display === 'none' || extra.style.display === '') {
        extra.style.display = 'block';
        button.textContent = 'Less';
    } else if (extra.style.display === 'block') {
        extra.style.display = 'none';
        button.textContent = 'More';
    }
}