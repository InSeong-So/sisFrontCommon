/**
 *  Protify jQuery Plugin
 *  version 0.3
 *
 * Copyright (c) 2009 Josh Powell
 * Licensed under the MIT license.
 *
 *  * Date: 2009-02-04 11:45:50 (Wed, 04 Feb 2009)
 *
 */
(function ($) {
    var $break = {};
    var arrayFunc = {
        _each: function (iterator) {
            for (var i = 0, length = this.length; i < length; i++) {
                iterator(this[i]);
            }
        },

        all: function (iterator, context) {
            iterator = iterator || function (x) {
                return x;
            };
            var result = true;
            this.each(function (value, index) {
                result = result && !!iterator.call(context, value, index);
                if (!result) {
                    throw $break;
                }
            });
            return result;
        },

        any: function (iterator, context) {
            iterator = iterator || function (x) {
                return x;
            };
            var result = false;
            this.each(function (value, index) {
                if (result = !!iterator.call(context, value, index)) {
                    throw $break;
                }
            });
            return result;
        },

        clear: function () {
            this.length = 0;
            return this;
        },

        clone: function () {
            return $.protify([].concat(this));
        },

        collect: function (iterator, context) {
            iterator = iterator || function (x) {
                return x;
            };
            var results = $.protify([]);
            this.each(function (value, index) {
                results.push(iterator.call(context, value, index));
            });
            return results;
        },

        detect: function (iterator, context) {
            var result;
            this.each(function (value, index) {
                if (iterator.call(context, value, index)) {
                    result = value;
                    throw $break;
                }
            });
            return result;
        },

        compact: function () {
            return $.protify(this.select(function (value) {
                return value !== null;
            }));
        },

        each: function (iterator, context) {
            context = context || this;
            var index = 0;
            try {
                this._each(function (value) {
                    iterator.call(context, value, index++);
                });
            } catch (e) {
                if (e != $break) {
                    throw e;
                }
            }
            return this;
        },

        eachSlice: function (number, iterator, context) {
            var index = -number, slices = [], array = this.toArray();
            if (number < 1) {
                return array;
            }
            while ((index += number) < array.length) {
                slices.push(array.slice(index, index + number));
            }
            return $.protify($.protify(slices).collect(iterator, context));
        },

        extended: function () {
            return true;
        },

        findAll: function (iterator, context) {
            var results = $.protify([]);
            this.each(function (value, index) {
                if (iterator.call(context, value, index)) {
                    results.push(value);
                }
            });
            return results;
        },

        flatten: function () {
            return this.inject([], function (array, value) {
                $.protify(value);
                return $.protify(array.concat($.isArray(value) ?
                    value.flatten() : [value]));
            });
        },

        first: function () {
            return this[0];
        },

        grep: function (filter, iterator, context) {
            iterator = iterator || function (x) {
                return x;
            };
            var results = $.protify([]);
            if (typeof filter === 'string') {
                filter = new RegExp(filter);
            }

            this.each(function (value, index) {
                if (filter.test(value)) {
                    results.push(iterator.call(context, value, index));
                }
            });
            return results;
        },

        include: function (object) {
            if ($.isFunction(this.indexOf)) {
                if (this.indexOf(object) != -1) {
                    return true;
                }
            }

            var found = false;
            this.each(function (value) {
                if (value == object) {
                    found = true;
                    throw $break;
                }
            });
            return found;
        },

        indexOf: function (item, i) {
            i || (i = 0);
            var length = this.length;
            if (i < 0) i = length + i;
            for (; i < length; i++)
                if (this[i] === item) return i;
            return -1;
        },

        inGroupsOf: function (number, fillWith) {
            fillWith = fillWith ? null : fillWith;
            return this.eachSlice(number, function (slice) {
                while (slice.length < number) {
                    slice.push(fillWith);
                }
                return slice;
            });
        },

        inject: function (memo, iterator, context) {
            this.each(function (value, index) {
                memo = iterator.call(context, memo, value, index);
            });
            return memo;
        },

        inspect: function () {
            return '[' + this.map($.inspect).join(', ') + ']';
        },

        intersect: function (array) {
            $.protify(array);
            return this.uniq().findAll(function (item) {
                return array.detect(function (value) {
                    return item === value;
                });
            });
        },

        invoke: function (method) {
            var args = $.makeArray(arguments).slice(1);
            return this.map(function (value) {
                return value[method].apply(value, args);
            });
        },

        last: function () {
            return this[this.length - 1];
        },

        lastIndexOf: function (item, i) {
            i = isNaN(i) ? this.length : (i < 0 ? this.length + i : i) + 1;
            var n = $.protify(this.slice(0, i).reverse()).indexOf(item);
            return (n < 0) ? n : i - n - 1;
        },
        max: function (iterator, context) {
            iterator = iterator || function (x) {
                return x;
            };
            var result;
            this.each(function (value, index) {
                value = iterator.call(context, value, index);
                if (result == null || value >= result) {
                    result = value;
                }
            });
            return result;
        },

        min: function (iterator, context) {
            iterator = iterator || function (x) {
                return x;
            };
            var result;
            this.each(function (value, index) {
                value = iterator.call(context, value, index);
                if (result == null || value < result) {
                    result = value;
                }
            });
            return result;
        },

        partition: function (iterator, context) {
            iterator = iterator || function (x) {
                return x;
            };
            var trues = [], falses = [];
            this.each(function (value, index) {
                (iterator.call(context, value, index) ? trues : falses).push(value);
            });
            return [trues, falses];
        },

        pluck: function (property) {
            var results = $.protify([]);
            this.each(function (value) {
                results.push(value[property]);
            });
            return results;
        },

        purge: function () {
            return [].concat(this);
        },

        reduce: function () {
            return this.length > 1 ? this : this[0];
        },

        reject: function (iterator, context) {
            var results = $.protify([]);
            this.each(function (value, index) {
                if (!iterator.call(context, value, index)) {
                    results.push(value);
                }
            });
            return results;
        },

        size: function () {
            return this.length;
        },

        sortBy: function (iterator, context) {
            return this.map(
                function (value, index) {
                    return {
                        value: value,
                        criteria: iterator.call(context, value, index)
                    };
                }).sort(
                function (left, right) {
                    var a = left.criteria, b = right.criteria;
                    return a < b ? -1 : a > b ? 1 : 0;
                }).pluck('value');
        },

        toArray: function () {
            return $.protify(this.map());
        },

//  toJSON: function() {
//  var results = [];
//  this.each(function(object) {
//    var value = Object.toJSON(object);
//    if (!Object.isUndefined(value)) results.push(value);
//  });
//  return '[' + results.join(', ') + ']';
//},

        uniq: function (sorted) {
            return $.protify(this.inject([], function (array, value, index) {
                $.protify(array, true);
                if (0 === index || (sorted ? array.last() != value : !array.include(value))) {
                    array.push(value);
                }
                return array;
            }));
        },

        without: function () {
            var values = $.protify($.makeArray(arguments));
            return $.protify(this.select(function (value) {
                return !values.include(value);
            }));
        },

        zip: function () {
            var iterator = function (x) {
                return x;
            }, args = $.protify($.makeArray(arguments));
            if ($.isFunction(args.last())) {
                iterator = args.pop();
            }

            var collections = $.protify([this].concat(args)).map();
            return this.map(function (value, index) {
                return iterator(collections.pluck(index));
            });
        }
    };

    $.extend(arrayFunc, {
        map: arrayFunc.collect,
        find: arrayFunc.detect,
        select: arrayFunc.findAll,
        filter: arrayFunc.findAll,
        member: arrayFunc.include,
        entries: arrayFunc.toArray,
        every: arrayFunc.all,
        some: arrayFunc.any
    });

    $.protify = function (target, permanent) {
        if (permanent) {
            $.extend(target, arrayFunc);
            return target;
        }
        target = $.extend(target.slice(), arrayFunc);
        return target;
    };

})(jQuery);


function $A(array) {
    return $.protify(array)
}

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
    return $.protify(arr).inject([], function (array, item) {
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
function applyStyle(e, textAlign, imeMode, maxLength)
{
    e = returnjQueryObj(e);
    if ( textAlign ) e.css({textAlign: textAlign});
    if ( imeMode ) e.css({imeMode: imeMode});
    if ( maxLength != null )
    {
        e.attr("maxLength", maxLength);
        e.attr("size", maxLength + 2);
    }
}