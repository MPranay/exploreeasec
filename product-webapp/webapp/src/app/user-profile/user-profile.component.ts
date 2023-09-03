import { Component } from '@angular/core';
import { UserService } from 'app/services/user.service';
import { BookingDetailsService } from 'app/services/booking-details.service';

import { LocationService } from 'app/services/location.service';

import { SharedDataService } from 'app/services/shared-data.service';
import { RouterService } from 'app/router.service';



@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {
  userId: number = 0;
  title = 'UserProfile';
  displayedColumns: string[] = ['locationImgs','location','publishedAt'];
  constructor(private UserService:UserService, private bookingservice:BookingDetailsService, private sharedData: SharedDataService, private routerService: RouterService){};

  ngOnInit(): void {
    this.userId = this.sharedData.getUserID();
    this.getUser();
    this.getBookingDetails();
    
   
  } 
  dataSource: any;
  curPass : string ='';
  newPass : string = '';
  

  bookingData: any;
  userData: any;
  
  panelOpenState = false;

  imageUrl: string | null = null;




  convertBase64ToImage(base64String: string) {
    
    const base64Header = base64String.substring(0, 20); // Adjust the length based on your needs

    let mimeType: string | undefined;

    if (base64Header.startsWith('/9j')) {
      mimeType = 'data:image/jpg;base64,';
    } else if (base64Header.startsWith('iVBORw0KGg')) {
      mimeType = 'data:image/png;base64,';
    } else if (base64Header.startsWith('R0lGODlh')) {
      mimeType = 'data:image/gif;base64,';
    }
    
    

    if (mimeType) {
      this.imageUrl = mimeType + base64String;
      console.log(this.imageUrl);
      
    } else {
      console.log('Unknown image type');
    }
  }


  getUser() {
    this.UserService.getData(this.userId).subscribe(
      (response) => {
        if (response) {
          this.userData = response;
          this.convertBase64ToImage(this.userData.profilePicture);

          console.log(this.userData);

        }
        else console.log("No Response from the server");
      });
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

  editProfile(){
    this.routerService.routeToEdit();
  }
}
