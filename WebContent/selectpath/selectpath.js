function changeColor(linkId) {
    const links = document.querySelectorAll('.group-link');
    links.forEach((link) => {
        if (link.id === linkId) {
            link.classList.add('clicked');
        } else {
            link.classList.remove('clicked');
        }
    });
}


const indexsec1 = document.querySelector('.index1')
const indexsec2 = document.querySelector('.index2')
const indexsec3 = document.querySelector('.index3')
const indexlink1 = document.querySelector('#group-1')
const indexlink2 = document.querySelector('#group-2')
const indexlink3 = document.querySelector('#group-3')

indexlink1.addEventListener('click', () => {
    indexsec2.classList.remove('active');
    indexsec3.classList.remove('active');
    indexsec1.classList.add('active');
})

indexlink2.addEventListener('click', () => {
    indexsec1.classList.remove('active');
    indexsec3.classList.remove('active');
    indexsec2.classList.add('active');
})
indexlink3.addEventListener('click', () => {
    indexsec1.classList.remove('active');
    indexsec2.classList.remove('active');
    indexsec3.classList.add('active');
})

function showGroup(groupNumber) {
    const sliderElement = document.querySelector(".slider");

    // 선택된 그룹에 따라 슬라이드 위치 결정
    switch (groupNumber) {
        case 1:
            sliderElement.style.transform = "translateX(0%)";
            break;
        case 2:
            sliderElement.style.transform = "translateX(-100%)";
            break;
        case 3:
            sliderElement.style.transform = "translateX(-200%)";
            break;
    }
}

// 페이지 로딩 완료 시 indexsec1에 'active' 클래스 추가
window.addEventListener('DOMContentLoaded', () => {
    indexsec1.classList.add('active');
});