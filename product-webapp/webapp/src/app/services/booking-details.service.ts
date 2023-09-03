import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable,OnInit } from '@angular/core';
import { booking } from 'app/booking';
import { Observable, map } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class BookingDetailsService  implements OnInit{

  private url:string='';
  constructor(private httpClient :HttpClient) {
    this.url = 'https://exploreease.stackroute.io/bookings/getAll'
}
ngOnInit(): void {
}

getBooking(): Observable<any> {
   
  return this.httpClient.get<Array<any>>(this.url);
}

addBooking(booking:any): Observable<any> {
return this.httpClient.post ("https://exploreease.stackroute.io/api/v5/bookings/add",booking);

}

getBookingsByUserId(userId:number) : Observable<any> {
  return this.httpClient.get("https://exploreease.stackroute.io/api/v5/bookings/getByUserId/"+userId);
}

getBookingByLocation(locationId:number) : Observable<any>{
  return this.httpClient.get("https://exploreease.stackroute.io/api/v5/bookings/getByLocationId/"+locationId)
}

addPayment(payment:any): Observable<any> {
  return this.httpClient.post ("https://exploreease.stackroute.io/api/v3/payments/add",payment);
}
  
}
