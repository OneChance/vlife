(function() {
	'use strict';
	var UTIL = {
		ajax : {},
		format:{},
		time:{}
	};
	
	window.UTIL = UTIL;
	
	UTIL.ajax.go = function(url,goType,data,success,error){
		$.ajax({
            url: url,
            type: goType,
            data: data,
            cache: false,
            dataType:"json",
            success:success,
            error: function() {
            	VLIFE.game.showMsg(server_error_msg);
            	if(error){
            		(error)();
            	}       	
            }
        });
	};
	
	UTIL.format.time = function(value){
		if(value<10){
			return '0'+value;
		}
		return value;
	}
	
	$.fn.zhcountdown = function(callback){		

		return this.each(function() {
			var timeLeft = $(this).attr("timeleft");
			var interval;
			var $this = $(this);
			var values = {};
			
			if(timeLeft){
				timeLeft = Math.floor(timeLeft/1000);
			}else{
				return;
			}
			
			countdown();
			interval = setInterval(function(){countdown();},1000);
			
			function countdown() {	
				
				if(!$this.parent().is(':visible')){
					stop();
					return;
				}		    

				timeLeft--;
				
				if(timeLeft <= 0) {
					stop();
			        dispatchEvent("finished");
			        return;
			    }
		        
		        var seconds = UTIL.format.time(timeLeft % 60);
		        var minutes = UTIL.format.time(Math.floor(timeLeft / 60) % 60);
		        var hours = UTIL.format.time(Math.floor(timeLeft / 60 / 60));
		        
		        values["show"] = hours+":"+minutes+":"+seconds;
		        dispatchEvent("show");		        
		    }
			
		     
			function dispatchEvent(eventName) {
		        var event = $.Event(eventName);
		        event.value = values[eventName];
		        callback.call($this, event);
		    }
		      
		    function stop() {
		        clearInterval(interval);
		    }
		});
	}

}());

