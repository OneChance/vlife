$(function() {

	var VLIFE = {
		game:{}
	};
	
	window.VLIFE = VLIFE;
	
	$('#unsign').click(function(){		
		UTIL.ajax.go(baseUrl+'unsign',"POST",null,function(res) {          	
        	if(res.message){
        		VLIFE.game.showMsg(res.message);
        	}else{
        		location = location;
        	}
        });
	});

    $("input,textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
        	
        	var url;
        	
        	var entertype = $("[name=entertype]").filter(':checked').val();
        	var account = $("input#account").val();
        	var name = $("input#name").val();
            var password = $("input#password").val();
            var sex = $("[name=sex]").val();

        	data = {
        			account:account,
        			name: name,
                    password: password
            }
        	
        	if(entertype == 'login'){
        		url = baseUrl+'login';     		
        	}else{
        		url = baseUrl+'reg';    		
        		data.sex = sex;
        	}
        	
            event.preventDefault(); // prevent default submit behaviour
            
            UTIL.ajax.go(url,"POST",data,function(res) {   
            	if(res.message){
            		VLIFE.game.showMsg(res.message);
            	}else{          		
            		location = location;        		 
            	}
            });
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
    
    show_info_detail = function(){
    	$("#info_detail").toggle("normal");
    }
    
    info_cancel = function(func){
    	$('#global_msg').fadeOut();
    	(func)();
    }
    info_confirm = function(func){
    	(func)();
    }
    
    VLIFE.game.reincarnate = function(callbackFunc){
    	UTIL.ajax.go(baseUrl+'reincarnate',"POST",null,callbackFunc);
    }
    
    VLIFE.game.changeProp = function(propData,callbackFunc){
    	UTIL.ajax.go(baseUrl+'changeprop',"POST",propData,callbackFunc);
    }
    
    VLIFE.game.showMsg = function(msg,type,moreInfo,moreText,infoContent,button,confirmText,confirmFunc,cancelText,cancelFunc){
    	
    	if($('#global_msg')){
    		$('#global_msg').remove();
    	}
    	
    	if(!type){
    		type = 'danger';
    	}
    	
    	var content = "<div class='alert alert-"+type+"' id='global_msg' style='z-index:3000;position:fixed; top:10px;left:10px;display:none'><button type='button' class='close' data-dismiss='alert' onclick='("+cancelFunc+")();'>&times;</button>"+
		  "<strong>"+msg+"</strong>";
    	
    	if(moreInfo){
    		content = content + "<a href='javacript:void(0);' onclick='show_info_detail();' style='margin-left:10px;'>"+moreText+"</a><p><div class='info-container' style='display:none' id='info_detail'>"+infoContent+"</div>";
    	}
    	
    	if(button){
    		content = content +　"<p><div style='padding-top:8px;text-align: center;'>" +
    							 "<button type='button' class='btn btn-success btn-sm' onclick='info_confirm("+confirmFunc+");'><i class='fa fa-edit'></i>"+confirmText+"&nbsp;&nbsp;"+
    							 "<button type='button' class='btn btn-warning btn-sm' onclick='info_cancel("+cancelFunc+");' style='margin-left:5px;'><i class='fa fa-edit'></i>"+cancelText+"</div>";
    	}
    	
    	content = content + "</div>";
    	
    	
    	$('#page-top').append(content);
    	
    	$('#global_msg').fadeIn('slow',function(){
    		
    		if(!moreInfo){
    			setTimeout(function () { 
        			if($('#global_msg').is(':visible')){
        				$('#global_msg').fadeOut();
        			}	
        	    }, 1000);
    		}
    		
    	});
    }
    
    VLIFE.game.regionInfo = function(regionId,callbackFunc){
    	UTIL.ajax.go(baseUrl+'regionInfo',"POST",{regionId:regionId},callbackFunc);
    }
});


/* When clicking on Full hide fail/success boxes */
$('input').focus(function() {
   $('#success').hide();
});

$('input').on('ifChecked', function(event){
	  $(this).click();
});

$('[name=entertype]').on('ifChecked', function(event){
	  if($(this).val()=='reg'){
		  $('.for-reg').show();
	  }else{
		  $('.for-reg').hide();
	  }
});

$(".game-page").click(function(){
	$($(this).attr("href")).load(baseUrl+$(this).attr("url"));
})
