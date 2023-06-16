const register_modal = document.querySelector('.register_modal');
const btn_modal = document.querySelectorAll('.btn-modal');
const btn_close = document.querySelector('.btn-close');

const pw_modal = document.querySelector('.share_pw_modal');
const btn_modal_2 = document.querySelectorAll('.btn-modal_2');
const btn_close_2 = document.querySelector('.btn-close_2');

// 신청하기 모달

btn_modal.forEach(open => {
  open.addEventListener('click', () => {
    register_modal.classList.add('active');
  });
});

btn_close.addEventListener('click', () => {
  register_modal.classList.remove('active');
});

// 신청자 비밀번호 모달
btn_modal_2.forEach(open => {
  open.addEventListener('click', () => {
    share_pw_modal.classList.add('active');
  });
});

btn_close_2.addEventListener('click', () => {
  share_pw_modal.classList.remove('active');
});

// // 모달창 밖의 영역 누르면 닫기
// window.onclick = function (event) {
//   if (event.target.className == "modal_1") {
//     event.target.style.display = "none";
//   }
// };
// let isDragging = false;
// let modalOffsetX;
// let modalOffsetY;
// // 모달창 드래그 시작
// modal.addEventListener("mousedown", (event) => {
//   isDragging = true;
//   modalOffsetX = event.offsetX;
//   modalOffsetY = event.offsetY;
// });
// // 모달창 드래그 중
// modal.addEventListener("mousemove", (event) => {
//   if (isDragging) {
//     const x = event.clientX - modalOffsetX;
//     const y = event.clientY - modalOffsetY;
//     modal.style.left = `${x}px`;
//     modal.style.top = `${y}px`;
//   }
// });
// // 모달창 드래그 종료
// modal.addEventListener("mouseup", () => {
//   isDragging = false;
// });