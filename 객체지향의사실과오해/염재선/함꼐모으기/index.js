"use strict";
exports.__esModule = true;
var barista_1 = require("./barista");
var customer_1 = require("./customer");
var menu_1 = require("./menu");
var menu_item_1 = require("./menu-item");
var menuItems = [
    new menu_item_1.MenuItem('아메리카노', 3000),
    new menu_item_1.MenuItem('바닐라 라떼', 4000),
    new menu_item_1.MenuItem('카라멜 마끼아또', 4500)
];
var menu = new menu_1.Menu(menuItems);
var customer1 = new customer_1.Customer();
customer1.order('아메리카노', new menu_1.Menu(menuItems), new barista_1.Barista());
