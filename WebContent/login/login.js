document.addEventListener('DOMContentLoaded', () => {
    // 오류 메시지가 있는지 확인
    var joinNicknameError = document.querySelector('#joinNicknameError').textContent;
    var joinIdError = document.querySelector('#joinIdError').textContent;
    var joinPasswordError = document.querySelector('#joinPasswordError').textContent;

    const loginsec = document.querySelector('.login-section');
    const loginlink = document.querySelector('.login-link');
    const registerlink = document.querySelector('.register-link');

    // 만약 오류 메시지가 있으면 .login-section에 active 클래스 추가
    if (joinNicknameError || joinIdError || joinPasswordError) {
        loginsec.classList.add('active');
    }

    registerlink.addEventListener('click', (event) => {
        event.preventDefault(); // 기본 이벤트 (페이지 이동 등)를 막기 위함
        loginsec.classList.add('active');
    });

    loginlink.addEventListener('click', (event) => {
        event.preventDefault(); // 기본 이벤트 (페이지 이동 등)를 막기 위함
        loginsec.classList.remove('active');
    });
});




window.onload = function() {
    var idValue = getCookie("remember");
    if (idValue) {
        document.getElementById("inputId").value = idValue;
        document.getElementById("rememberCheck").checked = true;
    }

    document.getElementById("loginButton").addEventListener("click", function() {
        if(!document.getElementById("rememberCheck").checked) {
            deleteCookie("remember");
        }
    });
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


