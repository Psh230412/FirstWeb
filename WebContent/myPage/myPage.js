/**
body js
 */

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.bxs-pencil').forEach(function(button) {
        button.addEventListener('click', function(event) {
            event.preventDefault();

            localStorage.setItem('scrollPosition', window.scrollY);

            const pathNo = event.currentTarget.getAttribute('data-pathno');
            const pathInput = document.getElementById('pathNameInput' + pathNo);

            if (pathInput.style.display === 'inline-block') {
                document.getElementById('pathForm' + pathNo).submit();
            } else {
                // 만약 'pathText' 요소가 존재하지 않는다면, 아래 줄은 오류를 발생시킬 것입니다.
                // 이 부분의 존재 여부를 확인하거나, try-catch로 오류 처리를 추가할 수 있습니다.
                document.getElementById('pathText' + pathNo).style.display = 'none';
                pathInput.style.display = 'inline-block';
                pathInput.focus();
            }
        });
    });
});


document.querySelectorAll('.button-x-image').forEach(function(button) {
	button.addEventListener('click', function(event) {
		event.preventDefault();

		localStorage.setItem('scrollPosition', window.scrollY);
		
		const form = button.closest('form');
        if (form) {
            form.submit();
        }
	})
});


window.addEventListener('load', function() {
	const savedScrollPosition = localStorage.getItem('scrollPosition');

	if (savedScrollPosition) {
		window.scrollTo(0, parseInt(savedScrollPosition, 10));
	}
});

document.querySelectorAll('button:not(.bxs-pencil):not(.button-x-image)').forEach(function(button) {
	button.addEventListener('click', function() {
		localStorage.removeItem('scrollPosition');
	});
});

function redirectToLogout() {
        window.location.href = "http://localhost:8080/ScreenSceneP/logout";
    }