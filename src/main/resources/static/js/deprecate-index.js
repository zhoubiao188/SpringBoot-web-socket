var sock = new SockJS("/endpointChat");
var stomp = Stomp.over(sock);
stomp.connect('guest', 'guest', function (frame) {
    stomp.subscribe("/user/notification", showGetMsg);
});


/**
 * 点击发送信息
 */
$('#chat-form').submit(function (e) {


        e.preventDefault();
        var text = $('textarea')[0].value;
        var receiver = $('input')[0].value;
        if (receiver != "" && text != "" || text !=""){
            console.log("发送的内容: [" + text + "] || 要发送给的对象: [" + receiver + "]");

            showSendMsg(text);

            guendong();
            $('textarea')[0].value = "";
            stomp.send("/chat", {}, JSON.stringify({"text": text, "receiver": receiver}));
            input_megssage.focus();
            addStyle();
        } else {

        }


});


function guendong(){
    // var  ps = document.getElementsByClassName('tochatwith_window')[0].getElementsByTagName('i')[0];
    // ps.scrollIntoView(false);
    var eles = document.getElementsByClassName('tochatwith_window')[0];

    eles.scrollTop = eles.scrollHeight;
    console.log(typeof eles.scrollTop);
}

//输入消息框
let input_megssage = document.getElementsByClassName('post_message_window')[0].getElementsByTagName('textarea')[0];
/**
 * 显示发送的信息
 * @param message
 */
function showSendMsg(message) {
    // 这个API时间格式化函数别人写的是，这里用hh ...
    var now = new Date().Format("yyyy-MM-dd hh:mm:ss");
    var receiverList = document.getElementById('btn1');

   if (receiverList.value == ""){
       $('#receive')[0].innerHTML += '<span>'.concat(now).concat("<br> 我: ").concat(message).concat('</span><br><hr>');

       $('input')[0].value = "";
   }else{
       $('#receive')[0].innerHTML += '<p>'.concat(now).concat("<br>" + "我@" + receiverList.value +":").concat(message).concat('</p><br>');
   }

}

function  addStyle() {
    var getShowMessage = document.getElementsByTagName('p');
    var showMessage =  getShowMessage.length -1;
    getShowMessage[showMessage].style.marginTop=30 + 'px';
    getShowMessage[showMessage].style.fontSize = 3+ 'rem';
    getShowMessage[showMessage].style.color = 'black';

}

/**
 * 显示收到的信息
 * @param message
 */
function showGetMsg(message) {
    console.log("收到服务器转发的的信息（别人发来的）:" + message);
    // 这个API时间格式化函数别人写的是，这里用hh ...
    var now = new Date().Format("yyyy-MM-dd hh:mm:ss");
    // 这里显示还没有完善(此外：异步回调的message是一个http数据包，数据在body)


        $('#receive')[0].innerHTML += '<p>'.concat(now).concat("<br>").concat(message.body).concat('</p><br>');


}


Date.prototype.Format = function(fmt)
{ //author: meizz   
    var o = {
        "M+" : this.getMonth()+1,                 //月份   
        "d+" : this.getDate(),                    //日   
        "h+" : this.getHours(),                   //小时   
        "m+" : this.getMinutes(),                 //分   
        "s+" : this.getSeconds(),                 //秒   
        "q+" : Math.floor((this.getMonth()+3)/3), //季度   
        "S"  : this.getMilliseconds()             //毫秒   
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

setInterval(function () {
    addStyle();

},800)
