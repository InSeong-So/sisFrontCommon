'use strict';

(function(g) {

    var validate = {};

    var isValidPhoneNum = function(str) {
        var reg = new RegExp(/^((13[0-9])|(147)|(15[^4,\D])|(17[0-9])|(18[0,0-9]))\d{8}$/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    var isValidEmail = function(str) {
        var reg = new RegExp(/[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    var isValidUrl = function(str) {
        //http或https协议类型网址
        var reg = new RegExp(/^((http)|(https))+:[^\s]+\.[^\s]*$/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    var isValidCarNo = function(str) {
        var reg = new RegExp(/^[A-Za-z]{1}[A-Za-z_0-9]{5}$/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    var isValidPostalCode = function(str) {
        var reg = new RegExp(/^[0-8]\d{5}(?!\d)$/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    var isValidChinese = function(str) {
        var reg = new RegExp(/^[\u4e00-\u9fa5]+$/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    var isValidIP = function(str) {
        var reg = new RegExp(/^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    var isValidIdCardNum = function(str) {
        str = str.toString().toLowerCase();
        var length = str.length;
        if (length !== 15 && length !== 18) return false;

        var provinceCodes = ["11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"];
        if (provinceCodes.indexOf(str.substr(0, 2)) === -1) return false;

        var year = 0;
        var reg;
        switch (length) {
            case 15:
                year = parseInt(str.substr(6, 2)) + 1900;
                if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
                    reg = new RegExp(/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/);
                } else {
                    reg = new RegExp(/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/);
                }

                return reg.test(str);

                break;

            case 18:
                year = parseInt(str.substr(6, 4));
                if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
                    reg = new RegExp(/^[1-9][0-9]{5}19|20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/);
                } else {
                    reg = new RegExp(/^[1-9][0-9]{5}19|20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/);
                }

                if (!reg.test(str)) {
                    return false;
                } else {
                    var S = parseInt(str.substr(0, 1)) * 7 +
                        parseInt(str.substr(1, 1)) * 9 +
                        parseInt(str.substr(2, 1)) * 10 +
                        parseInt(str.substr(3, 1)) * 5 +
                        parseInt(str.substr(4, 1)) * 8 +
                        parseInt(str.substr(5, 1)) * 4 +
                        parseInt(str.substr(6, 1)) * 2 +
                        parseInt(str.substr(7, 1)) * 1 +
                        parseInt(str.substr(8, 1)) * 6 +
                        parseInt(str.substr(9, 1)) * 3 +
                        parseInt(str.substr(10, 1)) * 7 +
                        parseInt(str.substr(11, 1)) * 9 +
                        parseInt(str.substr(12, 1)) * 10 +
                        parseInt(str.substr(13, 1)) * 5 +
                        parseInt(str.substr(14, 1)) * 8 +
                        parseInt(str.substr(15, 1)) * 4 +
                        parseInt(str.substr(16, 1)) * 2;

                    var Y = S % 11;
                    var M = "F";
                    var JYM = "10X98765432";
                    M = JYM.substr(Y, 1); //校验位
                    return M === str.substr(17, 1).toUpperCase()
                }
                break;

            default:
                return false;
        }

        return false;
    };

    var isValidTaxNo = function(str) {
        var reg = new RegExp(/[0-9]\d{13}([0-9]|X)$/);
        if (typeof str === 'string') return reg.test(str);
        return reg.test(str.toString());
    };

    validate = {
        VERSION: '1.0.0',

        isValidPhoneNum: isValidPhoneNum,

        isValidEmail: isValidEmail,

        isValidUrl: isValidUrl,

        isValidCarNo: isValidCarNo,

        isValidPostalCode: isValidPostalCode,

        isValidChinese: isValidChinese,

        isValidIP: isValidIP,

        isValidIdCardNum: isValidIdCardNum,

        isValidTaxNo: isValidTaxNo
    };

    g.validate = validate;
    g.V = validate;

    String.prototype.isValidPhoneNum = function() { return isValidPhoneNum(this); };
    String.prototype.isValidEmail = function() { return isValidEmail(this); };
    String.prototype.isValidUrl = function() { return isValidUrl(this); };
    String.prototype.isValidCarNo = function() { return isValidCarNo(this); };
    String.prototype.isValidPostalCode = function() { return isValidPostalCode(this); };
    String.prototype.isValidChinese = function() { return isValidChinese(this); };
    String.prototype.isValidIP = function() { return isValidIP(this); };
    String.prototype.isValidIdCardNum = function() { return isValidIdCardNum(this); };
    String.prototype.isValidTaxNo = function() { return isValidTaxNo(this); };

    Number.prototype.isValidPhoneNum = function() { return isValidPhoneNum(this); };
    Number.prototype.isValidPostalCode = function() { return isValidPostalCode(this); };

})(this);