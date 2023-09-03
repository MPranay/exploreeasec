import { Component, OnInit } from '@angular/core';

import { RouterService } from 'app/router.service';
import { LocationService } from 'app/services/location.service';
import { SharedDataService } from 'app/services/shared-data.service';
import Swal from 'sweetalert2';

export class manager {
managername  = '';
fullname  = '';
email  = '';
password  = '';
contact : number | null = null;
age : number | null = null;
gender : ("male" | "female" | "other" | string) = '';
profilePicture : any ;
locationId  =0;
userType  = "MANAGER";
location  = '';


}

export class email {

  recipient: string = '';
  msgbody: string =  '';
  subject: string = '';

}


@Component({
  selector: 'app-register-manager',
  templateUrl: './register-manager.component.html',
  styleUrls: ['./register-manager.component.css']
})
export class RegisterManagerComponent implements OnInit {
  constructor(private rs : RouterService, private locationService:LocationService, private sharedData: SharedDataService){
    
  }
  selectedFile: File | null = null;
  hide = true;
  inputField  = '';
  currManager : manager = new manager;
  email: email = new email;
  url : string | any = "../../assets/pfp.png";
  
  
  ngOnInit(): void {
    console.log(this.currManager);
    
  }

  imageUpload(event: any) {
    console.log(event);
    if (event.target.files[0]) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = ((e): any => {
        this.url = e.target?.result;
        //var bstring : any = e.target?.result;
        //console.log(bstring);
        this.selectedFile = event.target.files[0];
        // console.log("profile pic -" ,this.currUser.pfp)
        ;
      })
    }
  }


  onSubmit(){
    if(!this.currManager.email || !this.currManager.age || !this.currManager.contact || !this.currManager.gender
      || !this.currManager.password || !this.currManager.fullname || !this.currManager.managername)
      Swal.fire({
        title: 'Error!',
        text: 'All Fields are necessary',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
      else if(this.currManager.password.length < 6)
      Swal.fire({
        title: 'Error!',
        text: 'Password should be of minimum 6 Length',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
  
    else if(this.currManager.age < 18) 
    Swal.fire({
      title: 'Error!',
      text: 'Age should be greater than 18',
      icon: 'error',
      confirmButtonText: 'Okay',
    });
    else if(this.currManager.contact.toString().length !== 10) 
    Swal.fire({
      title: 'Error!',
      text: 'Enter a Valid 10 Digit Contact number',
      icon: 'error',
      confirmButtonText: 'Okay',
    });

    else if (this.selectedFile){
      console.log(this.currManager);
      const formData = new FormData;
      formData.append('username', this.currManager.managername);
      formData.append('name', this.currManager.fullname);
      formData.append('email', this.currManager.email);
      formData.append('password', this.currManager.password);
      formData.append('contact', this.currManager.contact.toString());
      formData.append('age', this.currManager.age.toString());
      formData.append('gender', this.currManager.gender);
      formData.append('profilePicture', this.selectedFile);
      formData.append('userType', this.currManager.userType.toString());
      formData.append('location', this.currManager.location);
      formData.append('locationId', this.currManager.locationId.toString());
      console.log(formData);
      this.locationService.addManager(formData).subscribe((data: any) => {
        this.email.recipient = this.currManager.email;
        this.email.subject = "Welcome to ExploreEase!!!";
        this.email.msgbody = "Hello, " + this.currManager.fullname + "We welcome you to our website and we hope together we can serve all our customers. Regards, ExploreEase Team";
        this.sharedData.sendEmail(this.email).subscribe((data) => {});
        Swal.fire({
          title: 'Success!',
          text: 'Registration Successful,Redirecting to Login',
          icon: 'success',
          confirmButtonText: 'Okay',
        });
        this.rs.routeToLoginManager();
      },(err)=>{
        Swal.fire({
          title: 'Error!',
          text: 'Registration Failed, Try Again',
          icon: 'error',
          confirmButtonText: 'Okay',
        });
      });
      
      

      
    }
  }
  
}
