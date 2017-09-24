import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../service/employee.service';
import { Employee } from '../model/employee.model';

@Component({
    moduleId: module.id,
    selector: 'ch-home',
    styleUrls: ['home.styles.css'],
    templateUrl: 'home.template.html'
})

export class HomeComponent implements OnInit {

    private employeeTree: Employee[];

    constructor(private employeeService: EmployeeService) {
    }

    public ngOnInit() {
        this.employeeService.getEmployeeTree()
            .subscribe((data: Employee[]) => {
                this.employeeTree = data;
            }, error => {
                console.log(error);
            });
    }
}
