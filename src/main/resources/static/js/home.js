$(function(){

    /*-------------方法接口-----------*/
    let interface = (function(){
        
        //发送按钮按下事件
        let btn_mousedown = () => {
            post_btn.style.filter = "none";
        }
        //发送按钮释放事件
        let btn_mouseup = () => {
                post_btn.style.filter = "brightness(1.1)";
        }
        
        let change_user = () =>{
            console.log("1");
            change_ceng.style.top = "22%";
        }


        //边框过渡效果
        let border_style = () => {
            border[0].style.border = '15px dotted rgba(23, 191, 233,' + x + ')';
            if(j == false){
                x-= 0.1;
                if(x < 0.4){
                    j = true;
                }
            }else if(j == true){
                x += 0.1;
                if(x > 1.1){
                    j = false;
                }
            }             
        }


        return{
            btn_mousedown,
            btn_mouseup,
            change_user,
            border_style,
        }

    })()


let j = false;
let y = false;
    //发送按钮
    let post_btn = document.getElementsByClassName('post_message_btn')[0].getElementsByTagName('button')[0];
    //输入消息框
    let input_megssage = document.getElementsByClassName('post_message_window')[0].getElementsByTagName('textarea')[0];
    //切换账号按钮
    let change_btn = document.getElementsByClassName('change_user')[0].getElementsByTagName('button')[0];
    //  弹出层
    let change_ceng = document.getElementsByClassName('ys_change_user')[0];
    console.log(change_ceng);
    //确定切换按钮
    let change_ceng_btn1 = document.getElementsByClassName('ys_change_user')[0].getElementsByTagName('button')[0];
    //取消切换按钮
    let change_ceng_btn2 = document.getElementsByClassName('ys_change_user')[0].getElementsByTagName('button')[1];
    //边框
    let border = document.getElementsByClassName('message_window_box');
    //边框计时器
    let tiemr1;
    //边框透明度值
    let x = 1;
    //边框增长判断
    let bor = 0;
    //边框计时器
    tiemr1 = setInterval(interface.border_style,100);
    //打开页面文本框自动获得焦点
    input_megssage.focus();

    post_btn.addEventListener('click',interface.input_megssage_fn,false);

    post_btn.addEventListener('mousedown',interface.btn_mousedown,false);

    post_btn.addEventListener('mouseup',interface.btn_mouseup,false);
    
    //切换按钮弹出层
    change_ceng_btn1.onclick = function(){     
        location.href = "/user/login";
    }
    change_ceng_btn2.onclick = function(){
        change_ceng.style.top = "-500px";
    }

    change_btn.addEventListener("click",interface.change_user);
})