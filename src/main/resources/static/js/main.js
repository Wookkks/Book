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