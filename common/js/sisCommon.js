/**********************************************************************************************************************/
/**
 * (s)가 null | "" | undefined 이면 (d)로 대체
 * @member global
 * @method nvl
 * @param {Object} s
 * @param {Object} d
 * @return {Object}
 */
function nvl(s, d) {
    return (s == null || s == "" || s == undefined) ? (d == null ? "" : d) : s;
}

/**
 * 스트링 배열(arr)의 각 요소(arr[n])를 시작첨자(s), 마지막첨자(e)를 붙인후 구분자(delim)로 연결하여 반환
 * @member global
 * @method joinStr
 * @param {Array} arr
 * @param {String} delim
 * @param {String} s
 * @param {String} e
 * @return {String}
 */
function joinStr(arr, delim, s, e) {
    delim = nvl(delim, ",");
    s = nvl(s);
    e = nvl(e);
    return $.protify(arr).inject([], function(array, item) {
        array.push(s + item + e);
        return array;
    }).join(delim);
}

/**
 * 배열의 중복을 제거하여 반환
 * @member jQuery
 * @method unique
 * @param {Array} arr
 * @return {Array}
 */
function unique(arr) {
    var i;
    var len = arr.length;
    var rtnArr = [];
    var tmp = {};
    for (i = 0; i < len; i++) {
        tmp[arr[i]] = 0;
    }
    for (i in tmp) {
        rtnArr.push(i);
    }
    return rtnArr;
}

/**
 * element 혹은 element id 를 입력받아 jQuery 객체로 리턴
 * @param {String|Element} element
 * @returns {Jquery}
 */
function returnjQueryObj(element) {
    return (typeof element == "string" && element != "") ? $("#" + element) : $(element);
}

/**
 * 텍스트정렬, 한영입력, 최대글자수제한 스타일 적용
 * @param e
 * @param textAlign
 * @param imeMode
 * @param maxLength
 * @return
 */
function applyStyle(e, textAlign, imeMode, maxLength) {
    e = returnjQueryObj(e);
    if (textAlign) e.css({ textAlign: textAlign });
    if (imeMode) e.css({ imeMode: imeMode });
    if (maxLength != null) {
        e.attr("maxLength", maxLength);
        e.attr("size", maxLength + 2);
    }
}

/**
 * class 추가 함수
 *
 * @member global
 * @method plusClass
 * @param {String}
 *          pageUrl
 * @param {String}
 *          pageMethod
 * @return none
 */
function plusClass(className, tagType, addClassName) {
    if ($("." + className).parents(tagType).hasClass(addClassName) != true) {
        console.log($(this));
        // $(this).addClass(addClassName);
    }
}

/**
 * 페이지 이동 함수
 *
 * @member global
 * @method movePage
 * @param {String}
 *          pageUrl
 * @param {char}
 *          pageMethod
 * @param {Array}
 *          optData
 * @return none
 */
function movePage(pageUrl, pageMethod, optData) {
    if (pageMethod == null || pageMethod == '' || pageMethod == undefined) {
        pageMethod = 'get';
    }

    var form = document.createElement("form");
    var param = new Array();
    // var input = new Array();

    form.action = pageUrl;
    form.method = pageMethod;

    // if(headerMenuActiveYn)
    // {
    // headerMenuActive();
    // }

    // param.push([ 'optData', optData ]);
    //
    // for (var i = 0; i < param.length; i++)
    // {
    // input[i] = document.createElement("input");
    // input[i].setAttribute("type", "hidden");
    // input[i].setAttribute("name", param[i][0]);
    // input[i].setAttribute("value", param[i][1]);
    //
    // form.appendChild(input[i]);
    // }

    document.body.appendChild(form);

    form.submit();
}

function fileUpload() {
    var fileTarget = $('.filebox .upload-hidden');
    fileTarget.on('change', function() {
        if (window.FileReader) {
            // 파일명 추출
            var filename = $(this)[0].files[0].name;
        } else {
            // Old IE 파일명 추출
            var filename = $(this).val().split('/').pop().split('\\').pop();
        };
        $(this).siblings('.upload-name').val(filename);
    });

    // preview image
    var imgTarget = $('.preview-image .upload-hidden');

    imgTarget.on('change', function() {
        var parent = $(this).parent();
        parent.children('.upload-display').remove();

        if (window.FileReader) {
            // image 파일만
            if (!$(this)[0].files[0].type.match(/image\//))
                return;

            var reader = new FileReader();
            reader.onload = function(e) {
                var src = e.target.result;
                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="' + src + '" class="upload-thumb"></div></div>');
            }
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            $(this)[0].select();
            $(this)[0].blur();
            var imgSrc = document.selection.createRange().text;
            parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');
            var img = $(this).siblings('.upload-display').find('img');
            img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\"" + imgSrc + "\")";
        }
    });
}

function returnElapsedTime(time) {
    var min = 60 * 1000;
    var c = new Date()
    var d = new Date(time);
    var minsAgo = Math.floor((c - d) / (min));

    var result = {
        'raw': d.getFullYear() + '-' + (d.getMonth() + 1 > 9 ? '' : '0') + (d.getMonth() + 1) + '-' + (d.getDate() > 9 ? '' : '0') + d.getDate() + ' ' + (d.getHours() > 9 ? '' : '0') + d.getHours() + ':' + (d.getMinutes() > 9 ? '' : '0') + d.getMinutes() + ':' + (d.getSeconds() > 9 ? '' : '0') + d.getSeconds(),
        'formatted': '',
    };

    // 1시간 내
    if (minsAgo < 60)
        result.formatted = minsAgo + '분 전';
    // 하루 내
    else if (minsAgo < 60 * 24)
        result.formatted = Math.floor(minsAgo / 60) + '시간 전';
    // 하루 이상
    else
        result.formatted = Math.floor(minsAgo / 60 / 24) + '일 전';

    return result.formatted;
}

function datetimeConvert(v) {
    var format = new Date(v);
    var year = format.getFullYear();
    var month = format.getMonth() + 1;
    var date = format.getDate();
    var hour = format.getHours();
    var min = format.getMinutes();
    var sec = format.getSeconds();

    if (month < 10)
        month = '0' + month;

    if (date < 10)
        date = '0' + date;

    if (hour < 10)
        hour = '0' + hour;

    if (min < 10)
        min = '0' + min;

    if (sec < 10)
        sec = '0' + sec;

    return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
}

function perNoToDate(val) {
    var target = 0;
    var compare = "234567892345";
    for (var i = 0; i < 12; i++) {
        target += Number(val.charAt(i)) * Number(compare.charAt(i));
    }
    target = 11 - (target % 11);
    target = target % 10;
    return Number(val.charAt(12)) == target;
}

function mergeTableTr() {
    var mergeElement = "";
    var mergeCnt = 0;
    var mergeRowNum = 0;

    // dynamicRowspan이라는 attribute를 가진 tr을 대상으로 반복문을 실행
    $('tr[dynamicRowspan]').each(function(row) {

        if (row > 2) {
            var tr = $(this);
            var element = $(':first-child', tr).html();

            if (mergeElement != item) {
                mergeCnt = 1;
                mergeElement = element;
                mergeRowNum = Number(row);
            } else {
                mergeCnt = Number(mergeCnt) + 1;
                $("tr:eq(" + mergeRowNum + ") > td:first-child").attr('rowspan', mergeCnt);
                $('td:first-child', tr).remove();
            }
        }
    });
}

function base64ToFile(dataUrl) {
    var fileName = this.fileName;

    var arr = dataUrl.split(',');
    var mime = arr[0].match(/:(.*?);/)[1];
    var byteString = atob(arr[1]);
    var len = byteString.length;
    var u8arr = new Uint8Array(len);

    while (len--) {
        u8arr[len] = byteString.charCodeAt(len);
    }

    return new File([u8arr], fileName, { type: mime });
}

function isIE() {
    if (navigator.userAgent.indexOf("MSIE") >= 0 || navigator.userAgent.indexOf("Trident") >= 0)
        return true;
    else
        return false;
}

function startsWith(str, p) {
    return p == str.toString().substr(0, p.length);
}

function toTimeObject(time) {
    var year = time.substr(0, 4);
    var month = time.substr(4, 2) - 1; // 1월=0,12월=11
    var day = time.substr(6, 2);

    var hour = "00";
    try {
        hour = time.substr(8, 2);
    } catch (e) {}

    var min = "00";
    try {
        min = time.substr(10, 2);
    } catch (e) {}

    return new Date(year, month, day, hour, min);
}