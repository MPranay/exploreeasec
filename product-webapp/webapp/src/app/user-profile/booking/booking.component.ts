import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/services/user.service';
import { BookingDetailsService } from 'app/services/booking-details.service';

import { SharedDataService } from 'app/services/shared-data.service';
import { RouterService } from 'app/router.service';


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit{

  constructor(private UserService:UserService, private bookingservice:BookingDetailsService, private sharedData: SharedDataService, private routerService: RouterService){};

  bookingData: any;
  userId: number = 0;
  ngOnInit(): void {
    this.userId = this.sharedData.getUserID();
    this.getBookingDetails();
  }


  getBookingDetails() {
    this.bookingservice.getBookingsByUserId(this.userId).subscribe(
      (response) => {
        if (response) {
          this.bookingData = response;
          console.log(this.bookingData);
        }
        else console.log("No Response from the server");
      }



    );
  }
  
  giveReview(locationId: number){
    this.sharedData.setSelectedIndex();
    this.routerService.routeToLocation(locationId);
  }

}
