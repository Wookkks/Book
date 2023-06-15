// 공지삭제버튼 모달
const remove_modal = document.querySelector('.remove_modal');
const btn_remove = document.querySelectorAll('.btn_remove');

const remove_btn_close = document.querySelector('.remove_btn_close');
const remove_cancel_btn = document.querySelector('.remove_cancel_btn');

btn_remove.forEach(btn_remove => {
    btn_remove.addEventListener('click', () => {
        remove_modal.classList.add('active');
        remove_btn_close.classList.add('active');
    })
});

remove_close_arr = [remove_btn_close, remove_cancel_btn];
remove_close_arr.forEach(close => {
    close.addEventListener('click', () => {
        remove_modal.classList.remove('active');
        remove_btn_close.classList.remove('active');
    })
});