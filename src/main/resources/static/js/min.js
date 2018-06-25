
function asac(){
	console.log("aaa");
	alert("aaa");
};


function bbbb(){
	console.log("bbbb");
    		$.ajax({
    	  	       type: 'post',
    	  	       url: 'http://localhost:8887/loginUser',
    	  	       data: {"username":"hlhdidi",
    	  	        "password":"123"},
    	  	       async:false,
    	 	       success: function(d){
                        console.log(d.returnCode);
                        if(d.returnCode!=1000){
                         console.log("error");
                        }else{
                        console.log("success");
                        }
    	 	       },
    	  	       dataType: "json"
    	    });
};

$(function(){
	$("div.div1").click(function(){
		console.log("aaa");
		$.ajax({
	  	       type: 'post',
	  	       url: 'http://localhost:8887/get',
	  	       data: {"name":"minqi"},
	  	       async:true,
	 	       success: function(d){
                    console.log(d.id);
                    alert("age:"+d.age);
	 	       },
	  	       dataType: "json"
	    });
	});
})