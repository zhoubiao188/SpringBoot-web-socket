$(function () {    
   
    /*-------------方法接口---------------- */
    let interface = (function () {
    /*-------------轮播图片---------------- */
    var play_img = () => {
            imgs[j].style.opacity = i
            if (j == 0) {
                j += 1;
            } else if (j == 1) {
                j += 1;
            } else if (j == 2) {
                j += 1;
            } else if (j == 3) {
                j -= 3;
            }
            if (i == 1) {
                i--;
            }
            imgs[j].style.opacity = i;
            if (j == 1) {
                j += 1;
            } else if (j == 2) {
                j += 1;
            } else if (j == 3) {
                j -= 3;
            } else if (j == 0) {
                j += 1;
            }
            if( i == 1){
                i--;
            }
            imgs[j].style.opacity = i;
            if (j == 1) {
                j += 1;
            } else if (j == 2) {
                j += 1;
            } else if (j == 3) {
                j -= 3;
            } else if (j == 0) {
                j += 1;
            }
            if(i ==1){
                i--;
            }
            imgs[j].style.opacity = i;
            if (j == 0) {
                j += 2;//2
            } else if (j == 1) {
                j += 2;//3
            } else if (j == 2) {
                j -= 2;//0
            }else if(j == 3){
                j -= 2;//1
            }
            if (i == 0) {
                i++;
            }
    }
    /*-------------开始播放---------------- */
    var start_play = () =>{
        timer = setInterval(interface.play_img, 3000);
    }
     /*-------------停止播放---------------- */
    var stop_play = () =>{
        clearInterval(timer);
    }

    /*--------------判断输入是否正确,登录------------ */
    var btn_shijian = () => {

            p_user.innerText = "";
            p_password.innerText = "";
        if(user.value == ""){
            p_user.style.color = "red";
            p_user.innerText = "请输入账号";
        }else if(password.value == ""){
            p_user.style.color = "white";
            p_password.style.color = "red";
            p_password.innerText = "请输入密码";
        }
        $.ajax({
            url:"/login",
            type:"post",
            data:{
                "username": user.value,
                "password": password.value
            },
            success:function(data){

                     p_user.style.color = "white";
                     p_user.innerText = "1";
                     p_password.style.color = "white";
                     p_password.innerText = "1";
                     location.href = "/user/home";
                
                   
            },
            error:function(data){
                console.log(data);
                if(data.responseJSON.message == "用户不存在!"){
                    p_user.style.color = "red";
                    p_user.innerText = data.responseJSON.message;
                    return;
                    
                }else{
                    p_password.style.color = "red";
                    p_password.innerText = data.responseJSON.message; 
                }

                console.log(data.responseJSON);
                console.log(data.responseJSON.message);
            }
        })
        
    }

    var create_new_users = () =>{
        location.href =  "/user/register/personal";
    }
        return {
            play_img,
            stop_play,
            start_play,
            btn_shijian,
            create_new_users
        };
    })();

   
    let index = 1;
    let imgs = document.getElementsByClassName('denglu_win_beijing')[0].getElementsByClassName('img_box');//图片集合
    let i = 1;  //隐藏顺序
    let j = 1; //播放顺序
    let timer; //计时器
    //注册按钮
    let create_new_user = document.getElementsByClassName('denglu_box_footer')[0].getElementsByTagName("a")[0];
    //登录按钮
    let denglu_btn = document.getElementsByClassName('denglu_box_btn')[0].getElementsByTagName('input')[1];
    //账号
    let user = document.getElementsByClassName('users')[0].getElementsByTagName('input')[0];
    //密码
    let password = document.getElementsByClassName('passwords')[0].getElementsByTagName('input')[0];
    //提示信息
    let p_user = document.getElementsByClassName('users')[0].getElementsByTagName('p')[0];
    let p_password = document.getElementsByClassName('passwords')[0].getElementsByTagName('p')[0];
    // console.log(user);
    // console.log(password);
    // console.log(denglu_btn);
    // console.log(rember_user);
    /*------------------判断账号是否输入正确-------------- */

    timer = setInterval(interface.play_img, 3000);
    $('.denglu_box_footer:eq(0)').children('a:eq(0)').bind('click',interface.create_new_users);
    $('.denglu_box_btn:eq(0)').children('input:eq(0)').bind('click',interface.btn_shijian);
    $('.denglu_win_beijing:eq(0)').bind('mouseover',interface.stop_play);
    $('.denglu_win_beijing:eq(0)').bind('mouseout',interface.start_play);
    password.onkeydown = function(e){

        if(e.keyCode == 13){
            interface.btn_shijian();
            console.log("2")
        }
    }


     

})


