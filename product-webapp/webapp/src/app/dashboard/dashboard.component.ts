import { Component } from '@angular/core';
import { RouterService } from 'app/router.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private rs : RouterService){}
  ngOnInit(): void {
    this.isManager = localStorage.getItem('isManager') ? true : false;
    this.isUser = localStorage.getItem('isUser') ? true : false;
    this.isLoggedIn = localStorage.getItem('token') ? true : false;
    
  }
  isManager : boolean = localStorage.getItem('isManager') ? true : false;
  isUser : boolean = localStorage.getItem('isUser') ? true : false;
  isLoggedIn : boolean = localStorage.getItem('isLoggedIn') ? true : false;
  clickLogin(){
    
    this.rs.routeToLogin();
  }
  clickContact(){
    this.rs.routeToContact();
  }
  clickProfile(){
    this.rs.routeToUserProfile();
  }
  clickLogout(){
    localStorage.clear();
    this.isLoggedIn = false;
    this.rs.routeToHome();
    location.reload();
  }


}
