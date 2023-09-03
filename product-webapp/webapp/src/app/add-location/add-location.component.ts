import { Component } from '@angular/core';
import { RouterService } from '../router.service';
import { LocationService } from 'app/services/location.service';
import Swal from 'sweetalert2';
import { SharedDataService } from 'app/services/shared-data.service';

export class locationDetail {
  ticketPrice = 0;
  locationDetails: string = '';
  locallyFamous: string = '';
  availableCommutes: string = '';
  touristAttractions: string = '';
}

@Component({
  selector: 'app-add-location',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css']
})
export class AddLocationComponent {

  constructor(private rs: RouterService, private locationService: LocationService, private sharedData: SharedDataService) {
  }

  url: string | any = "../../assets/pfp.png";
  currLocation: locationDetail = new locationDetail;
  locationImages: File[] = [];
  managerId: number | null = this.sharedData.getUserID();

  imageUpload(event: any) {
    this.locationImages = event.target.files;
  }

  onSubmit() {
    if (
      !this.currLocation.locationDetails || !this.currLocation.locallyFamous || !this.currLocation.availableCommutes ||
      !this.currLocation.touristAttractions || this.locationImages.length === 0 ) {
      Swal.fire('Error', 'Please fill in all fields', 'error');
      return;
    }


    if (this.locationImages.length > 0 && this.managerId !== null) {
    const formData = new FormData;
    formData.append('ticketPrice', this.currLocation.ticketPrice.toString())
    formData.append('locationDetails', this.currLocation.locationDetails);
    formData.append('locallyFamous', this.currLocation.locallyFamous);
    formData.append('availableCommutes', this.currLocation.availableCommutes);
    formData.append('touristAttractions', this.currLocation.touristAttractions);
    for (const image of this.locationImages) {
      formData.append('locationImgs', image);
    }

    this.locationService.addLocationDetails(this.managerId, formData).subscribe((data: any) => {
     
    });

    Swal.fire('Success', 'Added Details Successfully!', 'success');
    this.rs.routeToManagerHome();
  }
  }
}
