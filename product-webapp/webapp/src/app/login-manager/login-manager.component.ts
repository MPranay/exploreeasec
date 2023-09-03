import { Component } from '@angular/core';
import { RouterService } from 'app/router.service';
import { LocationService } from 'app/services/location.service';
import { SharedDataService } from 'app/services/shared-data.service';
import Swal from 'sweetalert2';

export class managerAuth {
  email : string = '';
  password : string = '';
  userType : string = "MANAGER";
}

@Component({
  selector: 'app-login-manager',
  templateUrl: './login-manager.component.html',
  styleUrls: ['./login-manager.component.css']
})
export class LoginManagerComponent {
  constructor(private sharedService: SharedDataService, private locationService: LocationService, private router: RouterService) { }

  manager : managerAuth = new managerAuth();
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
    console.log(this.manager);
    this.sharedService.authenticateUser(this.manager).subscribe((data) => {
      console.log(data);
      this.token = data;
      this.locationService.getManager(this.manager.email).subscribe((data) => {
        this.userData = data;
        this.sharedService.setUserID(this.userData.managerId);
        this.sharedService.setLocationID(this.userData.locationId);
        this.sharedService.setToken(this.token.token);
        Swal.fire({
          title: 'Success!',
          text: 'Logged In Successfully',
          icon: 'success',
          confirmButtonText: 'Okay',
        });
        this.router.routeToManagerHome();
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
