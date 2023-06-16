// 관리자비번 모달
const pw_modal = document.querySelector('.pw_modal');
const m_btn_modal = document.querySelector('.btn_modal');

// 닫기 버튼
const pw_btn_close = document.querySelector('.pw_btn_close');

// 리스너
m_btn_modal.addEventListener('click', () => {
    pw_modal.classList.add('active');
    pw_btn_close.classList.add('active');
});

pw_btn_close.addEventListener('click', () => {
    pw_modal.classList.remove('active');
    pw_btn_close.classList.remove('active');
});

function checkPwd() {
  var pwd = document.getElementById("pwd").value;
  if (pwd != 1111){
    alert("패스워드가 일치하지 않습니다");
    return false;
  }
  return true;
}