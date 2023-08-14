"use strict";
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
          pathInput.disabled = false;
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

document.querySelectorAll('button').forEach(function(button) {
    button.addEventListener('click', function(event) {
        if (!event.currentTarget.classList.contains('bxs-pencil') &&
            !event.currentTarget.classList.contains('button-x-image')) {
            localStorage.removeItem('scrollPosition');
        }
    });
});

