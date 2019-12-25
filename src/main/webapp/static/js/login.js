$(function () {

    $("#loginBtn").click(function () {
        /*发送请求  做登录认证*/
        $.post("/login",$("form").serialize(),function (data) {
        })
    })
});