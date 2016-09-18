// common utils
var util = {
  
  /* 
     is_empty(undefined) = true
     is_empty(null) = true
     is_empty('') = true
     else = false
  */
	is_empty : function (v) {
    return (
      (typeof (v) == "undefined") ||
      (v == null) ||
      (v.length <= 0)
    );
	},
  
  is_image : function (v) {
    return /^.*\.(png|jpg|bmp|gif|jpeg|PNG|JPG|BMP|GIF|JPEG)$/.test(v);
  },
  
  is_telphone : function (v) {
    return /^(\d{3,4}-)\d{7,8}$/.test(v);
  },
  
  is_cellphone : function (v) {
    return /^1(3|4|5|8)\d{9}$/.test(v);
  }
  
};

