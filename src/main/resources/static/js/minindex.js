
$(function(){
	$("#btn1").click(function(){
	    var email = $('input[name="email"]').val();
	    var myReg=/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		if(email===""){
		    alert("邮箱不能为空")
		}else if(myReg.test(email)){
               		$.ajax({
               		       contentType:"application/json",
               	  	       type: "post",
               	  	       url: "http://47.106.186.255/mail",
               	  	       data: JSON.stringify({"userEmail":email}),
               	  	       async:true,
               	 	       success: function(d){
                                alert("感谢关注，若网站正式开放我们会第一时间告诉你的~ ^_^");
               	 	            $('input[name="email"]').val("");
               	 	       },
               	 	       dataType:"json"
               	    });
        }else{
            alert("邮箱格式有误!")
        }
	});
})