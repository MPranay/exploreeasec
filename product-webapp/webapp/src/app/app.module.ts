import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatDatepickerModule } from '@angular/material/datepicker';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponent } from './register/register.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { RegisterManagerComponent } from './register-manager/register-manager.component';
import { RouterService } from './router.service';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatExpansionModule} from '@angular/material/expansion';
import { FormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { LoginManagerComponent } from './login-manager/login-manager.component' ;
import { ReviewComponent } from './review/review.component';
import {MatTabsModule} from '@angular/material/tabs';
import { MatDialogModule } from '@angular/material/dialog';
import {MatTooltipModule} from '@angular/material/tooltip';
import { LocationComponent } from './location/location.component';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './homepage/homepage.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { MatTableModule } from '@angular/material/table';
import { TicketBookingComponent } from './ticket-booking/ticket-booking.component';
import { MatNativeDateModule } from '@angular/material/core';
import { TimepickerModule } from 'ngx-bootstrap/timepicker';
import { AddLocationComponent } from './add-location/add-location.component';
import {MatGridListModule } from '@angular/material/grid-list';
import { UserEditComponent } from './user-profile/useredit/useredit.component';
import { FooterComponent } from './footer/footer.component';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { UserHomeComponent } from './user-home/user-home.component';
import { EditLocationComponent } from './edit-location/edit-location.component';
import { ViewBookingComponent } from './view-booking/view-booking.component';
import { ManagerHomeComponent } from './manager-home/manager-home.component';
import { ManagerProfileComponent } from './manager-home/managerComponents/manager-profile/manager-profile.component';
import { ManagerLocationsComponent } from './manager-home/managerComponents/manager-locations/manager-locations.component';
import { ManagerBookingsComponent } from './manager-home/managerComponents/manager-bookings/manager-bookings.component';
import { ManagerReviewsComponent } from './manager-home/managerComponents/manager-reviews/manager-reviews.component';
import { ContactComponent } from './contact/contact.component';
import { ErrorComponent } from './error/error.component';
import { ProfileComponent } from './user-profile/profile/profile.component';
import { BookingComponent } from './user-profile/booking/booking.component';






@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    DashboardComponent,
    RegisterUserComponent,
    RegisterManagerComponent,
    LoginComponent,
    LoginManagerComponent,
    ReviewComponent,
    LocationComponent,
    HomepageComponent,
    UserProfileComponent,
    TicketBookingComponent,

    AddLocationComponent,
    UserEditComponent,
    FooterComponent,
    UserHomeComponent,
    EditLocationComponent,
    ViewBookingComponent,
    ManagerHomeComponent,
    ManagerProfileComponent,
    ManagerLocationsComponent,
    ManagerBookingsComponent,
    ManagerReviewsComponent,
    ContactComponent,
    ErrorComponent,
    ProfileComponent,
    BookingComponent
    
    
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatExpansionModule,
    FormsModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatDialogModule,
    MatTooltipModule,
    HttpClientModule,
    MatTableModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
    TimepickerModule.forRoot(),
    MatGridListModule,
    SweetAlert2Module
    
  ],
  providers: [RouterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
