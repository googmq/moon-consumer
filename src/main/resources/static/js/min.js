var page = 1
var size = 3
var total = 1

$.ajax({
        contentType:"application/json",
        type: "post",
        url: "http://localhost:8880/findPage",
        data: JSON.stringify({"pageNumber":page,"pageSize" : size}),
        async:true,
        success: function(d){
            console.log(d.result.number+1);
            $(".social_facebook1").attr("src",d.result.content[0].url);
            $(".social_facebook2").attr("src",d.result.content[1].url);
            $(".social_facebook3").attr("src",d.result.content[2].url);
            total = d.result.totalPages;
        },
        dataType:"json"
});

$(function(){
	$("#b1").click(function(){
			console.log("aaa");
			if(page>1){
			    page = page-1
			}else{
			    page = total
			}
    		$.ajax({
    		       contentType:"application/json",
    	  	       type: "post",
    	  	       url: "http://localhost:8880/findPage",
    	  	       data: JSON.stringify({"pageNumber":page,"pageSize" : size}),
    	  	       async:true,
    	 	       success: function(d){
                        console.log(d.result.number+1);
                        $(".social_facebook1").attr("src",d.result.content[0].url);
                        $(".social_facebook2").attr("src",d.result.content[1].url);
                        $(".social_facebook3").attr("src",d.result.content[2].url);
                        total = d.result.totalPages;
    	 	       },
    	 	       dataType:"json"
    	    });
	});
})

$(function(){
	$("#b2").click(function(){
			console.log("bbb");
			if(page < total){
			    page = page+1
			}else{
			    page = 1
			}
    		$.ajax({
    		       contentType:"application/json",
    	  	       type: "post",
    	  	       url: "http://localhost:8880/findPage",
    	  	       data: JSON.stringify({"pageNumber":page,"pageSize" : size}),
    	  	       async:true,
    	 	       success: function(d){
                        console.log(d.result.number+1);
                        $(".social_facebook1").attr("src",d.result.content[0].url);
                        $(".social_facebook2").attr("src",d.result.content[1].url);
                        $(".social_facebook3").attr("src",d.result.content[2].url);
                        total = d.result.totalPages;
    	 	       },
    	 	       dataType:"json"
    	    });
	});
})
