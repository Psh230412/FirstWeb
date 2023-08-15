"use strict";

/**
 * navbar variables
 */

const navOpenBtn = document.querySelector("[data-menu-open-btn]");
const navCloseBtn = document.querySelector("[data-menu-close-btn]");
const navbar = document.querySelector("[data-navbar]");
const overlay = document.querySelector("[data-overlay]");

const navElemArr = [navOpenBtn, navCloseBtn, overlay];

for (let i = 0; i < navElemArr.length; i++) {
  navElemArr[i].addEventListener("click", function () {
    navbar.classList.toggle("active");
    overlay.classList.toggle("active");
    document.body.classList.toggle("active");
  });
}

/**
 * header sticky
 */

const header = document.querySelector("[data-header]");

window.addEventListener("scroll", function () {
  window.scrollY >= 10
    ? header.classList.add("active")
    : header.classList.remove("active");
});

/**
 * go top
 */

const goTopBtn = document.querySelector("[data-go-top]");

window.addEventListener("scroll", function () {
  window.scrollY >= 500
    ? goTopBtn.classList.add("active")
    : goTopBtn.classList.remove("active");
});

/**
body js
 */

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