import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RouterService } from 'app/router.service';
import { BookingDetailsService } from 'app/services/booking-details.service';
import { SharedDataService } from 'app/services/shared-data.service';

import Swal from 'sweetalert2';

declare var Razorpay: any;
export class TicketCount {
  adult: number = 0;
  children: number = 0;
}

export class Ticket {
  locationId: number = 0;
  tripdate: Date = new Date('');
  timeslot: string = '';
  name: string = '';
  email: string = '';
  phone: string = '';
  adult: number = 0;
  children: number = 0;
  userId: number = 0;
  locationName: string = '';
}

export class email {

  recipient: string = '';
  msgbody: string =  '';
  subject: string = '';

}

@Component({
  selector: 'app-ticket-booking',
  templateUrl: './ticket-booking.component.html',
  styleUrls: ['./ticket-booking.component.css']
})
export class TicketBookingComponent implements OnInit{
  locationName: string = '';

  locationId: number = 0;
  userId: number = 0;
  constructor(private bookingDetails: BookingDetailsService, private sharedData: SharedDataService, private router: RouterService, private activatedRoute: ActivatedRoute) { }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.locationId = +params['id'];
      console.log(this.locationId);
    });
    this.locationName = this.sharedData.getTemporaryLocationName();
    
    this.userId = this.sharedData.getUserID();
    if(this.locationName === ''){
      this.router.routeToLocation(this.locationId);
      ;
    }
    
  }
  
  
  Razorpay: any;
  paymentSucess: boolean = false;
  crrbooking: Ticket = new Ticket();
  tc: TicketCount = new TicketCount();
  email: email = new email;

  

  incrementAdults() {
    this.crrbooking.adult++;
  }

  decrementAdults() {
    if (this.crrbooking.adult > 1) {
      this.crrbooking.adult--;
    }
  }

  incrementChildren() {
    this.crrbooking.children++;
  }

  decrementChildren() {
    if (this.crrbooking.children > 0) {
      this.crrbooking.children--;
    }
  }

  addCustomer() {
    this.tc.adult = this.crrbooking.adult;
    this.tc.children = this.crrbooking.children;
  }

  removeCustomer() {
    this.tc.adult = this.tc.children = 0;
  }
  calculateTotalFare() {
    const adultPrice = this.tc.adult * 100* this.sharedData.getTicketPrice();
    const childrenPrice = this.tc.children * 50* this.sharedData.getTicketPrice();
    return adultPrice + childrenPrice;
  }
  
  isTripDateValid(tripDate: Date): boolean {
    const currentDate = new Date();
    console.log(currentDate);
    console.log(tripDate)
    Date.parse(tripDate.toDateString().replace(/-/g, " "));
    return true;
  }
  
  payNow() {
    if (!this.crrbooking.tripdate || !this.crrbooking.timeslot || !this.crrbooking.name ||
      !this.crrbooking.email || !this.crrbooking.phone) {
    Swal.fire('Error', 'Please fill in all required fields.', 'error');
    return;
  }

  
    this.addCustomer();
    const totalFare = this.calculateTotalFare();
    console.log(totalFare);
    const RazerpayOptions:any = {
      description: 'Exploreease',
      currency: 'INR',
      amount: totalFare,
      name: 'Exploreease',
      key: 'rzp_test_hUQYNFULhkHrR2',
      image: '../../assets/Logo-1.png',
      
      prefill: {
        name: 'Adesh' ,
        email: 'adesh.senapati10@gmail.com',
        phone: '9438251641'
      },
      theme: {
        color: '#6466e3' 
      },
      modal: {
        ondismiss:  () => {
          console.log('dismissed')
          
          Swal.fire('Error', 'Payment failed. Please try again.', 'error');
        }
      }
    }
    RazerpayOptions.handler = ((response: any) => {
      console.log(response);
      console.log(response.razorpay_payment_id);
      this.paymentSucess = true;
      this.crrbooking.userId = this.userId;
      this.crrbooking.locationId = this.locationId;
      this.crrbooking.locationName = this.locationName;
      Swal.fire({
        title: 'Success!',
        text: 'Trip Booked Succesfully , Redirecting to Explore Page',
        icon: 'success',
        confirmButtonText: 'Okay',
      });
      setTimeout(()=>{
        this.router.routeToUserHome();
      },2000);
      this.bookingDetails.addBooking(this.crrbooking).subscribe((data: any) => {
        const paymentData = {
          
          paymentId: response.razorpay_payment_id,
          userId: this.userId,
          locationId: this.locationId,
          totalFees: totalFare
          
          
        }
        this.email.recipient = this.crrbooking.email;
        this.email.subject = "Booking Done!!";
        this.email.msgbody = "Hello, " + this.crrbooking.name + "Booking done. We hope you have a great time . Thank you for choosing us. Regards, ExploreEase Team";
        this.sharedData.sendEmail(this.email).subscribe((data) => {});
        this.bookingDetails.addPayment(paymentData).subscribe((data: any) => {
          Swal.fire('Success', 'Payment Successful', 'success');
          this.sharedData.sendEmail(this.email).subscribe((data) => {});
          this.sharedData.setTemporaryLocationName('');
        });
      });
    });
    
    
    var rzs = new Razorpay(RazerpayOptions);
    rzs.open();
    rzs.on('payment.failed', function (response:any){
      console.log(response.error.code);
      console.log(response.error.description);
      console.log(response.error.source);
      console.log(response.error.step);
      console.log(response.error.reason);
      console.log(response.error.metadata.order_id);
      console.log(response.error.metadata.payment_id);
      
    })
    
  
  }
  
  

  

}


