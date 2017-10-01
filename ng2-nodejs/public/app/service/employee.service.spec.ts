import { inject, TestBed } from '@angular/core/testing';
import {
    BaseRequestOptions, Headers, Http, RequestMethod, RequestOptions, Response, ResponseOptions
} from '@angular/http';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { EmployeeService } from './employee.service';
import { ApiUtil } from '../integration/api.util';
import URL_CONFIG from '../integration/url.config';

class MockApiUtil extends ApiUtil {
}

describe('EmployeeService', () => {
    let mockApiUtil: ApiUtil;
    beforeEach(() => {
        mockApiUtil = new MockApiUtil();
        TestBed.configureTestingModule({
            providers: [
                BaseRequestOptions,
                MockBackend,
                {
                    provide: Http, useFactory: (mockBackend: MockBackend, defaultOptions: BaseRequestOptions) => {
                        return new Http(mockBackend, defaultOptions);
                    },
                    deps: [MockBackend, BaseRequestOptions]
                },
                {provide: ApiUtil, useValue: mockApiUtil},
                EmployeeService
            ]
        });
    });

    describe('getEmployeeTree()', () => {
        let headers: Headers = new Headers({'Content-Type': 'application/json'});
        let requestOptions: RequestOptions = new RequestOptions({headers: headers});

        beforeEach(() => {
            spyOn(mockApiUtil, 'getRequestOptions')
                .and.returnValue(requestOptions);
        });

        afterEach(() => {

        });

        function Setup(mockBackend: MockBackend, requestMethod: RequestMethod) {
            mockBackend.connections.subscribe((mockConnection: MockConnection) => {
                expect(mockConnection.request.method).toBe(requestMethod);
                expect(mockConnection.request.url).toBe(URL_CONFIG.EMPLOYEE_TREE_URL);
                expect(mockConnection.request.headers).toEqual(headers);
            });
        }

        it('get employee tree with no backend connection issues',
            inject([MockBackend, EmployeeService], (mockBackend: MockBackend,
                                                       employeeService: EmployeeService) => {
                // Arrange
                Setup(mockBackend, RequestMethod.Get);

                // Act
                employeeService.getEmployeeTree().subscribe(response => {
                    // Assert
                    expect(mockApiUtil.getRequestOptions).toHaveBeenCalled();
                });
            }));

        it('get employee tree with connection issues',
            inject([MockBackend, EmployeeService], (mockBackend: MockBackend,
                                                    employeeService: EmployeeService) => {
                // Arrange
                Setup(mockBackend, RequestMethod.Get);

                // Act
                employeeService.getEmployeeTree().subscribe(response => {
                    // Assert
                    expect(mockApiUtil.getRequestOptions).toHaveBeenCalled();
                });
            }));
    });

});
