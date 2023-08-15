$(document).ready(function() {
	var changeNicknameError = $("#errorData").data("hasChangeNicknameError");
	var failCheckPassword = $("#errorData").data("hasFailCheckPassword");

	if (changeNicknameError || failCheckPassword) {
		$("#inputChangeNickname").show();
	}

	$("#NicknameButton").click(function(event) {
		if ($("#inputChangeNickname").is(":visible")) {
			$("#inputChangeNickname").hide();
		} else {
			$("#inputChangeNickname").show();
			$("#inputChangePassword").hide();
		}
	});
});


$(document).ready(function() {
	var failCheckPasswordChange = $("#errorData").data("hasFailCheckPasswordChange");
	var passwordInputError = $("#errorData").data("hasPasswordInputError");

	if (failCheckPasswordChange || passwordInputError) {
		$("#inputChangePassword").show();
	}

	$("#passwordButton").click(function(event) {
		if ($("#inputChangePassword").is(":visible")) {
			$("#inputChangePassword").hide();
		} else {
			$("#inputChangeNickname").hide();
			$("#inputChangePassword").show();
		}
	});
});

document.getElementById('photoset-Button').addEventListener('click', function(e) {
	e.preventDefault(); // 기본 버튼 동작 중지
	document.getElementById('fileInput').click(); // 파일 입력 필드 트리거
});

// 선택한 파일이 있다면 폼을 자동으로 제출
document.getElementById('fileInput').addEventListener('change', function() {
	if (this.files.length > 0) {
		this.form.submit();
	}
});

document.getElementById('fileInput').addEventListener('change', function() {
    if (this.files.length > 0) {
        var reader = new FileReader();

        reader.onload = function(e) {
            // 미리보기 이미지를 로컬에서 표시
			// 현재의 타임스탬프를 추가하여 캐시 방지
            var newSrc = e.target.result + "#" + new Date().getTime();
            document.querySelector('.fixed-size-image').src = newSrc;
        };
        reader.readAsDataURL(this.files[0]);
    }
});

function redirectToLogout() {
        window.location.href = "http://localhost:8080/ScreenSceneP/logout";
    }
    
    
    document.addEventListener('DOMContentLoaded', function() {
    const errorData = document.getElementById('errorData');
    
    let errorMessage = '';
    if (errorData.getAttribute('data-has-change-nickname-error') === 'true') {
        errorMessage += '동일한 이름의 닉네임이 존재합니다.\n';
    }
    if (errorData.getAttribute('data-has-fail-check-password') === 'true') {
        errorMessage += '비밀번호를 잘못 입력하셨습니다.\n';
    }
    if (errorData.getAttribute('data-has-fail-check-password-change') === 'true') {
        errorMessage += '비밀번호를 잘못 입력하셨습니다.\n';
    }
    if (errorData.getAttribute('data-has-password-input-error') === 'true') {
        errorMessage += '변경할 비밀번호를 서로 다르게 입력하셨습니다.\n';
    }

    // 에러 메시지가 있으면 알림창을 표시합니다.
    if (errorMessage) {
        alert(errorMessage);
    }
});
    