import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styles: []
})
export class IndexComponent implements OnInit {

    private contents: string;

  constructor() { }

  ngOnInit() {
      this.contents = 'index works!';
  }

}
