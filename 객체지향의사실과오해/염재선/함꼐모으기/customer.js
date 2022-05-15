"use strict";
exports.__esModule = true;
exports.Customer = void 0;
var Customer = /** @class */ (function () {
    function Customer() {
    }
    // 
    Customer.prototype.order = function (menuName, menu, barista) {
        var menuItem = menu.choose(menuName);
        var coffee = barista.makeCoffee(menuItem);
    };
    return Customer;
}());
exports.Customer = Customer;
