/**
  Author: pxz
  Email: buffll@foxmail.com
  2021-03-05 0:15
*/
$('.menu.toggle').click(function () {
	$('.m-item').toggleClass('m-mobile-hide');
})

//微信的悬浮窗
$('.wechat').popup({
	popup: $('.wechat-qr'),
	position: 'bottom center'
})

//QQ的悬浮窗
$('.qq').popup({
	popup: $('.qq-qr'),
	position: 'bottom center'
})

//邮箱的悬浮窗
$('.email').popup({
	popup: $('.email-qr'),
	position: 'bottom center'
})

//github的悬浮窗
// $('.github').popup({
//   popup: $('.github-qr'),
//   position: 'bottom center'
// });