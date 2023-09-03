import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class RouterService {

  constructor(private router: Router) { }

  routeToUserRegistration() {
    this.router.navigate(['register-user']);
  }

  routeToManager() {
    this.router.navigate(['register-manager']);
  }
  routeToHome(){
    this.router.navigate(['home']);
  }
  routeToLogin(){
    this.router.navigate(['login']);
  }
  routeToRegister(){
    this.router.navigate(['register']);
  }
  routeToLoginManager(){
    this.router.navigate(['login-manager']);
  }
 
  routeToUserHome(){
    this.router.navigate(['user-home']);
  }
  routeToManagerHome(){
    this.router.navigate(['manager-home']);
  }
  routeToEdit(){
    this.router.navigate(['user-edit']);
  }
  routeToEditLoc(){
    this.router.navigate(['edit-location']);
  }
  routeToAddLoc(){
    this.router.navigate(['add-location']);
  }
  routeToUserProfile(){
    this.router.navigate(['user-profile']);
  }
  routeToLocation(id:number){
    this.router.navigate(['location',id]);
  }
  routeToBooking(id:number|undefined){
    this.router.navigate([id,'ticket-booking']);
  }
  routeToContact(){
    this.router.navigate(['contact']);
  }
}