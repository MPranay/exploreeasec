import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';



@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent {
  

  reviewForm: FormGroup;
  ratings: number[] = [1, 2, 3, 4, 5];

  constructor(
    
    
    public dialogRef: MatDialogRef<ReviewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.reviewForm = new FormGroup({
      name: new FormControl(data.name || '', [Validators.required]),
      contactEmail: new FormControl(data.contactEmail || '', [Validators.required, Validators.email]),
      rating: new FormControl(data.rating || 0, [Validators.required]),
      reviewBody: new FormControl(data.reviewBody || '', [Validators.required])
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    
    if (this.reviewForm.valid) {
      
      this.dialogRef.close(this.reviewForm.value);
    }
  }

}
