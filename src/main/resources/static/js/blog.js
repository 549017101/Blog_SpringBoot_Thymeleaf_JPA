/**
  Author: pxz
  Email: buffll@foxmail.com
  2021-03-05 0:20
*/
tocbot.init({
	// Where to render the table of contents.
	tocSelector: '.js-toc',
	// Where to grab the headings to build the table of contents.
	contentSelector: '.js-toc-content',
	// Which headings to grab inside of the contentSelector element.
	headingSelector: 'h1, h2, h3',
})

//赞赏按钮浮动效果
$('#payButton').popup({
	popup: $('.payQR.popup'),
	//on: 'click',
	position: 'bottom center'
})

//侧边栏目录按钮浮动效果
$('.toc.button').popup({
	popup: $('.toc-container.popup'),
	on: 'click',
	position: 'left center'
})

//侧边栏微信按钮浮动效果
$('.wechat').popup({
	popup: $('.wechat-qr'),
	position: 'left center'
})

// 扫描二维码在移动端阅读
// var qrcode = new QRCode("qrcode", {
//   //text: "../../static/img/vx.png",
//   width: 110,
//   height: 110,
//   colorDark: "#000000",
//   colorLight: "#ffffff",
//   correctLevel: QRCode.CorrectLevel.H
// })

//移动到顶部
$('#toTop-button').click(function () {
	$(window).scrollTo(0, 500)
})

//移动到底部
$('#toButtom-button').click(function () {
	$(window).scrollTo(document.body.scrollHeight, 500)
})

let waypoint = new Waypoint({
	element: document.getElementById('waypoint'),
	handler: function (direction) {
		if (direction === 'down') {
			$('#toolbar').show(100);
		} else {
			$('#toolbar').hide(500);
		}
		//console.log('Scrolled to waypoint!  ' + direction);
	}
})

//评论表单验证
$('.ui.form').form({
	fields: {
		title: {
			identifier: 'content',
			rules: [{
				type: 'empty',
				prompt: '请输入评论内容'
			}
			]
		},
		content: {
			identifier: 'nickname',
			rules: [{
				type: 'empty',
				prompt: '请输入您的昵称'
			}]
		},
		type: {
			identifier: 'email',
			rules: [{
				type: 'email',
				prompt: '请填写正确的邮箱地址'
			}]
		}
	}
})