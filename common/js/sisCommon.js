var PARANG = {
    /**
     * (s)가 null | "" | undefined 이면 (d)로 대체
     * @member global
     * @method nvl
     * @param {Object} s
     * @param {Object} d
     * @return {Object}
     */
    nvl: function(s, d) {
        return (s == null || s == "" || s == undefined) ? (d == null ? "" : d) : s;
    },

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
    joinStr: function(arr, delim, s, e) {
        delim = nvl(delim, ",");
        s = nvl(s);
        e = nvl(e);
        return $.protify(arr).inject([], function(array, item) {
            array.push(s + item + e);
            return array;
        }).join(delim);
    },

    /**
     * 배열의 중복을 제거하여 반환
     * @member jQuery
     * @method getUniqueArray
     * @param {Array} arr
     * @return {Array}
     */
    getUniqueArray: function(duplicateArray, type) {
        var uniqueArray = [];
        if (Array.isArray(duplicateArray)) {
            $.each(duplicateArray, function(index, element) {
                if ($.inArray(element, uniqueArray) === -1) uniqueArray.push(element);
            });
        } else {
            return;
        }

        return uniqueArray;

        // another
        // var names = ["Mike", "Matt", "Nancy", "Adam", "Jenny", "Nancy", "Carl"];
        // var uniqueNames = [];

        // $.each(names, function(i, el) {
        //     if ($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
        // });
        //---------
        // var names = ["Mike", "Matt", "Nancy", "Adam", "Jenny", "Nancy", "Carl"];

        // var uniq = names.reduce(function(a, b) {
        //     if (a.indexOf(b) < 0) a.push(b);
        //     return a;
        // }, []);

        // console.log(uniq, names) // [ 'Mike', 'Matt', 'Nancy', 'Adam', 'Jenny', 'Carl' ]
        //---------
        // // 한 줄로 표현
        // return names.reduce(function(a, b) { if (a.indexOf(b) < 0) a.push(b); return a; }, []);
        //---------
        // var uniq = names.slice() // 정렬하기 전에 복사본을 만든다.
        //     .sort(function(a, b) {
        //         return a - b;
        //     })
        //     .reduce(function(a, b) {
        //         if (a.slice(-1)[0] !== b) a.push(b); // slice(-1)[0] 을 통해 마지막 아이템을 가져온다.
        //         return a;
        //     }, []); //a가 시작될 때를 위한 비어있는 배열
        //---------
        // // 한 줄로 표현
        // return names.slice().sort(function(a, b) { return a - b }).reduce(function(a, b) { if (a.slice(-1)[0] !== b) a.push(b); return a; }, []);
    },

    /**
     * element 혹은 element id 를 입력받아 jQuery 객체로 리턴
     * @param {String|Element} element
     * @returns {Jquery}
     */
    getjQueryObj: function(element) {
        return (typeof element == "string" && element != "") ? $("#" + element) : $(element);
    },

    /**
     * 텍스트정렬, 한영입력, 최대글자수제한 스타일 적용
     * @param e
     * @param textAlign
     * @param imeMode
     * @param maxLength
     * @return
     */
    applyStyle: function(e, textAlign, imeMode, maxLength) {
        e = returnjQueryObj(e);
        if (textAlign) e.css({ textAlign: textAlign });
        if (imeMode) e.css({ imeMode: imeMode });
        if (maxLength != null) {
            e.attr("maxLength", maxLength);
            e.attr("size", maxLength + 2);
        }
    },

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
    plusClass: function(className, tagType, addClassName) {
        if ($("." + className).parents(tagType).hasClass(addClassName) != true) {
            console.log($(this));
            // $(this).addClass(addClassName);
        }
    },

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
    movePage: function(pageUrl, pageMethod, optData) {
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
    },

    fileUpload: function() {
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
    },

    getElapsedTime: function(time) {
        var min = 60 * 1000;
        var c = new Date()
        var d = new Date(time);
        var minsAgo = Math.floor((c - d) / (min));

        var result = {
            'raw': d.getFullYear() + '-' + (d.getMonth() + 1 > 9 ? '' : '0') + (d.getMonth() + 1) + '-' + (d.getDate() > 9 ? '' : '0') + d.getDate() + ' ' + (d.getHours() > 9 ? '' : '0') + d.getHours() + ':' + (d.getMinutes() > 9 ? '' : '0') + d.getMinutes() + ':' + (d.getSeconds() > 9 ? '' : '0') + d.getSeconds(),
            'formatted': '',
        };

        // 1시간 내
        if (minsAgo < 60) {
            result.formatted = minsAgo + '분 전';
        }
        // 하루 내
        else if (minsAgo < 60 * 24) {
            result.formatted = Math.floor(minsAgo / 60) + '시간 전';
        }
        // 하루 이상
        else {
            result.formatted = Math.floor(minsAgo / 60 / 24) + '일 전';
        }

        return result.formatted;
    },

    datetimeConvert: function(v) {
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
    },

    perNoToDate: function(val) {
        var target = 0;
        var compare = "234567892345";
        for (var i = 0; i < 12; i++) {
            target += Number(val.charAt(i)) * Number(compare.charAt(i));
        }
        target = 11 - (target % 11);
        target = target % 10;
        return Number(val.charAt(12)) == target;
    },

    mergeTableTr: function() {
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
    },

    base64ToFile: function(dataUrl) {
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
    },

    isIE: function() {
        if (navigator.userAgent.indexOf("MSIE") >= 0 || navigator.userAgent.indexOf("Trident") >= 0)
            return true;
        else
            return false;
    },

    startsWith: function(str, p) {
        return p == str.toString().substr(0, p.length);
    },

    toTimeObject: function(time) {
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
    },

    getMobileOs: function() {
        var userAgent = navigator.userAgent || navigator.vendor || window.opera;
        if (/windows phone/i.test(userAgent)) {
            return "Windows Phone";
        }
        if (/android/i.test(userAgent)) {
            return "Android";
        }
        // iOS detection from: http://stackoverflow.com/a/9039885/177710
        if (/iPad|iPhone|iPod/.test(userAgent) && !window.MSStream) {
            return "iOS";
        }
        return "unknown";
    },

    getMobileUUID: function() {
        var mobileUUID = "";
        var userAgent = navigator.userAgent;
        var indexOfDeviceId = userAgent.toLowerCase().indexOf("deviceid");

        if (indexOfDeviceId < 0) return "";

        mobileUUID = userAgent.substr(indexOfDeviceId, userAgent.length).trim();
        var arrUUID = mobileUUID.split(":");
        if (arrUUID != null && arrUUID.length > 1) {
            mobileUUID = arrUUID[1].trim();
        } else {
            mobileUUID = "";
        }

        return mobileUUID;
    },

    isApp: function(platform) {
        platform = platform || getMobileOS();

        //if(platform =="Android" && typeof API == "undefined" ) return; //이건 앱아니다
        //if(platform =="iOS" && (window.webkit == undefined || window.webkit.messageHandlers == undefined )) return; //이건 앱아니다
        var b = false;
        switch (platform) {
            case "Android":
                {
                    if (typeof API != "undefined") {
                        b = true;
                    }
                }
                break;
            case "iOS":
                {
                    if (window.webkit != undefined && window.webkit.messageHandlers != undefined) {
                        b = true;
                    }
                }
                break;
            case "unknown":
                {
                    b = false;
                }
                break;
        }

        return b;
    },

    maxStringConvert: function(str, limit) {
        var strL = str.length;
        var byte = 0;
        var cnt = 0;
        var c = "";
        var result = "";

        for (var i = 0; i < strL; i++) {
            char = str.charAt(i);
            if (escape(c).length > 4) {
                byte += 2;
            } else {
                byte++;
            }
            if (byte <= limit) {
                strL = i + 1;
            }
        }
        if (byte > limit) {
            result = str.substr(0, strL) + "...";
        } else {
            result = str;
        }

        return result;
    },

    getBrowserType: function() {
        var _ua = navigator.userAgent;
        var rv = -1;
        //IE 11,10,9,8
        var trident = _ua.match(/Trident\/(\d.\d)/i);
        if (trident != null) {
            if (trident[1] == "7.0") return rv = "IE" + 11;
            if (trident[1] == "6.0") return rv = "IE" + 10;
            if (trident[1] == "5.0") return rv = "IE" + 9;
            if (trident[1] == "4.0") return rv = "IE" + 8;
        }
        //IE 7...
        if (navigator.appName == 'Microsoft Internet Explorer') return rv = "IE" + 7;
        //other
        var agt = _ua.toLowerCase();
        if (agt.indexOf("chrome") != -1) return 'Chrome';
        if (agt.indexOf("opera") != -1) return 'Opera';
        if (agt.indexOf("staroffice") != -1) return 'Star Office';
        if (agt.indexOf("webtv") != -1) return 'WebTV';
        if (agt.indexOf("beonex") != -1) return 'Beonex';
        if (agt.indexOf("chimera") != -1) return 'Chimera';
        if (agt.indexOf("netpositive") != -1) return 'NetPositive';
        if (agt.indexOf("phoenix") != -1) return 'Phoenix';
        if (agt.indexOf("firefox") != -1) return 'Firefox';
        if (agt.indexOf("safari") != -1) return 'Safari';
        if (agt.indexOf("skipstone") != -1) return 'SkipStone';
        if (agt.indexOf("netscape") != -1) return 'Netscape';
        if (agt.indexOf("mozilla/5.0") != -1) return 'Mozilla';
    }
}

var PerformanceCheck = {
    start() {
        var setTestFunc = setTimerFunc((a, b) => a + b, [1, 2], loopCount);
        var loopLoopCount = 10;
        var loopTestTime = getLoopTestTime(setTestFunc, loopLoopCount);
        console.log('덧셈함수를 ' + loopCount + '번 실행을 ' + loopLoopCount + '번 수행한 시간 : ' + loopTestTime);
        // 덧셈함수를 10000000번 실행을 10번 수행한 시간 : 203,173,179,206,187,174,196,169,179,181

        console.log(testTimeReport(loopLoopTestTime));
        // {average: 176.892578125, min: 167, max: 204, originalData: Array(10)}
    },
    timer(func, maxCount, params) {
        var start = new Date().getTime();
        maxCount = maxCount ? maxCount : 1;
        for (var n = 0; n < maxCount; n++) {
            /* 측정할 연산을 수행한다. -> 함수로 바꿉니다 */
            /* 동적으로 파라미터를 받아서 처리할 수 있게 apply 메서들르 사용합니다 */
            func.apply(null, params);
        }
        var elapsed = new Date().getTime() - start;
        return elapsed;
    },
    // timer를 장착한 함수와 반복횟수를 받는 함수를 받아야 하는 규약이 생깁니다.
    getLoopTestTime(setTimerFunc, count) {
        // 이제 결과값이 여러개가 오기 때문에 결과값을 담은 배열을 리턴해 줍니다
        var execTimes = [],
            timeCost;
        for (var i = 0; i < count; i++) {
            timeCost = setTimerFunc();
            execTimes.push(timeCost);
        }
        return execTimes;
    },
    // 실제 실행되는 함수인 timer를 실행시킬 함수로 고정시켜주는 함수를 만듭니다
    setTimerFunc(func, params, loopCount) {
        return lazyExec(timer, [lazyExec(func, params), loopCount]);
    },
    testTimeReport(costTimes) {
        var copied = costTimes.slice(); // 값만 복사해서 원본의 훼손을 막습니다
        return {
            'average': copied.reduce((a, b) => (a + b) / 2),
            'min': copied.sort((a, b) => a - b)[0],
            'max': copied.sort((a, b) => a - b)[copied.length - 1],
            'originalData': costTimes
        }
    },
}

// (function() {
//     String.implement({
//         slugify: function(replace) {
//             if (!replace) replace = '-';
//             var str = this.toString().tidy().standardize().replace(/[\s\.]+/g, replace).toLowerCase().replace(new RegExp('[^a-z0-9' + replace + ']', 'g'), replace).replace(new RegExp(replace + '+', 'g'), replace);
//             if (str.charAt(str.length - 1) == replace) str = str.substring(0, str.length - 1);
//             return str;
//         }
//     });
// })();
