$(function(){

    let interface = (function(){

    /*--------图片的轮播----- */
        var play_img = () => {
            img_box[j].style.opacity = i
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
            img_box[j].style.opacity = i;
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
            img_box[j].style.opacity = i;
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
            img_box[j].style.opacity = i;
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

    /*--------输入框的提示信息显示样式----- */
        var user_name_tishi = () => {
            spans[0].innerText = "";
            spans[1].innerText = "";
            spans[2].innerText = "";
            spans[3].innerText = "";
            p_box[0].innerText = "请输入2-10个字符或汉字"
            p_box[0].style.top = "80px";
            p_box[1].style.top = "135px";
            inputs2_box.style.top = "120px";
            inputs3_box.style.top = "210px";
            inputs4_box.style.top = "410px";
        }
        var user_name = () => {
            p_box[0].style.top = "30px";
            p_box[1].style.top = "105px";
            inputs2_box.style.top = "90px";
            inputs3_box.style.top = "180px";
            inputs4_box.style.top = "380px";
        }
        var user_id_tishi = () => {
            spans[0].innerText = "";
            spans[1].innerText = "";
            spans[2].innerText = "";
            spans[3].innerText = "";
            p_box[1].innerText = "请输入4-11个字母或数字"
            p_box[1].style.top = "165px";
            inputs3_box.style.top = "210px";
            inputs4_box.style.top = "410px";
        }
        var user_id = () => {
            p_box[1].style.top = "105px";
            inputs3_box.style.top = "180px";
            inputs4_box.style.top = "380px";
        }
        var user_passwords_tishi = () => {
            spans[0].innerText = "";
            spans[1].innerText = "";
            spans[2].innerText = "";
            spans[3].innerText = "";
            p_box[2].innerText = "密码的长度只能是6到22位字符";
            p_box[2].style.top = "75px";
            inputs4_box.style.top = "410px";
            password_box.style.marginTop = "60px";
        }
        var user_passwords = () => {
            p_box[2].style.top = "27px";
            password_box.style.marginTop = "30px";
            inputs4_box.style.top = "380px";
        }

        var zhuce_chenggong = () => {
            zhezhaoceng.style.opacity = "0.8";
            zhezhaoceng.style.zIndex = "7";
            timer2 = setInterval(daoshu,1000);
        }

        var daoshu = () => {
            timerI--;
            daoshus.innerText = " " + timerI + " ";
            if(timerI == 0){
                clearInterval(timer2);
                location.href = "/user/login";
            }  
        }

        var submit = () => {
            let ys = false;
            spans[0].innerText = "";
            spans[1].innerText = "";
            spans[2].innerText = "";
            spans[3].innerText = "";
            if(user_names.value == "" && user_nickname.value == "" && user_password.value == ""){
                spans[0].innerText = "请输入用户名";
                spans[1].innerText = "请输入账户";
                spans[2].innerText = "请输入密码";
            }else if(user_names.value == "" && user_nickname.value == ""){
                spans[0].innerText = "请输入用户名";
                spans[1].innerText = "请输入账户";
                spans[2].innerText = "";
                spans[3].innerText = "";
            }else if(user_nickname.value == "" && user_password.value == ""){
                spans[1].innerText = "请输入账户";
                spans[2].innerText = "请输入密码";
                spans[0].innerText = "";
            }else if(user_names.value == "" && user_password.value == ""){
                spans[0].innerText = "请输入用户名";
                spans[2].innerText = "请输入密码";
                spans[1].innerText = "";
            }else if(user_names.value == ""){
                spans[2].innerText = "";
                spans[1].innerText = "";
                spans[0].innerText = "请输入用户名";
            }else if(user_nickname.value == ""){
                spans[2].innerText = "";
                spans[0].innerText = "";
                spans[1].innerText = "请输入账户";
            }else if(user_password.value == ""){
                spans[2].innerText = "请输入密码";
                spans[0].innerText = "";
                spans[1].innerText = "";
            }else{
                ys = true;
            }
            if(user_password.value !=user_again_password.value){
                spans[3].innerText = "密码不相同";
                ys = false;
            }
            if(ys == true){
                spans[0].innerText = "";
                spans[1].innerText = "";
                spans[2].innerText = "";
                spans[3].innerText = "";
                $.ajax({
                    url:"/user/register",
                    type:"post",
                    data:{
                        "nickname" :user_names.value,
                        "username":user_nickname.value,
                        "password":user_password.value
                    },
                    success:function(data){
                        console.log(data);
                        if(data.message == "用户名称的长度只能是2到10位"){
                            spans[0].innerText = "用户名称的长度只能是2到10位!";
                            return;
                        }else if(data.message == "用户名的长度只能是4到11位"){
                            spans[1].innerText = "用户名的长度只能是4到11位!"
                            return;
                        }else if(data.message == "密码的长度只能是6到22位"){
                            spans[2].innerText = "密码的长度只能是6到22位!";
                        }else if(data.message == "用户名已被注册:"+user_nickname.value){
                            spans[1].innerText = "此用户名已存在！"
                        }else if(data.code == 200){
                            zhuce_chenggong();
                        }  
                    },
                    srror:function(){
                        console.log("注册失败");
                    }
                })
            }
            
        }

        return {
            play_img,
            user_name,
            user_name_tishi,
            user_id_tishi,
            user_id,
            user_passwords_tishi,
            user_passwords,
            submit,
            zhuce_chenggong
        };

    })()
    let img_box = document.getElementsByClassName('save_img');
    let i = 1;  //隐藏顺序
    let j = 1; //播放顺序
    let timer; //计时器1
    let timer2;//计时器2
    let timerI = 6;
    let daoshus = document.getElementsByClassName('tishi')[0].getElementsByTagName('i')[0];
    let zhezhaoceng = document.getElementsByClassName('zhezhaoceng')[0];
    console.log(zhezhaoceng);

    //提示信息元素集合
    let inputs1_box = document.getElementsByClassName('user_name')[0];
    let inputs2_box = document.getElementsByClassName('user_id')[0];
    let inputs3_box = document.getElementsByClassName('user_password')[0];
    let inputs4_box = document.getElementsByClassName('submit_user')[0];
    let password_box = document.getElementsByClassName('user_password')[0].getElementsByTagName('input')[1];
    let p_box = document.getElementsByClassName('input_box')[0].getElementsByTagName('p');
    let inputs = document.getElementsByTagName('input');
    //提交按钮
    let sumbit = document.getElementsByClassName('submit_user')[0].getElementsByTagName('input')[0];
    //用户名称
    let user_nickname = document.getElementsByClassName('user_id')[0].getElementsByTagName('input')[0];
    //用户账号
    let user_names = document.getElementsByClassName('user_name')[0].getElementsByTagName('input')[0];
    //用户密码
    let user_password = document.getElementsByClassName('user_password')[0].getElementsByTagName('input')[0];
    let user_again_password = document.getElementsByClassName('user_password')[0].getElementsByTagName('input')[1];
    //警告信息
    let spans = document.getElementsByTagName('span');
    //播放图片
    timer = setInterval(interface.play_img,2500);

    inputs[0].onfocus = interface.user_name_tishi;
    inputs[0].onblur = interface.user_name;
    inputs[1].onfocus = interface.user_id_tishi;
    inputs[1].onblur = interface.user_id;
    inputs[2].onfocus = interface.user_passwords_tishi;
    inputs[2].onblur = interface.user_passwords;
    sumbit.addEventListener('click',interface.submit,false);//提交信息

})