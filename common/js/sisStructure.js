var HashTable = function () {
    this._limit = 8;
    this._storage = LimitedArray(this._limit);
};

HashTable.prototype.insert = function (k, v) {
    var index = getIndexBelowMaxForKey(k, this._limit);
    let value = this._storage.get(index);
    let obj = {};
    if (value) {
        obj = value;
        obj[k] = v;
        this._storage.set(index, obj);
    } else {
        obj[k] = v;
        this._storage.set(index, obj);
    }
};

HashTable.prototype.retrieve = function (k) {
    var index = getIndexBelowMaxForKey(k, this._limit);
    return this._storage.get(index)[k];
};

HashTable.prototype.remove = function (k) {
    var index = getIndexBelowMaxForKey(k, this._limit);
    this._storage.each(function (el, i) {
        if (index === i) {
            delete el[k];
        }
    });
};