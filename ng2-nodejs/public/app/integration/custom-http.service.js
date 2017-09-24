"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
var Rx_1 = require('rxjs/Rx');
var CustomHttp = (function (_super) {
    __extends(CustomHttp, _super);
    function CustomHttp(backend, defaultOptions) {
        _super.call(this, backend, defaultOptions);
        this.backend = backend;
        this.defaultOptions = defaultOptions;
    }
    CustomHttp.prototype.request = function (url, options) {
        var _this = this;
        return this.httpInterceptTrigger(function () { return _super.prototype.request.call(_this, url, _this.getRequestOptionArgs(options)); });
    };
    CustomHttp.prototype.get = function (url, options) {
        var _this = this;
        return this.httpInterceptTrigger(function () { return _super.prototype.get.call(_this, url, _this.getRequestOptionArgs(options)); });
    };
    CustomHttp.prototype.post = function (url, body, options) {
        var _this = this;
        return this.httpInterceptTrigger(function () { return _super.prototype.post.call(_this, url, body, _this.getRequestOptionArgs(options)); });
    };
    CustomHttp.prototype.put = function (url, body, options) {
        var _this = this;
        return this.httpInterceptTrigger(function () { return _super.prototype.put.call(_this, url, body, _this.getRequestOptionArgs(options)); });
    };
    CustomHttp.prototype.delete = function (url, options) {
        var _this = this;
        return this.httpInterceptTrigger(function () { return _super.prototype.delete.call(_this, url, _this.getRequestOptionArgs(options)); });
    };
    CustomHttp.prototype.getRequestOptionArgs = function (options) {
        if (!options) {
            options = new http_1.RequestOptions();
        }
        if (!options.headers) {
            options.headers = new http_1.Headers();
        }
        var contentTypes = options.headers.getAll('Content-Type');
        if (!contentTypes || !contentTypes.find(function (contentType) { return contentType.toLowerCase().includes('application/json'); })) {
            options.headers.append('Content-Type', 'application/json');
        }
        var allowCors = options.headers.getAll('Access-Control-Allow-Origin');
        if (!allowCors) {
            options.headers.append('Access-Control-Allow-Origin', '*');
        }
        return options;
    };
    CustomHttp.prototype.handleError = function (error) {
        // TODO add custom error handling here
        return Rx_1.Observable.throw(error || 'Server error');
    };
    /**
     * This method intercepts all http calls and handles error logging
     * @param httpCall
     * @returns {Observable<R>}
     */
    CustomHttp.prototype.httpIntercept = function (httpCall) {
        var _this = this;
        return httpCall().catch(function (err) { return _this.handleError(err); });
    };
    CustomHttp.prototype.httpInterceptTrigger = function (httpCall) {
        var _this = this;
        this.startHttpRequest();
        return this.httpIntercept(httpCall).finally(function () { return _this.endHttpRequest(); });
    };
    CustomHttp.prototype.startHttpRequest = function () {
        // aop point before http call
    };
    CustomHttp.prototype.endHttpRequest = function () {
        // aop point after http call
    };
    CustomHttp = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.ConnectionBackend, http_1.RequestOptions])
    ], CustomHttp);
    return CustomHttp;
}(http_1.Http));
exports.CustomHttp = CustomHttp;
//# sourceMappingURL=custom-http.service.js.map