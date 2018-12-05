import {ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MatDialog, MatDialogRef} from '@angular/material';
import {AlertDialogComponent} from './alert-dialog/alert-dialog.component';

@Component({
    selector: 'app-admin-ticket',
    templateUrl: './admin-ticket.component.html',
    styleUrls: ['./admin-ticket.component.scss']
})
export class AdminTicketComponent implements OnInit {
    @ViewChild('fileName') fileName: ElementRef;
    alertDialogRef: MatDialogRef<AlertDialogComponent>;
    form: FormGroup;

    constructor(private cd: ChangeDetectorRef, private router: Router, private dialog: MatDialog) {
        this.form = new FormGroup({
            title: new FormControl(null, [Validators.required,
                Validators.maxLength(50)]),
            description: new FormControl(null, [Validators.required,
                Validators.maxLength(255)]),
            file: new FormControl(null)
        });
    }

    ngOnInit() {
    }

    onFileChange(event) {
        const reader = new FileReader();

        if (event.target.files && event.target.files.length) {
            const [file] = event.target.files;
            reader.readAsDataURL(file);

            if (file.size > 5242880) {
                reader.abort();
                this.fileName.nativeElement.innerHTML = 'Max file size is 5Mb.';
            }

            if (reader.readyState === 1) {
                this.fileName.nativeElement.innerHTML = 'Loading...';
            }

            reader.onload = () => {
                this.form.patchValue({
                    file: reader.result
                });

                this.fileName.nativeElement.innerHTML = file.name;

                this.cd.markForCheck();
            };
        }
    }

    onSubmit() {
        console.log(this.form.value);
        this.openDialog();
    }

    openDialog() {
        this.alertDialogRef = this.dialog.open(AlertDialogComponent, {});
        this.alertDialogRef.afterClosed().subscribe(() => {
            this.router.navigateByUrl('/');
        });
    }

}
