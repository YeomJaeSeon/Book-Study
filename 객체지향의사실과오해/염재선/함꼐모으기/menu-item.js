"use strict";
exports.__esModule = true;
exports.MenuItem = void 0;
var MenuItem = /** @class */ (function () {
    function MenuItem(name, price) {
        this.name = name;
        this.price = price;
    }
    MenuItem.prototype.cost = function () {
        return this.price;
    };
    MenuItem.prototype.getName = function () {
        return this.name;
    };
    return MenuItem;
}());
exports.MenuItem = MenuItem;
