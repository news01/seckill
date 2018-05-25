/**
 * 存放主要交互逻辑的js代码
 */
var seckill = {
	url : {

	},
	validatePhone : function(phone) {
		if (phone && phone.length == 11 && isNaN(phone)) {
			return true;
		} else {
			return false;
		}

	},
	detail : {
		init : function(params) {
			var seckillPhone = $cookie("killPhone");
			var seckillId = params['seckillId'];
			var startTime = params['startTime'];
			var t = seckill.validatePhone(seckillPhone);
			if(!t){
				
			}
		}

	}

}