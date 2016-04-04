$(function() {

	var VLIFE = {
		game:{}
	};
	
	window.VLIFE = VLIFE;
	
	$('#unsign').click(function(){		
		UTIL.ajax.go(baseUrl+'unsign',"POST",null,function(res) {          	
        	if(res.message){
        		showMsg(res.message);
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
            		showMsg(res.message);
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
    
    VLIFE.game.reincarnate = function(callbackFunc){
    	UTIL.ajax.go(baseUrl+'reincarnate',"POST",null,callbackFunc);
    }
    
    VLIFE.game.changeProp = function(propData,callbackFunc){
    	UTIL.ajax.go(baseUrl+'changeprop',"POST",propData,callbackFunc);
    }
    
    VLIFE.game.showMsg = function(msg){
    	alert(msg);
    }
    
    VLIFE.game.regionInfo = function(regionId,callbackFunc){
    	UTIL.ajax.go(baseUrl+'regionInfo',"POST",{regionId:regionId},callbackFunc);
    }
});

function showMsg(msg){	
	$('#success').fadeIn('slow',function(){
		setTimeout(function () { 
			if($('#success').is(':visible')){
				$('#success').fadeOut();
			}	
	    }, 1000);
	}).find("strong").html(msg); 
}

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
