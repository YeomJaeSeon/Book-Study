"use strict";
exports.__esModule = true;
exports.Barista = void 0;
var coffee_1 = require("./coffee");
var Barista = /** @class */ (function () {
    function Barista() {
    }
    Barista.prototype.makeCoffee = function (menuItem) {
        var coffee = new coffee_1.Coffee(menuItem);
        return coffee;
    };
    return Barista;
}());
exports.Barista = Barista;
