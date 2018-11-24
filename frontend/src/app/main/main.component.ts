import { Component, ViewChild, ElementRef, Input } from '@angular/core';

import { fromEvent } from 'rxjs'
import { debounceTime } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent {

someCode = "var a = 10;"
highlightedHtml = ""
highlightedCss = ""
highlightedJs = ""

  @Input() parentData

  iframe = ""

  @ViewChild('htmlPanel') htmlPanel: ElementRef;
  @ViewChild('iframePanel') iframePanel: ElementRef;
  @ViewChild('cssPanel') cssPanel: ElementRef;
  @ViewChild('jsPanel') jsPanel: ElementRef;

  htmlPanelValue = ""; 
  cssPanelValue = ""; 
  jsPanelValue = ""

  getValueFromArea(panel, where) {
    fromEvent(panel.nativeElement, "keyup")
      .pipe(
        map((event: any) => { return event.target.textContent; })
      ).pipe(
      debounceTime(500)
      )
      .subscribe(ch => {
        if (where === "html") {
          this.htmlPanelValue = "<html>" + ch + "</html>"
          this.highlightedHtml = ch
        } else if (where === "style") {
          this.cssPanelValue = "<style>" + ch + "</style>"
          this.highlightedCss = ch
        } else if (where === "script") {
          this.jsPanelValue = "<script>" + ch + "</script>"
          this.highlightedJs = ch
        }

        let doc = this.iframePanel.nativeElement.contentDocument

        doc.open();
        doc.write(this.htmlPanelValue);
        doc.write(this.cssPanelValue);
        doc.write(this.jsPanelValue);
        doc.close();
      })
  }

  ngOnChanges() {
    this.getValueFromArea(this.htmlPanel, "html")
    this.getValueFromArea(this.cssPanel, "style")
    this.getValueFromArea(this.jsPanel, "script")

    if (this.parentData === true) {
      this.iframe = "iframe-show"
    }
  }
}
