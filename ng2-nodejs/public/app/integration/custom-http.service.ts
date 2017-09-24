import { Injectable } from '@angular/core';
import { Http, Request, RequestOptionsArgs, Response, RequestOptions, ConnectionBackend, Headers } from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class CustomHttp extends Http {
    constructor(
        private backend: ConnectionBackend,
        private defaultOptions: RequestOptions) {
        super(backend, defaultOptions);
    }

    public request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
        return this.httpInterceptTrigger(() => super.request(url, this.getRequestOptionArgs(options)));
    }

    public get(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.httpInterceptTrigger(() => super.get(url, this.getRequestOptionArgs(options)));
    }

    public post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.httpInterceptTrigger(() => super.post(url, body, this.getRequestOptionArgs(options)));
    }

    public put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.httpInterceptTrigger(() => super.put(url, body, this.getRequestOptionArgs(options)));
    }

    public delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.httpInterceptTrigger(() => super.delete(url, this.getRequestOptionArgs(options)));
    }

    private getRequestOptionArgs(options?: RequestOptionsArgs): RequestOptionsArgs {
        if (!options) {
            options = new RequestOptions();
        }

        if (!options.headers) {
            options.headers = new Headers();
        }

        let contentTypes: string[] = options.headers.getAll('Content-Type');
        if (!contentTypes || !contentTypes.find((contentType) => contentType.toLowerCase().includes('application/json'))) {
            options.headers.append('Content-Type', 'application/json');
        }

        let allowCors: string[] = options.headers.getAll('Access-Control-Allow-Origin');
        if (!allowCors) {
            options.headers.append('Access-Control-Allow-Origin', '*');
        }

        return options;
    }

    private handleError(error: Response | any) {
        // TODO add custom error handling here
        return Observable.throw(error || 'Server error');
    }

    /**
     * This method intercepts all http calls and handles error logging
     * @param httpCall
     * @returns {Observable<R>}
     */
    private httpIntercept(httpCall: () => Observable<Response>): Observable<Response> {
        return httpCall().catch( err => this.handleError(err));
    }

    private httpInterceptTrigger(httpCall: () => Observable<Response>) {
        this.startHttpRequest();
        return this.httpIntercept(httpCall).finally(() => this.endHttpRequest());
    }

    private startHttpRequest() {
        // aop point before http call
    }

    private endHttpRequest() {
        // aop point after http call
    }
}
