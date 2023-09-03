import { Component } from '@angular/core';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  email : string = '';
  notify(){
    if(this.email !== '')
    Swal.fire({
      title: 'Success!',
      text: 'You will recieve latest Updates on your Email',
      icon: 'success',
      confirmButtonText: 'Okay',
    });
    else
    Swal.fire({
      title:'Error',
      text:'Please provide an Email',
      icon: 'error',
      timer:2000})
  }
  
}
