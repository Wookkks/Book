// modal
const modal = document.querySelector('.modal');
const btn_modal = document.querySelector('.btn_modal');
const btn_close = document.querySelector('.btn_close');

btn_modal.addEventListener('click', () => {
    modal.classList.add('active');
    btn_close.classList.add('active');
});

btn_close.addEventListener('click', () => {
    modal.classList.remove('active');
    btn_close.classList.remove('active');
});

function checkPwd() {
	console.log("checkPwd실행");
    var pwd = document.getElementById("managePwd").value;
    if (pwd != 1111){
      alert("관리자 패스워드가 일치하지 않습니다");
      return false;
    }
    return true;
}