document.querySelectorAll('.bxs-pencil').forEach(function(button) {
    button.addEventListener('click', function(event) {
        const pathNo = event.currentTarget.getAttribute('data-pathno');

        // '나의경로' 텍스트 숨기기
        document.getElementById('pathText' + pathNo).style.display = 'none';

        // 텍스트 필드 보이게 하기
        const pathInput = document.getElementById('pathNameInput' + pathNo);
        pathInput.style.display = 'inline-block';
        pathInput.focus();
    });
});
