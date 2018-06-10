(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error('Cannot find module "' + req + '".');
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n  <div id=\"page-wrapper\">\n    <router-outlet></router-outlet>\n  </div>\n\n\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'app';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _nav_nav_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./nav/nav.component */ "./src/app/nav/nav.component.ts");
/* harmony import */ var _reservation_check_reservation_check_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./reservation-check/reservation-check.component */ "./src/app/reservation-check/reservation-check.component.ts");
/* harmony import */ var _overview_overview_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./overview/overview.component */ "./src/app/overview/overview.component.ts");
/* harmony import */ var _reservation_check_reservation_datepicker_reservation_datepicker_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./reservation-check/reservation-datepicker/reservation-datepicker.component */ "./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.ts");
/* harmony import */ var _reservation_check_reservation_categories_reservation_categories_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./reservation-check/reservation-categories/reservation-categories.component */ "./src/app/reservation-check/reservation-categories/reservation-categories.component.ts");
/* harmony import */ var _reservation_check_reservation_personaldata_reservation_personaldata_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./reservation-check/reservation-personaldata/reservation-personaldata.component */ "./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.ts");
/* harmony import */ var _outlet_wrapper__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ./outlet-wrapper */ "./src/app/outlet-wrapper.ts");
/* harmony import */ var _success_success_component__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./success/success.component */ "./src/app/success/success.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};















var appRoutes = [
    {
        path: '',
        redirectTo: 'overview',
        pathMatch: 'full'
    },
    {
        path: 'reservation-check',
        component: _reservation_check_reservation_check_component__WEBPACK_IMPORTED_MODULE_8__["ReservationCheckComponent"],
        children: [
            {
                path: '',
                redirectTo: '/reservation-check/(reservation-datepicker-aux:reservation-datepicker)',
                pathMatch: 'full'
            },
            {
                path: 'reservation-datepicker',
                component: _reservation_check_reservation_datepicker_reservation_datepicker_component__WEBPACK_IMPORTED_MODULE_10__["ReservationDatepickerComponent"],
                data: {
                    animation: 'reservation-datepicker'
                },
                outlet: 'reservation-datepicker-aux'
            },
            {
                path: 'reservation-categories',
                component: _reservation_check_reservation_categories_reservation_categories_component__WEBPACK_IMPORTED_MODULE_11__["ReservationCategoriesComponent"],
                data: {
                    animation: 'reservation-categories'
                },
                outlet: 'reservation-categories-aux'
            },
            {
                path: 'reservation-personaldata',
                component: _reservation_check_reservation_personaldata_reservation_personaldata_component__WEBPACK_IMPORTED_MODULE_12__["ReservationPersonaldataComponent"],
                data: {
                    animation: 'reservation-personaldata'
                },
                outlet: 'reservation-personaldata-aux'
            },
        ]
    },
    {
        path: 'overview',
        component: _overview_overview_component__WEBPACK_IMPORTED_MODULE_9__["OverviewComponent"]
    },
    {
        path: 'success',
        component: _success_success_component__WEBPACK_IMPORTED_MODULE_14__["SuccessComponent"]
    }
];
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"],
                _reservation_check_reservation_check_component__WEBPACK_IMPORTED_MODULE_8__["ReservationCheckComponent"],
                _overview_overview_component__WEBPACK_IMPORTED_MODULE_9__["OverviewComponent"],
                _nav_nav_component__WEBPACK_IMPORTED_MODULE_7__["NavComponent"],
                _reservation_check_reservation_datepicker_reservation_datepicker_component__WEBPACK_IMPORTED_MODULE_10__["ReservationDatepickerComponent"],
                _reservation_check_reservation_categories_reservation_categories_component__WEBPACK_IMPORTED_MODULE_11__["ReservationCategoriesComponent"],
                _reservation_check_reservation_personaldata_reservation_personaldata_component__WEBPACK_IMPORTED_MODULE_12__["ReservationPersonaldataComponent"],
                _outlet_wrapper__WEBPACK_IMPORTED_MODULE_13__["OutletWrapper"],
                _success_success_component__WEBPACK_IMPORTED_MODULE_14__["SuccessComponent"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClientModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_3__["BrowserAnimationsModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forRoot(appRoutes, { enableTracing: true } // for debugging
                )
            ],
            providers: [],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_6__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/nav/nav.component.css":
/*!***************************************!*\
  !*** ./src/app/nav/nav.component.css ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/nav/nav.component.html":
/*!****************************************!*\
  !*** ./src/app/nav/nav.component.html ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<header id=\"header\" class=\"container\">\n  <div class=\"row\">\n    <div class=\"12u\">\n\n      <!-- Logo -->\n        <h1><a href=\"#\" id=\"logo\">Roomix</a></h1>\n\n      <!-- Nav -->\n        <nav id=\"nav\" >\n          <a routerLink=\"/overview\" routerLinkeActive=\"active\">Homepage</a>\n          <a routerLink=\"/reservation-check\" routerLinkActive=\"active\">Reservation</a>\n        </nav>\n    </div>\n  </div>\n</header>\n"

/***/ }),

/***/ "./src/app/nav/nav.component.ts":
/*!**************************************!*\
  !*** ./src/app/nav/nav.component.ts ***!
  \**************************************/
/*! exports provided: NavComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NavComponent", function() { return NavComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var NavComponent = /** @class */ (function () {
    function NavComponent() {
    }
    NavComponent.prototype.ngOnInit = function () {
    };
    NavComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-nav',
            template: __webpack_require__(/*! ./nav.component.html */ "./src/app/nav/nav.component.html"),
            styles: [__webpack_require__(/*! ./nav.component.css */ "./src/app/nav/nav.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], NavComponent);
    return NavComponent;
}());



/***/ }),

/***/ "./src/app/outlet-wrapper.ts":
/*!***********************************!*\
  !*** ./src/app/outlet-wrapper.ts ***!
  \***********************************/
/*! exports provided: OutletWrapper */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "OutletWrapper", function() { return OutletWrapper; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var OutletWrapper = /** @class */ (function () {
    function OutletWrapper() {
        this.visible = "none";
    }
    OutletWrapper.prototype.wrap = function () {
        this.visible = "";
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostBinding"])('style.display'),
        __metadata("design:type", Object)
    ], OutletWrapper.prototype, "visible", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["HostListener"])('document:wrapUp'),
        __metadata("design:type", Function),
        __metadata("design:paramtypes", []),
        __metadata("design:returntype", void 0)
    ], OutletWrapper.prototype, "wrap", null);
    OutletWrapper = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Directive"])({
            selector: '[outletWrapper]'
        })
    ], OutletWrapper);
    return OutletWrapper;
}());



/***/ }),

/***/ "./src/app/overview/overview.component.css":
/*!*************************************************!*\
  !*** ./src/app/overview/overview.component.css ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/overview/overview.component.html":
/*!**************************************************!*\
  !*** ./src/app/overview/overview.component.html ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div id=\"header-wrapper\">\n    <app-nav></app-nav>\n  <div id=\"banner\">\n    <div class=\"container\">\n      <div class=\"row\">\n        <div class=\"6u 12u(mobile)\">\n\n          <!-- Banner Copy -->\n            <p>To make a quick reservation...</p>\n            <a routerLink=\"/reservation-check\" routerLinkActive=\"active\" class=\"button-big\">Reservation, click me!</a>\n\n        </div>\n        <div class=\"6u 12u(mobile)\">\n\n          <!-- Banner Image -->\n            <img src=\"assets/images/roomix_logoBlack.png\" alt=\"\" width=\"550\" height=\"120\"/>\n\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n<!-- Features -->\n<div id=\"features-wrapper\">\n  <div id=\"features\">\n    <div class=\"container\">\n      <div class=\"row\">\n        <div class=\"3u 12u(mobile)\" style=\"width:33%;\">\n\n          <!-- Feature #1 -->\n            <section>\n              <a href=\"#\" class=\"bordered-feature-image\"><img src=\"assets/images/singleroom.PNG\" alt=\"\" /></a>\n              <h2>Single Room</h2>\n              <p>\n                Here you can see a single room!\n              </p>\n            </section>\n\n        </div>\n        <div class=\"3u 12u(mobile)\" style=\"width:33%;\">\n\n          <!-- Feature #2 -->\n            <section>\n              <a href=\"#\" class=\"bordered-feature-image\"><img src=\"assets/images/doubleroom.PNG\" alt=\"\" /></a>\n              <h2>Double Room</h2>\n              <p>\n                Here you can see a double room!\n              </p>\n            </section>\n\n        </div>\n        <div class=\"3u 12u(mobile)\" style=\"width:33%;\">\n\n          <!-- Feature #3 -->\n            <section>\n              <a href=\"#\" class=\"bordered-feature-image\"><img src=\"assets/images/suite.PNG\" alt=\"\" /></a>\n              <h2>Suite</h2>\n              <p>\n                Here you can see a suite!\n              </p>\n            </section>\n\n        </div>\n      </div>\n    </div>\n  </div>\n</div>\n\n\n\n"

/***/ }),

/***/ "./src/app/overview/overview.component.ts":
/*!************************************************!*\
  !*** ./src/app/overview/overview.component.ts ***!
  \************************************************/
/*! exports provided: OverviewComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "OverviewComponent", function() { return OverviewComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var OverviewComponent = /** @class */ (function () {
    function OverviewComponent() {
    }
    OverviewComponent.prototype.ngOnInit = function () {
    };
    OverviewComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-overview',
            template: __webpack_require__(/*! ./overview.component.html */ "./src/app/overview/overview.component.html"),
            styles: [__webpack_require__(/*! ./overview.component.css */ "./src/app/overview/overview.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], OverviewComponent);
    return OverviewComponent;
}());



/***/ }),

/***/ "./src/app/reservation-check/reservation-categories/reservation-categories.component.css":
/*!***********************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-categories/reservation-categories.component.css ***!
  \***********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/reservation-check/reservation-categories/reservation-categories.component.html":
/*!************************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-categories/reservation-categories.component.html ***!
  \************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "    <!-- Main Content -->\n    <section>\n    <header>\n      <h2>How many rooms do you need?</h2>\n    </header>\n    <ul class=\"link-list\" *ngFor=\"let category of categories; trackBy: trackCategory; let idx = index;\">\n        <li>        \n            <div class=\"uk-inline\">\n                <a class=\"uk-form-icon uk-form-icon-flip\" style=\"text-decoration: none;\" (click)=\"increment(idx)\"><i class=\"fas fa-plus-circle\"></i></a>\n                <a class=\"uk-form-icon\" style=\"text-decoration: none;\" ><i class=\"fas fa-minus-circle\" (click)=\"decrement(idx)\"></i></a>\n                <input class=\"uk-input uk-form-width-small\" type=\"text\" [value]=\"categoryCount[idx].num\" >\n            </div>\n            {{ category.categoryName }}\n        </li>\n    </ul>\n    <div [hidden]=\"!displayPrice\">\n        Total price: {{ totalPrice }}\n    </div>\n    <a (click)=\"next()\" [hidden]=\"!nextVisible\">Next</a>\n    </section>"

/***/ }),

/***/ "./src/app/reservation-check/reservation-categories/reservation-categories.component.ts":
/*!**********************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-categories/reservation-categories.component.ts ***!
  \**********************************************************************************************/
/*! exports provided: ReservationCategoriesComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReservationCategoriesComponent", function() { return ReservationCategoriesComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _slide_in_animation__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../slide-in-animation */ "./src/app/reservation-check/slide-in-animation.ts");
/* harmony import */ var _service_rest_price_loader_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../service/rest/price-loader.service */ "./src/app/service/rest/price-loader.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _reservation_check_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../reservation-check.component */ "./src/app/reservation-check/reservation-check.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ReservationCategoriesComponent = /** @class */ (function () {
    function ReservationCategoriesComponent(priceLoader, router, parent) {
        this.priceLoader = priceLoader;
        this.router = router;
        this.parent = parent;
        this.totalPrice = "";
        this.displayPrice = false;
        this.nextVisible = true;
        this.categories = [];
        this.categoryCount = [
            {
                num: 0
            },
            {
                num: 0
            },
            {
                num: 0
            }
        ];
    }
    ReservationCategoriesComponent.prototype.ngOnInit = function () {
        this.categories = this.parent.categories;
        if (this.parent.alertVisible == true) {
            this.nextVisible = false;
        }
    };
    ReservationCategoriesComponent.prototype.next = function () {
        var _this = this;
        this.router.navigate([
            'reservation-check',
            {
                outlets: {
                    'reservation-personaldata-aux': ['reservation-personaldata']
                }
            },
        ], { skipLocationChange: true });
        this.parent.personaldataVisible = true;
        this.parent.categoryCount = this.categoryCount;
        this.priceLoader.sendRequest(this.categories, this.categoryCount, this.parent.startDate, this.parent.endDate).subscribe(function (data) {
            _this.totalPrice = data["price"];
            _this.displayPrice = true;
        });
    };
    ReservationCategoriesComponent.prototype.trackCategory = function (index, category) {
        return category ? category.categoryId : undefined;
    };
    ReservationCategoriesComponent.prototype.increment = function (index) {
        console.log(this.categories[index].freeRooms);
        if (this.categoryCount[index].num < this.categories[index].freeRooms) {
            this.categoryCount[index].num += 1;
        }
    };
    ReservationCategoriesComponent.prototype.decrement = function (index) {
        this.categoryCount[index].num > 0 ? this.categoryCount[index].num -= 1 : this.categoryCount[index].num = 0;
    };
    ReservationCategoriesComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-reservation-categories',
            template: __webpack_require__(/*! ./reservation-categories.component.html */ "./src/app/reservation-check/reservation-categories/reservation-categories.component.html"),
            styles: [__webpack_require__(/*! ./reservation-categories.component.css */ "./src/app/reservation-check/reservation-categories/reservation-categories.component.css")],
            providers: [
                _service_rest_price_loader_service__WEBPACK_IMPORTED_MODULE_2__["PriceLoaderService"]
            ],
            animations: [_slide_in_animation__WEBPACK_IMPORTED_MODULE_1__["slideInAnimation"]],
            host: { '[@routerAnimation]': '' }
        }),
        __metadata("design:paramtypes", [_service_rest_price_loader_service__WEBPACK_IMPORTED_MODULE_2__["PriceLoaderService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"],
            _reservation_check_component__WEBPACK_IMPORTED_MODULE_4__["ReservationCheckComponent"]])
    ], ReservationCategoriesComponent);
    return ReservationCategoriesComponent;
}());



/***/ }),

/***/ "./src/app/reservation-check/reservation-check.component.css":
/*!*******************************************************************!*\
  !*** ./src/app/reservation-check/reservation-check.component.css ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".centered-flex-box-100{\r\n    display:flex;\r\n    height:100px;\r\n    justify-content: center;\r\n    align-items: center;\r\n}"

/***/ }),

/***/ "./src/app/reservation-check/reservation-check.component.html":
/*!********************************************************************!*\
  !*** ./src/app/reservation-check/reservation-check.component.html ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n\t<!-- <body class=\"subpage\"> -->\n\t\t<!-- <div id=\"page-wrapper\"> -->\n\n\t\t\t<!-- Header -->\n\t\t\t\t<div id=\"header-wrapper\">\n\t\t\t\t\t<header id=\"header\" class=\"container\">\n\t\t\t\t\t\t<div class=\"row\">\n\t\t\t\t\t\t\t<div class=\"12u\">\n\n\t\t\t\t\t\t\t\t<!-- Logo -->\n\t\t\t\t\t\t\t\t\t<h1><a href=\"#\" id=\"logo\">Roomix Reservation</a></h1>\n\n\t\t\t\t\t\t\t\t<!-- Nav -->\n\t\t\t\t\t\t\t\t\t<nav id=\"nav\">\n\t\t\t\t\t\t\t\t\t\t<a routerLink=\"/overview\" routerLinkActive=\"active\">Homepage</a>\n\t\t\t\t\t\t\t\t\t</nav>\n\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</header>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"uk-alert-danger\" style=\"margin:20px;\" [hidden]=\"!alertVisible\" uk-alert>\n\t\t\t\t\t\t<a class=\"uk-alert-close\" uk-close></a>\n\t\t\t\t\t\t<p>{{ errorMessage }}</p>\n\t\t\t\t\t</div>\n\t\t\t\t<div class=\"centered-flex-box-100\"  id=\"region-seperator\">\n\t\t\t\t\t<ul class=\"uk-breadcrumb\" style=\"color:#000000;opacity: 1;\">\n\t\t\t\t\t\t<li><span>Time span</span></li>\n\t\t\t\t\t\t<li><a (click)=\"todo()\">Categories</a></li>\n\t\t\t\t\t\t<li><a (click)=\"todo()\">Personal Data</a></li>\n\t\t\t\t\t\t<li></li>\n\t\t\t\t\t</ul>\n\t\t\t\t</div>\n\t\t\t<!-- Content -->\n\t\t\t\t<div class=\"content-flex-container\" [@routerAnimation]=\"getRouteAnimation(route)\">\n\t\t\t\t\t<div class=\"content-flex\"  [hidden]=\"!datepickerVisible\">\n\t\t\t\t\t\t<router-outlet #route=\"outlet\" name=\"reservation-datepicker-aux\"></router-outlet>\n\t\t\t\t\t</div>\n\t\t\t\t\t<div class=\"content-flex\" [hidden]=\"!categoryVisible\">\n\t\t\t\t\t\t<router-outlet #route=\"outlet\" name=\"reservation-categories-aux\"></router-outlet>\n\t\t\t\t\t</div>\t\n\t\t\t\t\t<div class=\"content-flex\" [hidden]=\"!personaldataVisible\">\n\t\t\t\t\t\t<router-outlet #route=\"outlet\" name=\"reservation-personaldata-aux\"></router-outlet>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\n\t\t<!-- </div> -->\n  <!-- </body> -->\n\n\n"

/***/ }),

/***/ "./src/app/reservation-check/reservation-check.component.ts":
/*!******************************************************************!*\
  !*** ./src/app/reservation-check/reservation-check.component.ts ***!
  \******************************************************************/
/*! exports provided: ReservationCheckComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReservationCheckComponent", function() { return ReservationCheckComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _slide_in_animation__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./slide-in-animation */ "./src/app/reservation-check/slide-in-animation.ts");
/* harmony import */ var _service_data_data_transfer_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../service/data/data-transfer.service */ "./src/app/service/data/data-transfer.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var ReservationCheckComponent = /** @class */ (function () {
    function ReservationCheckComponent(dataTransfer) {
        this.dataTransfer = dataTransfer;
        this.categories = [];
        this.categoryVisible = false;
        this.datepickerVisible = false;
        this.personaldataVisible = false;
        this.alertVisible = false;
        this.errorMessage = "";
    }
    ReservationCheckComponent.prototype.ngOnInit = function () {
    };
    ReservationCheckComponent.prototype.getRouteAnimation = function (outlet) {
        return outlet.activatedRouteData.animation;
    };
    ReservationCheckComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-reservation-check',
            template: __webpack_require__(/*! ./reservation-check.component.html */ "./src/app/reservation-check/reservation-check.component.html"),
            styles: [__webpack_require__(/*! ./reservation-check.component.css */ "./src/app/reservation-check/reservation-check.component.css")],
            animations: [
                _slide_in_animation__WEBPACK_IMPORTED_MODULE_1__["slideInAnimation"]
            ],
            providers: [
                _service_data_data_transfer_service__WEBPACK_IMPORTED_MODULE_2__["DataTransferService"]
            ]
        }),
        __metadata("design:paramtypes", [_service_data_data_transfer_service__WEBPACK_IMPORTED_MODULE_2__["DataTransferService"]])
    ], ReservationCheckComponent);
    return ReservationCheckComponent;
}());



/***/ }),

/***/ "./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.css":
/*!***********************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.css ***!
  \***********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.html":
/*!************************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.html ***!
  \************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\t\t\t\n        <!-- Left Sidebar -->\n          <section>\n            <header>\n              <h2>Please enter the time to check if we have a room for you!</h2><br>\n            </header>\n            <fieldset id=\"formLayout\">\n              <p>\n                <label for=\"startDate\">Start Date </label>\n                <input [(ngModel)]=\"startDate\" [attr.disabled]=\"!inputEnabled ? '' : null\" type=\"date\" id=\"startDate\" class=\"uk-form-width-medium\" (change)=\"dateCheck()\"/>\n              </p>\n              <p>\n                <label for=\"endDate\">End Date </label>\n                <input [(ngModel)]=\"endDate\"  [attr.disabled]=\"!inputEnabled ? '' : null\" type=\"date\" id=\"endDate\" class=\"uk-form-width-medium\" (change)=\"dateCheck()\"/>\n              </p>\n            </fieldset><br>\n          </section>\n"

/***/ }),

/***/ "./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.ts":
/*!**********************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.ts ***!
  \**********************************************************************************************/
/*! exports provided: ReservationDatepickerComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReservationDatepickerComponent", function() { return ReservationDatepickerComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _slide_in_animation__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../slide-in-animation */ "./src/app/reservation-check/slide-in-animation.ts");
/* harmony import */ var _service_rest_reservation_check_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../service/rest/reservation-check.service */ "./src/app/service/rest/reservation-check.service.ts");
/* harmony import */ var _service_validation_date_validation_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../service/validation/date-validation.service */ "./src/app/service/validation/date-validation.service.ts");
/* harmony import */ var _reservation_check_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../reservation-check.component */ "./src/app/reservation-check/reservation-check.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var ReservationDatepickerComponent = /** @class */ (function () {
    function ReservationDatepickerComponent(reservationCheckService, router, dateValidation, parent) {
        this.reservationCheckService = reservationCheckService;
        this.router = router;
        this.dateValidation = dateValidation;
        this.parent = parent;
        this.inputEnabled = true;
        this.wrapUpEvent = new CustomEvent('wrapUp');
        parent.datepickerVisible = true;
    }
    ReservationDatepickerComponent.prototype.ngOnInit = function () {
        this.datepickerVisible = true;
    };
    ReservationDatepickerComponent.prototype.checkReservation = function () {
        var _this = this;
        this.reservationCheckService.sendRequest(this.startDate, this.endDate).subscribe(function (data) {
            var i = 0;
            data.forEach(function (element) {
                i += 1;
                _this.parent.categories.push(element);
            });
            if (i == 0) {
                _this.parent.errorMessage = "Sorry, there are no more rooms available";
                _this.parent.alertVisible = true;
            }
        });
    };
    ReservationDatepickerComponent.prototype.dateCheck = function () {
        if (this.dateValidation.validate(this.startDate, this.endDate)) {
            this.inputEnabled = false;
            this.parent.startDate = this.startDate;
            this.parent.endDate = this.endDate;
            this.checkReservation();
            this.next();
        }
    };
    ReservationDatepickerComponent.prototype.next = function () {
        this.router.navigate([
            '/reservation-check',
            {
                outlets: {
                    'reservation-categories-aux': ['reservation-categories']
                }
            },
        ], { skipLocationChange: true });
        this.parent.categoryVisible = true;
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Boolean)
    ], ReservationDatepickerComponent.prototype, "datepickerVisible", void 0);
    ReservationDatepickerComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-reservation-datepicker',
            template: __webpack_require__(/*! ./reservation-datepicker.component.html */ "./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.html"),
            styles: [__webpack_require__(/*! ./reservation-datepicker.component.css */ "./src/app/reservation-check/reservation-datepicker/reservation-datepicker.component.css")],
            providers: [_service_rest_reservation_check_service__WEBPACK_IMPORTED_MODULE_3__["ReservationCheckService"]],
            animations: [_slide_in_animation__WEBPACK_IMPORTED_MODULE_2__["slideInAnimation"]],
            host: { '[@routerAnimation]': '' }
        }),
        __metadata("design:paramtypes", [_service_rest_reservation_check_service__WEBPACK_IMPORTED_MODULE_3__["ReservationCheckService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _service_validation_date_validation_service__WEBPACK_IMPORTED_MODULE_4__["DateValidationService"],
            _reservation_check_component__WEBPACK_IMPORTED_MODULE_5__["ReservationCheckComponent"]])
    ], ReservationDatepickerComponent);
    return ReservationDatepickerComponent;
}());



/***/ }),

/***/ "./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.css":
/*!***************************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.css ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.html":
/*!****************************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.html ***!
  \****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "    <!-- Right Sidebar -->\n    <section>\n        <header>\n          <h2>Please enter your personal information here, so we can reserve a room for you</h2>\n        </header>\n        <fieldset id=\"formLayout\">\n          <p>\n              <label for=\"male-select\" style=\"width:40%;\">female <input checked=\"checked\" name=\"Anrede\" id=\"male-select\" type=\"radio\" value=\"male\" required/> </label>\n              <label for=\"female-select\">male <input name=\"Anrede\" type=\"radio\" id=\"female-select\" value=\"female\" required/></label> \n          </p>\n          <p>\n            <label for=\"firstname\">Firstname:</label>\n            <input name=\"Vorname\" id=\"firstname\" type=\"text\" [(ngModel)]=\"firstname\" required/>\n          </p>\n          <p>\n              <label for=\"lastname\">Lastname:</label>\n              <input name=\"Nachname\" id=\"lastname\" type=\"text\" [(ngModel)]=\"lastname\" required/>\n          </p>\n          <p>\n            <label for=\"mail-input\">E-Mail:</label>\n            <input name=\"Email\" id=\"mail-input\"   type=\"email\" [(ngModel)]=\"eMail\" required/>\n          </p>\t\n          <p>\n              <label for=\"phone-input\">Phone number:</label>\n              <input name=\"phone-input\" id=\"phone-input\"   type=\"tel\" [(ngModel)]=\"phoneNumber\" required/>\n          </p>\t\n          <p>\n            <label for=\"location-input\">Location:</label>\n            <input name=\"Wohnort\" id=\"location-input\"   type=\"text\" [(ngModel)]=\"location\" required/>\n          </p>\n          <p>\n              <label for=\"postcode-input\">Postal code:</label>\n              <input name=\"postcode-input\" id=\"postcode-input\"   type=\"text\" [(ngModel)]=\"postcode\" required/>\n          </p>\t\t\n          <p>\n            <label for=\"street-input\">Street:</label>\n            <input name=\"Straße\" id=\"street-input\"   type=\"text\" [(ngModel)]=\"street\" required/>\n          </p>\t\t\t\n          <p>\n            <label for=\"houseno-input\">House number:</label>\n            <input name=\"Hausnummer\" id=\"Hausnummer\"   type=\"text\" [(ngModel)]=\"houseNumber\" required/>\n          </p>\n          <p>\n            <label for=\"creditcard-input\">Credit Card:</label>\n            <input name=\"Kreditkarte\" id=\"Kreditkarte\" [(ngModel)]=\"creditCard\"  type=\"text\" min=\"5\" required/>\n          </p>\n          <p>\n              <label for=\"country-input\">Country:</label>\n              <input name=\"country-input\" id=\"country\" [(ngModel)]=\"country\"  type=\"text\" required/>\n          </p>\n          <p>\n              <button (click)=\"next()\" class=\"uk-button uk-button-primary\">Submit</button>\n          </p>\n        </fieldset>\n      </section>"

/***/ }),

/***/ "./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.ts":
/*!**************************************************************************************************!*\
  !*** ./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.ts ***!
  \**************************************************************************************************/
/*! exports provided: ReservationPersonaldataComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReservationPersonaldataComponent", function() { return ReservationPersonaldataComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _service_rest_reservation_request_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../service/rest/reservation-request.service */ "./src/app/service/rest/reservation-request.service.ts");
/* harmony import */ var _reservation_check_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../reservation-check.component */ "./src/app/reservation-check/reservation-check.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ReservationPersonaldataComponent = /** @class */ (function () {
    function ReservationPersonaldataComponent(reservationService, parent, router) {
        this.reservationService = reservationService;
        this.parent = parent;
        this.router = router;
        this.firstname = "";
        this.lastname = "";
        this.eMail = "";
        this.location = "";
        this.street = "";
        this.houseNumber = "";
        this.creditCard = "";
        this.phoneNumber = "";
        this.country = "";
        this.postcode = "";
    }
    ReservationPersonaldataComponent.prototype.ngOnInit = function () {
    };
    ReservationPersonaldataComponent.prototype.next = function () {
        var _this = this;
        var personalData = {
            fname: this.firstname,
            lname: this.lastname,
            eMail: this.eMail,
            place: this.location,
            street: this.street,
            housenumber: this.houseNumber,
            creditcard: this.creditCard,
            categoryRequests: [],
            country: this.country,
            postCode: this.postcode,
            phoneNumber: this.phoneNumber,
            getStartDate: this.parent.startDate,
            getEndDate: this.parent.endDate
        };
        this.reservationService.sendRequest(this.parent.categories, this.parent.categoryCount, personalData, this.parent.startDate, this.parent.endDate).subscribe(function (data) {
            var pdf = new Blob([data], {
                type: 'application/pdf'
            });
            /*
              Some javascript to download the pdf file
            */
            var url = window.URL.createObjectURL(pdf);
            var a = document.createElement('a');
            document.body.appendChild(a);
            a.setAttribute('style', 'display: none');
            a.href = url;
            a.download = "ReservationInfo";
            a.click();
            window.URL.revokeObjectURL(url);
            a.remove();
            _this.router.navigateByUrl("/success");
        }, function (error) {
            _this.parent.errorMessage = "Something went wrong";
            _this.parent.alertVisible = true;
        });
    };
    ReservationPersonaldataComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-reservation-personaldata',
            template: __webpack_require__(/*! ./reservation-personaldata.component.html */ "./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.html"),
            styles: [__webpack_require__(/*! ./reservation-personaldata.component.css */ "./src/app/reservation-check/reservation-personaldata/reservation-personaldata.component.css")]
        }),
        __metadata("design:paramtypes", [_service_rest_reservation_request_service__WEBPACK_IMPORTED_MODULE_2__["ReservationRequestService"],
            _reservation_check_component__WEBPACK_IMPORTED_MODULE_3__["ReservationCheckComponent"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], ReservationPersonaldataComponent);
    return ReservationPersonaldataComponent;
}());



/***/ }),

/***/ "./src/app/reservation-check/slide-in-animation.ts":
/*!*********************************************************!*\
  !*** ./src/app/reservation-check/slide-in-animation.ts ***!
  \*********************************************************/
/*! exports provided: slideInAnimation */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "slideInAnimation", function() { return slideInAnimation; });
/* harmony import */ var _angular_animations__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/animations */ "./node_modules/@angular/animations/fesm5/animations.js");

var slideInAnimation = Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["trigger"])('routerAnimation', [
    Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["transition"])('* <=> *', [
        Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':enter, :leave', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({}), { optional: true }),
        Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["group"])([
            Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':leave', [
                Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({ opacity: 1 }),
                Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animate"])('3s ease-in-out', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({ opacity: 0 })),
            ], { optional: true }),
            Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["query"])(':enter', [
                Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({ opacity: 0 }),
                Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["animate"])('3s ease-in-out', Object(_angular_animations__WEBPACK_IMPORTED_MODULE_0__["style"])({ opacity: 1 }))
            ], { optional: true })
        ]),
    ])
]);


/***/ }),

/***/ "./src/app/service/data/data-transfer.service.ts":
/*!*******************************************************!*\
  !*** ./src/app/service/data/data-transfer.service.ts ***!
  \*******************************************************/
/*! exports provided: DataTransferService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DataTransferService", function() { return DataTransferService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var DataTransferService = /** @class */ (function () {
    function DataTransferService() {
    }
    DataTransferService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [])
    ], DataTransferService);
    return DataTransferService;
}());



/***/ }),

/***/ "./src/app/service/rest/price-loader.service.ts":
/*!******************************************************!*\
  !*** ./src/app/service/rest/price-loader.service.ts ***!
  \******************************************************/
/*! exports provided: PriceLoaderService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PriceLoaderService", function() { return PriceLoaderService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var PriceLoaderService = /** @class */ (function () {
    function PriceLoaderService(httpClient) {
        this.httpClient = httpClient;
        this.url = 'http://localhost:8080/ReservationPrice';
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
                'Content-Type': 'application/json'
            })
        };
    }
    PriceLoaderService_1 = PriceLoaderService;
    PriceLoaderService.prototype.sendRequest = function (categories, categoryCount, startDate, endDate) {
        console.log(startDate);
        categoryCount.forEach(function (element, i) {
            if (element.num > 0) {
                var part = {
                    categoryID: categories[i].categoryId.toString(),
                    amount: element.num.toString(),
                    startDate: startDate,
                    endDate: endDate
                };
                PriceLoaderService_1.requestData.push(part);
            }
        });
        return this.httpClient.post(this.url, PriceLoaderService_1.requestData, this.httpOptions);
    };
    PriceLoaderService.requestData = [];
    PriceLoaderService = PriceLoaderService_1 = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], PriceLoaderService);
    return PriceLoaderService;
    var PriceLoaderService_1;
}());



/***/ }),

/***/ "./src/app/service/rest/reservation-check.service.ts":
/*!***********************************************************!*\
  !*** ./src/app/service/rest/reservation-check.service.ts ***!
  \***********************************************************/
/*! exports provided: ReservationCheckService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReservationCheckService", function() { return ReservationCheckService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var ReservationCheckService = /** @class */ (function () {
    function ReservationCheckService(httpClient) {
        this.httpClient = httpClient;
        this.url = 'http://localhost:8080/ReservationCheck';
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
                'Content-Type': 'application/json'
            })
        };
    }
    ReservationCheckService.prototype.sendRequest = function (startDate, endDate) {
        this.requestData = {
            startDate: startDate,
            endDate: endDate
        };
        return this.httpClient.post(this.url, this.requestData, this.httpOptions);
    };
    ReservationCheckService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], ReservationCheckService);
    return ReservationCheckService;
}());



/***/ }),

/***/ "./src/app/service/rest/reservation-request.service.ts":
/*!*************************************************************!*\
  !*** ./src/app/service/rest/reservation-request.service.ts ***!
  \*************************************************************/
/*! exports provided: ReservationRequestService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReservationRequestService", function() { return ReservationRequestService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var ReservationRequestService = /** @class */ (function () {
    function ReservationRequestService(httpClient) {
        this.httpClient = httpClient;
        this.url = 'http://localhost:8080/DoReservation';
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
                'Content-Type': 'application/json',
                'Accept': 'application/octet-stream',
            })
        };
    }
    ReservationRequestService.prototype.sendRequest = function (categories, categoryCount, request, startDate, endDate) {
        categoryCount.forEach(function (element, i) {
            if (element.num > 0) {
                var part = {
                    categoryID: categories[i].categoryId.toString(),
                    amount: element.num.toString(),
                    startDate: startDate,
                    endDate: endDate
                };
                request.categoryRequests.push(part);
            }
        });
        return this.httpClient.post(this.url, request, { responseType: 'blob' });
    };
    ReservationRequestService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], ReservationRequestService);
    return ReservationRequestService;
}());



/***/ }),

/***/ "./src/app/service/validation/date-validation.service.ts":
/*!***************************************************************!*\
  !*** ./src/app/service/validation/date-validation.service.ts ***!
  \***************************************************************/
/*! exports provided: DateValidationService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DateValidationService", function() { return DateValidationService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var DateValidationService = /** @class */ (function () {
    function DateValidationService() {
    }
    DateValidationService.prototype.validate = function (startDate, endDate) {
        if (startDate && endDate) {
            var startdate = new Date(startDate);
            var enddate = new Date(endDate);
            return (startdate < enddate);
        }
        return false;
    };
    DateValidationService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [])
    ], DateValidationService);
    return DateValidationService;
}());



/***/ }),

/***/ "./src/app/success/success.component.css":
/*!***********************************************!*\
  !*** ./src/app/success/success.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/success/success.component.html":
/*!************************************************!*\
  !*** ./src/app/success/success.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"uk-flex uk-flex-center\" style=\"height:100%\">\n    <div class=\"uk-card uk-card-default uk-card-body\" style=\"margin:30px\">\n        <div uk-alert-success>\n            <h3>Success</h3>\n            <p>Your reservation was saved. Download the PDF for further information. </p>\n            <a href=\"/index.html\">Back to the homepage</a>\n        </div>\n    </div>\n</div>\n\n"

/***/ }),

/***/ "./src/app/success/success.component.ts":
/*!**********************************************!*\
  !*** ./src/app/success/success.component.ts ***!
  \**********************************************/
/*! exports provided: SuccessComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SuccessComponent", function() { return SuccessComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var SuccessComponent = /** @class */ (function () {
    function SuccessComponent() {
    }
    SuccessComponent.prototype.ngOnInit = function () {
    };
    SuccessComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-success',
            template: __webpack_require__(/*! ./success.component.html */ "./src/app/success/success.component.html"),
            styles: [__webpack_require__(/*! ./success.component.css */ "./src/app/success/success.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], SuccessComponent);
    return SuccessComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\files\workspace_angular\roomix-web\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map