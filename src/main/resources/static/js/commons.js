$(function () {
  //引入公共底部
  // 方式1:
  //$(".myFooter").load("/common/footer.html")

  // 方式2: ajax异步请求
    $.ajax({
      type: "get",
      url: "/common/footer.html",
      async: true,
      success: function (data) {
        $(".myFooter").html(data);
      }
    })

  //引入导航栏
    $.ajax({
      type: "get",
      url: "/common/navigation.html",
      async: true,
      success: function (data) {
        $(".myNav").html(data);
      }
    })

    //引入上一页下一页按钮
    $.ajax({
      type: "get",
      url: "/common/pageUpDown.html",
      async: true,
      success: function (data) {
        $(".pageUpDown").html(data);
      }
    })
  })