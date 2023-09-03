import { Component, OnInit } from '@angular/core';
import { RouterService } from 'app/router.service';
import { SharedDataService } from 'app/services/shared-data.service';
import { UserService } from 'app/services/user.service';
import Swal from 'sweetalert2';

export class user {
  username: string = '';
  fullname: string = '';
  email: string = '';
  password: string = '';
  contact: number | null = null;
  age: number | null = null;
  gender: ("male" | "female" | "other" | string) = '';
  pfp: any;
  walletBalance: number = 0.0;
  userType: string = "USER";

}

export class email {

  recipient: string = '';
  msgbody: string =  '';
  subject: string = '';

}



@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {
  constructor(private rs: RouterService, private userService: UserService, private sharedData: SharedDataService) { }

  hide = true;
  inputField: string = '';
  currUser: user = new user;
  email: email = new email;
  url: string | any = "../../assets/pfp.png";

  ngOnInit(): void {
    console.log(this.currUser);
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
        this.currUser.pfp = event.target.files[0];
        // console.log("profile pic -" ,this.currUser.pfp)
        ;
      })
    }
  }


  onSubmit() {
    console.log(this.currUser);

    if (!this.currUser.email || !this.currUser.age || !this.currUser.contact || !this.currUser.gender
      || !this.currUser.password || !this.currUser.fullname || !this.currUser.username)
      Swal.fire({
        title: 'Error!',
        text: 'All Fields are necessary',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
    else if (this.currUser.password.length < 8)
      Swal.fire({
        title: 'Error!',
        text: 'Password should be of minimum 6 Length',
        icon: 'error',
        confirmButtonText: 'Okay',
      });

    else if (this.currUser.age < 18)
      Swal.fire({
        title: 'Error!',
        text: 'Age should be greatr than 18',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
    else if (this.currUser.contact.toString().length !== 10)
      Swal.fire({
        title: 'Error!',
        text: 'Enter a Valid 10 Digit Contact number',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
    else {
      const formData = new FormData();
      formData.append('profilePicture', this.currUser.pfp);
      formData.append('username', this.currUser.username);
      formData.append('name', this.currUser.fullname);
      formData.append('email', this.currUser.email);
      formData.append('password', this.currUser.password);
      formData.append('contact', this.currUser.contact!.toString());
      formData.append('age', this.currUser.age!.toString());
      formData.append('gender', this.currUser.gender);
      formData.append('walletBalance', this.currUser.walletBalance.toString());
      formData.append('userType', this.currUser.userType);
      console.log(formData);

      this.userService.registerUser(formData).subscribe((data) => {
        console.log(data);
        this.email.recipient = this.currUser.email;
        this.email.subject = "Welcome to ExploreEase!!!";
        this.email.msgbody = "Hello, " + this.currUser.fullname + "We welcome you to our website and we hope we can serve all your ticket booking needs. We hope you have a great time using our website. Thank you for choosing us. Regards, ExploreEase Team";
        this.sharedData.sendEmail(this.email).subscribe((data) => {});
        Swal.fire({
          title: 'Success!',
          text: 'Registration Successful,Redirecting to Login.',
          icon: 'success',
          confirmButtonText: 'Okay',
        });
        
        this.rs.routeToLogin();
      }, (err) => {
        console.log(err);
        Swal.fire({
          title: 'Error!',
          text: 'Registration Failed, Try Again Later',
          icon: 'error',
          confirmButtonText: 'Okay',
        });
      });

    }
  }
}
