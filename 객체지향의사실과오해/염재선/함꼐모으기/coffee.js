"use strict";
exports.__esModule = true;
exports.Coffee = void 0;
var Coffee = /** @class */ (function () {
    function Coffee(menuItem) {
        this.name = menuItem.getName();
        this.price = menuItem.cost();
    }
    return Coffee;
}());
exports.Coffee = Coffee;
