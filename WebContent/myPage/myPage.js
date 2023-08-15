document.querySelectorAll('.bxs-pencil').forEach(function(button) {
	button.addEventListener('click', function(event) {
		event.preventDefault();

		localStorage.setItem('scrollPosition', window.scrollY);

		const pathNo = event.currentTarget.getAttribute('data-pathno');
		const pathInput = document.getElementById('pathNameInput' + pathNo);

		if (pathInput.style.display === 'inline-block') {
			document.getElementById('pathForm' + pathNo).submit();
		} else {
			document.getElementById('pathText' + pathNo).style.display = 'none';
			pathInput.style.display = 'inline-block';
			pathInput.focus();
		}
	});
});

document.querySelectorAll('.button-x-image').forEach(function(button) {
	button.addEventListener('click', function(event) {
		event.preventDefault();

		localStorage.setItem('scrollPosition', window.scrollY);
	})
});


window.addEventListener('load', function() {
	const savedScrollPosition = localStorage.getItem('scrollPosition');

	if (savedScrollPosition) {
		window.scrollTo(0, parseInt(savedScrollPosition, 10));
	}
});

window.addEventListener('beforeunload', function() {
	localStorage.removeItem('scrollPosition');
});

document.querySelectorAll('button:not(.bxs-pencil):not(.button-x-image)').forEach(function(button) {
	button.addEventListener('click', function() {
		localStorage.removeItem('scrollPosition');
	});
});