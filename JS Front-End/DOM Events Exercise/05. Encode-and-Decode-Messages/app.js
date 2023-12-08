function encodeAndDecodeMessages() {
    let writeArea = document.querySelectorAll('textarea')[0];
    let readArea = document.querySelectorAll('textarea')[1];
    let encodeSendBtn = document.querySelectorAll('button')[0];
    let decodeReadBtn = document.querySelectorAll('button')[1];

    encodeSendBtn.addEventListener('click', encode);
    decodeReadBtn.addEventListener('click', decode);

    function encode(e) {
        let message = writeArea.value;
        let encodedMessage = "";
        for (let i = 0; i < message.length; i++) {
            let charCode = message.charCodeAt(i);
            encodedMessage += String.fromCharCode(charCode + 1);
        }
        readArea.value = encodedMessage;
        writeArea.value = '';
    }

    function decode(e) {
        let message = readArea.value;
        let decodedMessage = '';
        for (let i = 0; i < message.length; i++) {
            let charCode = message.charCodeAt(i);
            decodedMessage += String.fromCharCode(charCode - 1);
        }
        readArea.value = decodedMessage;
    }
}