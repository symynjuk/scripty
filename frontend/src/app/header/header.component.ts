import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  //LOGO = require("../images/icon.png"); // doesn't show //image without 'require'
  @Output() iframeTrigger: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  showIframe(){
    this.iframeTrigger.emit(true);// true is just to send data to app-root. Then to app-main to change state trigger 
  }

}
