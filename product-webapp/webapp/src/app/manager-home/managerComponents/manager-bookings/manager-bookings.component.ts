import { Component } from '@angular/core';
import { booking } from 'app/booking';
import { BookingDetailsService } from 'app/services/booking-details.service';
import { SharedDataService } from 'app/services/shared-data.service';

export class bookingsv {
    bookingId : number | undefined;
      userId : number | undefined;
      locationId : number | undefined;
      locationName ="";
      adult : number | undefined;
      children : number | undefined;
      email = "";
      name  = "";
      phone : number | undefined;
      timeslot : any | undefined;
      tripdate : any | undefined;

}

@Component({
  selector: 'app-manager-bookings',
  templateUrl: './manager-bookings.component.html',
  styleUrls: ['./manager-bookings.component.css']
})
export class ManagerBookingsComponent {
  locationId=this.share.getLocationID();
  Bookings : bookingsv[] = [];
  constructor(private bookingservice:BookingDetailsService,private share:SharedDataService){
    bookingservice.getBookingByLocation(this.locationId).subscribe((data:bookingsv[])=>{
      this.Bookings=data;
      
    })
  }
  
  // bookings: any[] = [{ id: 1, name: 'Booking 1', date: '2023-08-16' },
  // { id: 2, name: 'Booking 2', date: '2023-08-17' },
  // { id: 3, name: 'Booking 3', date: '2023-08-18' },
  // { id: 3, name: 'Booking 3', date: '2023-08-18' },
  // { id: 3, name: 'Booking 3', date: '2023-08-18' },
  // { id: 3, name: 'Booking 3', date: '2023-08-18' },
  // { id: 3, name: 'Booking 3', date: '2023-08-18' },
  // { id: 3, name: 'Booking 3', date: '2023-08-18' },];
}
