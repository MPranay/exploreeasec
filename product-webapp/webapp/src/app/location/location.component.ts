import { Component, OnInit, ViewEncapsulation} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ReviewComponent } from '../review/review.component';
import { LocationService } from '../services/location.service';
import { ActivatedRoute } from '@angular/router';
import { SharedDataService } from '../services/shared-data.service';
import { RouterService } from 'app/router.service';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LocationComponent implements OnInit {
  imgUrl: string[] = [];
  imageUrls: string[] = [];
  locationName: string = '';
  locationAbout: string = '';
  locationTouristAttractions: string = '';
  locationAvailableCommutes: string = '';
  locationLocallyFamous: string = '';
  ticketPrice=0;
  TouristAttractions : string[] = [];
  currentImageIndex: number = 0;
  selectedIndex: number = 0;
  reviews: any[] = [];
  id: number | undefined;
  isUserLoggedIn : boolean = false;
  ngOnInit(): void {
    this.isUserLoggedIn = localStorage.getItem('token') ? true : false;
    this.activatedRoute.params.subscribe(params => {
      this.id = +params['id'];
      console.log(this.id);
    });
    this.getLocationDetails();
    this.changeBackgroundImage();
    
    if(this.sharedData.getSelectedIndex()){
      this.selectedIndex = 4;
      this.sharedData.setSelectedIndex();
    }
    this.getReviews();
    this.TouristAttractions = this.locationTouristAttractions.split(', ');
    console.log(this.TouristAttractions)
  }
  constructor(public dialog: MatDialog, private locationService: LocationService, private activatedRoute: ActivatedRoute, private sharedData: SharedDataService, private router: RouterService) { }

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
      this.imgUrl.push(mimeType + base64String);
      
      console.log(this.imgUrl);
      
    } else {
      console.log('Unknown image type');
    }
  }
  getLocationDetails(){
    this.locationService.getlocationName(this.id).subscribe((data: any) => {
      console.log(data);
      this.imageUrls = data.locationImgs;
      for(let i = 0; i < this.imageUrls.length; i++){
        this.convertBase64ToImage(this.imageUrls[i]);
      }
      this.locationName = data.location;
      this.locationAbout = data.locationDetails;
      this.locationAvailableCommutes = data.availableCommutes;
      this.locationLocallyFamous = data.locallyFamous;
      this.locationTouristAttractions = data.touristAttractions;
      this.ticketPrice=data.ticketPrice;
    });
  }
  changeBackgroundImage() {
    setTimeout(() => {
      this.currentImageIndex = (this.currentImageIndex + 1) % this.imgUrl.length;
      
      this.changeBackgroundImage();
      
    }, 5000); // Change background every 5 seconds (5000 milliseconds)
  }
  

  openReviewDialog() {
    const dialogRef = this.dialog.open(ReviewComponent, {
      width: '500px',
      panelClass: 'rev',
      data: {} // You can pass any data to the dialog if needed
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result!=undefined){
        result.locationID = this.id;
        result.userID = this.sharedData.getUserID();
        console.log('Dialog result:', result);
        this.locationService.addReview(result).subscribe((data: any) => {console.log(data); this.reviews.push(result)});
        this.getReviews();
      }
      
    });
  }

  getStarIcons(rating: number) {
    let starIcons = [];
    for (let i = 0; i < rating; i++) {
      starIcons.push("star");
    }
    for (let i = 0; i < 5 - rating; i++) {
      starIcons.push("star_border");
    }
    return starIcons;
  }

  getReviews(){
    this.locationService.getReviews(this.id).subscribe((data: any) => {
      this.reviews = data;
      this.reviews.reverse();
      console.log(this.reviews);
    });
  }
  bookNow(){
    this.sharedData.setTemporaryLocationName(this.locationName);
    this.sharedData.setTicketPrice(this.ticketPrice);
    this.router.routeToBooking(this.id);
  }
  
  

}
