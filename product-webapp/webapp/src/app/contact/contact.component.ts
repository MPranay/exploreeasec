import { Component } from '@angular/core';
import { SharedDataService } from 'app/services/shared-data.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  constructor(private sharedData: SharedDataService){}

  email: string = '';
  name: string = '';
  contactNo: number | null = null;
  issue: string = '';
  
  status: string = 'active';
  onSubmit(){
    console.log(this.email);
    
    const data = {
      userID: this.sharedData.getUserID(),
      userEmail: this.email,
      name: this.name,
      contact: this.contactNo,
      issue: this.issue,
      status: this.status
    }
    console.log(data);
    this.sharedData.sendSupport(data).subscribe((data) => {
      Swal.fire({
        title: 'Success',
        text: 'Your issue has been submitted',
        icon: 'success',
        confirmButtonText: 'Ok'
      });
      this.email = '';
      this.contactNo=null;
      this.name='';
      this.issue='';
    });
  }
}
