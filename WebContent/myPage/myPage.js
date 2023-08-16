document.querySelectorAll('.bxs-pencil').forEach(function(button) {
    button.addEventListener('click', function(event) {
        event.preventDefault();

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


