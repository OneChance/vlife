(function() {
	'use strict';
	var UTIL = {
		ajax : {},
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
	}
	
}());

