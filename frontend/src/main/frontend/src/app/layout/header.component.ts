import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: []
})
export class HeaderComponent implements OnInit {

    private title: string;

    constructor() {
        this.title = 'Angular Board';
    }

    ngOnInit() {
    }

}
