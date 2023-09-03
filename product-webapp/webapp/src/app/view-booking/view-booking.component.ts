import { Component } from '@angular/core';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent {
  bookings: any[] = [{ id: 1, name: 'Booking 1', date: '2023-08-16' },
  { id: 2, name: 'Booking 2', date: '2023-08-17' },
  { id: 3, name: 'Booking 3', date: '2023-08-18' },
  { id: 3, name: 'Booking 3', date: '2023-08-18' },
  { id: 3, name: 'Booking 3', date: '2023-08-18' },
  { id: 3, name: 'Booking 3', date: '2023-08-18' },
  { id: 3, name: 'Booking 3', date: '2023-08-18' },
  { id: 3, name: 'Booking 3', date: '2023-08-18' },];

  // constructor(private apiService: ApiService) {}

  // ngOnInit() {
  //   this.apiService.getBookings().subscribe((data: any[]) => {
  //     this.bookings = data;
  //   });
  // }

  // deleteBooking(bookingId: number) {
  //   this.apiService.deleteBooking(bookingId).subscribe(() => {
  //     this.bookings = this.bookings.filter(booking => booking.id !== bookingId);
  //   });
  // }
}
