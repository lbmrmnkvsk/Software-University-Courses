function extract(content) {
    let targetElement = document.getElementById(content);
    let matches = targetElement.textContent.match(/\(([^)]+)\)/g);

    if (matches.length > 0) {
        return matches.join("; ");
    } else {
        return null;
    }
}