import { Component, OnInit } from '@angular/core';
import { RouterService } from '../router.service';
import { LocationService } from 'app/services/location.service';
import { SharedDataService } from 'app/services/shared-data.service';
import Swal from 'sweetalert2';
export class locationDetail{
  ticketPrice=0;
  locationDetails: string ='' ;
  locallyFamous: string ='' ;
  availableCommutes: string ='' ;
  touristAttractions: string ='' ;
}
@Component({
  selector: 'app-edit-location',
  templateUrl: './edit-location.component.html',
  styleUrls: ['./edit-location.component.css']
})
export class EditLocationComponent {
  constructor(private rs : RouterService, private locationService:LocationService, private sharedData: SharedDataService){
    this.locationService.getlocationName(this.locationId).subscribe((data:any)=>{
      this.currLocation.ticketPrice=data.ticketPrice;
      this.currLocation.locationDetails = data.locationDetails;
        this.currLocation.locallyFamous = data.locallyFamous;
        this.currLocation.availableCommutes = data.availableCommutes;
        this.currLocation.touristAttractions = data.touristAttractions;
    })
  }
  
  
  url : string | any = "../../assets/pfp.png";
  currLocation : locationDetail = new locationDetail;
  locationImages: File[] = [];
  managerId = this.sharedData.getUserID();
  locationId = this.sharedData.getLocationID();

imageUpload(event: any) {
  this.locationImages = event.target.files;
}
onSubmit(){


  if (this.locationImages.length > 0 && this.managerId !== null) {
    
    console.log(this.currLocation);
    const formData = new FormData;
    formData.append('ticketPrice',this.currLocation.ticketPrice.toString())
    formData.append('locationDetails', this.currLocation.locationDetails);
    formData.append('locallyFamous', this.currLocation.locallyFamous);
    formData.append('availableCommutes', this.currLocation.availableCommutes);
    formData.append('touristAttractions', this.currLocation.touristAttractions);
    for (const image of this.locationImages) {
      formData.append('locationImgs', image);
    }
  
    console.log(formData);
    this.locationService.addLocationDetails(this.managerId,formData).subscribe((data: any) => {
      console.log(data);
    });
    Swal.fire('Success', 'Added Details Successfully!', 'success');
    this.rs.routeToManagerHome();
  
}
}
}
