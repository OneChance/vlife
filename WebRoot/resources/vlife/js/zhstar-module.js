(function() {
	'use strict';
	var UTIL = {
		ajax : {},
	};
	
	window.UTIL = UTIL;
	
	UTIL.ajax.go = function(url,goType,data,success){
		$.ajax({
            url: url,
            type: goType,
            data: data,
            cache: false,
            dataType:"json",
            success:success,
            error: function() {         	
            	alert(server_error_msg);
            },
        });
	}
	
}());

