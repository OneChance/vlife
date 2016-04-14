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
    
    VLIFE.game.reincarnate = function(callbackFunc){
    	UTIL.ajax.go(baseUrl+'reincarnate',"POST",null,callbackFunc);
    }
    
    VLIFE.game.changeProp = function(propData,callbackFunc){
    	UTIL.ajax.go(baseUrl+'changeprop',"POST",propData,callbackFunc);
    }
    
    VLIFE.game.confirm = function(msg,confirmFunc){
    	VLIFE.game.showMsg(msg,"warning",false,"","",true,"",confirmFunc);
    }
    
    VLIFE.game.showMsg = function(msg,type,moreInfo,moreText,infoContent,button,confirmText,confirmFunc,cancelText,cancelFunc){
    	
    	if($('#global_msg')){
    		$('#global_msg').remove();
    	}
    	
    	if(!type){
    		type = 'danger';
    	}
    	
    	var content = "<div class='alert alert-"+type+"-border' id='global_msg' style='z-index:3000;position:fixed; top:10px;left:10px;display:none'><button type='button' class='close info_cancel' data-dismiss='alert'>&times;</button>"+
		  "<strong>"+msg+"</strong>";
    	
    	if(moreInfo){
    		
    		$(".global_overlay").show();
    		
    		content = content + "<a href='javacript:void(0);' onclick='show_info_detail();' style='margin-left:10px;'>"+moreText+"</a><p><div class='info-container' style='display:none' id='info_detail'>"+infoContent+"</div>";
    	}
    	
    	if(button){
    		
    		$(".global_overlay").show();
    		
    		if(!confirmText){
    			confirmText = op_confirm;
    		}
    		
    		if(!cancelText){
    			cancelText = op_cancel;
    		}
    		
    		content = content + "<p><div style='padding-top:8px;text-align: center;'>" +
    							 "<button type='button' class='btn btn-success btn-sm info_confirm'><i class='fa fa-check'></i>"+confirmText+"&nbsp;&nbsp;"+
    							 "<button type='button' class='btn btn-danger btn-sm info_cancel' style='margin-left:5px;'><i class='fa fa-remove'></i>"+cancelText+"</div>";
    	}
    	
    	content = content + "</div>";

    	$('#page-top').append(content);
    	
    	$(".info_confirm").bind("click",confirmFunc).click(function(){
    		$(".global_overlay").hide();
    	});
    	$(".info_cancel").bind("click",cancelFunc).click(function(){
    		$('#global_msg').fadeOut();
    		$(".global_overlay").hide();
    	});

    	$('#global_msg').fadeIn('slow',function(){
    		
    		var $this = $(this);
    		
    		if(!moreInfo && !button){
    			setTimeout(function () { 
        			if($this.is(':visible')){
        				$this.fadeOut();
        			}	
        	    }, 1000);
    		}  		
    	});
    }
    
    VLIFE.game.regionInfo = function(regionId,callbackFunc,errorFunc){
    	UTIL.ajax.go(baseUrl+'regionInfo',"POST",{regionId:regionId},callbackFunc,errorFunc);
    }
    
    VLIFE.game.regionMove = function(regionId,callbackFunc,errorFunc){
    	UTIL.ajax.go(baseUrl+'regionMove',"POST",{regionId:regionId},callbackFunc,errorFunc);
    }
    
    VLIFE.game.actionExe = function(species,code,callbackFunc){
    	UTIL.ajax.go(baseUrl+species+'/'+code,"POST",{},callbackFunc);
    }
    
    VLIFE.game.actionDelete = function(species,actionId,callbackFunc){
    	UTIL.ajax.go(baseUrl+species+'/delete',"POST",{actionId:actionId},callbackFunc);
    }
    
    $('.page-scroll a').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });
    
    $("body").on("input propertychange", ".floating-label-form-group", function(e) {
        $(this).toggleClass("floating-label-form-group-with-value", !! $(e.target).val());
    }).on("focus", ".floating-label-form-group", function() {
        $(this).addClass("floating-label-form-group-with-focus");
    }).on("blur", ".floating-label-form-group", function() {
        $(this).removeClass("floating-label-form-group-with-focus");
    });
    
    // Highlight the top nav as scrolling occurs
    $('body').scrollspy({
        target: '.navbar-fixed-top'
    })

    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function() {
        $('.navbar-toggle:visible').click();
    });
    
    $('header').css("height",document.documentElement.clientHeight+"px");
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
