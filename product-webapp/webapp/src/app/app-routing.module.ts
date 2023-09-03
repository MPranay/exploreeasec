import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { RegisterManagerComponent } from './register-manager/register-manager.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { LoginComponent } from './login/login.component';
import { LoginManagerComponent } from './login-manager/login-manager.component';
import { LocationComponent } from './location/location.component';
import { ReviewComponent } from './review/review.component';
import { HomepageComponent } from './homepage/homepage.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { TicketBookingComponent } from './ticket-booking/ticket-booking.component';
import { AddLocationComponent } from './add-location/add-location.component';
import { UserEditComponent } from './user-profile/useredit/useredit.component';
import { EditLocationComponent } from './edit-location/edit-location.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { ViewBookingComponent } from './view-booking/view-booking.component';

import { ManagerHomeComponent } from './manager-home/manager-home.component';
import { ContactComponent } from './contact/contact.component';
import { ErrorComponent } from './error/error.component';
import { BookingComponent } from './user-profile/booking/booking.component';
import { canActivateRouteGuard } from './can-activate-route.guard';

const routes: Routes = [
  {
  path:'',
  redirectTo:'home',
  pathMatch:"full",
  },
  {
    path : "error",
    component : ErrorComponent
  },
  {
    path : "home",
    component : HomepageComponent
    
  },
  {
  path:"register",
  component:RegisterComponent
  },
  {
    path:"register-user", 
    component:RegisterUserComponent
  },
  {
    path:"register-manager", 
    component:RegisterManagerComponent
  },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"add-location",
    component:AddLocationComponent,
    canActivate: [canActivateRouteGuard]
  },
  {
    path:"login-manager",
    component:LoginManagerComponent
  },
  {
    path:"edit-location",
    component:EditLocationComponent,
    canActivate: [canActivateRouteGuard]
  },
  { 
    path: 'location/:id',
    component: LocationComponent
  },
  { 
    path: 'review', component: ReviewComponent
  },
  {
    path:'user-profile',component:UserProfileComponent,canActivate: [canActivateRouteGuard]
  },
  {
    path : ":id/ticket-booking",
    component : TicketBookingComponent,
    canActivate: [canActivateRouteGuard]
  },
  {
    path: 'user-edit', 
    component: UserEditComponent,
    canActivate: [canActivateRouteGuard]
  },
  {
    path : 'user-home',
    component: UserHomeComponent
  },
  {
    path : 'manager-view-bookings',
    component: ViewBookingComponent,
    canActivate: [canActivateRouteGuard]
  },{
    path : 'manager-home',
    component : ManagerHomeComponent,
    canActivate: [canActivateRouteGuard]
  },
  { 
    path: 'contact', 
    component: ContactComponent 
  },
  {
    path: '**',
    redirectTo: 'error'
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
 