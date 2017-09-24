import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { ApiUtil } from '../integration/api.util';
import URL_CONFIG from '../integration/url.config';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class EmployeeService {

    constructor(private http: Http, private apiUtil: ApiUtil) {
    }

    public getEmployeeTree(): Observable<any> {
        return this.http.get(URL_CONFIG.EMPLOYEE_TREE_URL, this.apiUtil.getRequestOptions())
            .map(response => response.json())
            .catch(this.apiUtil.handleError);
    }
}