function toggleMenu() {
    var drawer = document.querySelector('.drawer');
    drawer.classList.toggle('open');
  }

//登录按钮
document.getElementById('div_sid2').addEventListener('click', function() {
    document.querySelector('#div_sid2 input[type="submit"]').click();
});