var baseUrl = "http://114.116.24.215:8080";
baseUrl="http://localhost:8080";
var REGIONPRICE = baseUrl + "/prd/variety/regionPrice";
var VARIETYLARGE = baseUrl + "/prd/variety/large";
var echartMapStyle = {
	styleJson: [{
		'featureType': 'water',
		'elementType': 'all',
		'stylers': {
			'color': '#d1d1d1'
		}
	}, {
		'featureType': 'land',
		'elementType': 'all',
		'stylers': {
			'color': '#f3f3f3'
		}
	}, {
		'featureType': 'railway',
		'elementType': 'all',
		'stylers': {
			'visibility': 'off'
		}
	}, {
		'featureType': 'highway',
		'elementType': 'all',
		'stylers': {
			'color': '#fdfdfd'
		}
	}, {
		'featureType': 'highway',
		'elementType': 'labels',
		'stylers': {
			'visibility': 'off'
		}
	}, {
		'featureType': 'arterial',
		'elementType': 'geometry',
		'stylers': {
			'color': '#fefefe'
		}
	}, {
		'featureType': 'arterial',
		'elementType': 'geometry.fill',
		'stylers': {
			'color': '#fefefe'
		}
	}, {
		'featureType': 'poi',
		'elementType': 'all',
		'stylers': {
			'visibility': 'off'
		}
	}, {
		'featureType': 'green',
		'elementType': 'all',
		'stylers': {
			'visibility': 'off'
		}
	}, {
		'featureType': 'subway',
		'elementType': 'all',
		'stylers': {
			'visibility': 'off'
		}
	}, {
		'featureType': 'manmade',
		'elementType': 'all',
		'stylers': {
			'color': '#d1d1d1'
		}
	}, {
		'featureType': 'local',
		'elementType': 'all',
		'stylers': {
			'color': '#d1d1d1'
		}
	}, {
		'featureType': 'arterial',
		'elementType': 'labels',
		'stylers': {
			'visibility': 'off'
		}
	}, {
		'featureType': 'boundary',
		'elementType': 'all',
		'stylers': {
			'color': '#fefefe'
		}
	}, {
		'featureType': 'building',
		'elementType': 'all',
		'stylers': {
			'color': '#d1d1d1'
		}
	}, {
		'featureType': 'label',
		'elementType': 'labels.text.fill',
		'stylers': {
			'color': '#999999'
		}
	}]
};
/**
 * 获取品种列表
 * @param {Object} object
 */
function varietyLarge(object) {
	var large = $("#varietyLarge").val();
	var arr = [];
	switch(parseInt(large)) {
		case 1:
			arr = var1;
			break;
		case 2:
			arr = var2;
			break;
		case 3:
			arr = var3;
			break;
		case 4:
			arr = var4;
			break;
		case 5:
			arr = var5;
			break;
	}
	$("#variety").empty();
	var str = "<option value=''>请选择品种</option>";
	for(var i = 0; i < arr.length; i++) {
		str += "<option value=\"" + arr[i].name + "\">" + arr[i].name + "</option>";
	}
	$("#variety").append(str);
}
/**
 * 获取选中品种
 * @param {Object} object
 */
function variety(object) {
	var variety = $("#variety").val();
	var date = $("#date").val();
	var url = location.host + location.pathname + "?1=1";
	if(variety != null) {
		url += "&name=" + variety;
	}
	if(date != null) {
		url += "&date=" + date;
	}
	window.location.href = "http://" + url;
}

/**
 * 百度提供demo
 * @param {Object} data
 */
var convertData = function(data) {
	var res = [];
	for(var i = 0; i < data.length; i++) {
		//取出地址对应的经纬度
		var geoCoord = geoCoordMap[data[i].name];
		if(geoCoord) {
			res.push({
				name: data[i].name,
				value: geoCoord.concat(data[i].value)
			});
		}
	}
	return res;
};

function getPreFormatDate() {
	var curDate = new Date();
	var date = new Date(curDate.getTime() - 24 * 60 * 60 * 1000);
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if(month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if(strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}