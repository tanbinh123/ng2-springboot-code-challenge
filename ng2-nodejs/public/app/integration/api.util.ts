import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

export class ApiUtil {
    public handleError(error: Response | any) {
        console.error(error);
        return Observable.throw(error);
    }

    public getRequestOptions() {
        let headers = new Headers({'Content-Type': 'application/json'});
        headers.append('Access-Control-Allow-Origin', '*');
        return new RequestOptions({headers: headers});
    }
}
