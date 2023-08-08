$(document).ready(function() {
	var changeNicknameError = $("#errorData").data("hasChangeNicknameError");
	var failCheckPassword = $("#errorData").data("hasFailCheckPassword");

	if (changeNicknameError || failCheckPassword) {
		$("#inputChangeNickname").show();
	}

	$("#NicknameButton").click(function(event) {
		$("#inputChangeNickname").show();
		$("#inputChangePassword").hide();
	});
});


$(document).ready(function() {
	var failCheckPasswordChange = $("#errorData").data("hasFailCheckPasswordChange");
	var passwordInputError = $("#errorData").data("hasPasswordInputError");

	if (failCheckPasswordChange || passwordInputError) {
		$("#inputChangePassword").show();
	}

	$("#passwordButton").click(function(event) {
		$("#inputChangeNickname").hide();
		$("#inputChangePassword").show();
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
		var formData = new FormData();
		formData.append('uploaded_file', this.files[0]);
		formData.append('form_type', 'profilImg');

		var xhr = new XMLHttpRequest();
		xhr.open('POST', '서버의_업로드_URL', true);

		xhr.onload = function() {
			if (xhr.status === 200) {
				var imageUrl = JSON.parse(xhr.responseText).imageUrl;
				// 이미지 태그에 URL 설정
				document.getElementById('yourImageTagId').src = imageUrl;
			} else {
				console.error('업로드 중 문제 발생:', xhr.responseText);
			}
		};

		xhr.send(formData);
	}
});
