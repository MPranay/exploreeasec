import { Component } from '@angular/core';
import { SharedDataService } from 'app/services/shared-data.service';
import { UserService } from 'app/services/user.service';
import { user } from '../user';
import Swal from 'sweetalert2';
import { RouterService } from 'app/router.service';

export class userAuth {
  email: string = '';
  password: string = '';
  userType: string = "USER"
}



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private sharedService: SharedDataService, private userService: UserService, private router: RouterService) { }
  user: userAuth = new userAuth();
  userData: any;
  token: any;
  forgotPassword(){
    Swal.fire({
      title: 'Success!',
      text: 'An Password Reset Link has been sent to your Email',
      icon: 'success',
      confirmButtonText: 'Okay',
    });
  }
  onSubmit() {
    console.log(this.user);
    this.sharedService.authenticateUser(this.user).subscribe((data) => {
      console.log(data);
      this.token = data;
      this.userService.getUser(this.user.email).subscribe((data) => {
        this.userData = data;
        this.sharedService.setUserID(this.userData.userId);
        this.sharedService.setToken(this.token.token);
        localStorage.setItem('isUser','true');
        localStorage.setItem('isLoggedIn','true');
        Swal.fire({
          title: 'Success!',
          text: 'Login Successful',
          icon: 'success',
          confirmButtonText: 'Okay',
        });
        this.router.routeToUserHome();
      });
    },(err)=>{
      Swal.fire({
        title: 'Error!',
        text: 'Invalid Credentials',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
    });
  }

}
