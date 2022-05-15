"use strict";
exports.__esModule = true;
exports.Menu = void 0;
var Menu = /** @class */ (function () {
    function Menu(items) {
        this.items = items;
    }
    Menu.prototype.choose = function (name) {
        return this.items.find(function (each) { return each.getName() === name; });
    };
    return Menu;
}());
exports.Menu = Menu;
