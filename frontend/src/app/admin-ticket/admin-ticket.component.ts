import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-admin-ticket',
  templateUrl: './admin-ticket.component.html',
  styleUrls: ['./admin-ticket.component.scss']
})
export class AdminTicketComponent implements OnInit {
  form: FormGroup;

  constructor(private cd: ChangeDetectorRef) {
    this.form = new FormGroup({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      file: new FormControl(null, Validators.required)
    });
  }

  ngOnInit() {
  }

  onFileChange(event) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {
        this.form.patchValue({
          file: reader.result
        });

        this.cd.markForCheck();
      };
    }
  }

  onSubmit() {
    console.log(this.form.value);
    this.form.reset();
  }

}
