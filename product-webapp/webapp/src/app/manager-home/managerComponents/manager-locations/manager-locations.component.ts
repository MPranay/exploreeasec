import { Component } from '@angular/core';
import { RouterService } from 'app/router.service';
import { LocationService } from 'app/services/location.service';
import { SharedDataService } from 'app/services/shared-data.service';

export class locationDetail{
  ticketPrice=0;
  locationDetails: string ='' ;
  locallyFamous: string ='' ;
  availableCommutes: string ='' ;
  touristAttractions: string ='' ;
}
@Component({
  selector: 'app-manager-locations',
  templateUrl: './manager-locations.component.html',
  styleUrls: ['./manager-locations.component.css']
})
export class ManagerLocationsComponent {
  constructor(private rs : RouterService, private locationService:LocationService, private shared:SharedDataService){
    this.locationService.getlocationName(this.locationId).subscribe((data:any)=>{
      this.currLocation.ticketPrice=data.ticketPrice;
      this.currLocation.locationDetails = data.locationDetails;
        this.currLocation.locallyFamous = data.locallyFamous;
        this.currLocation.availableCommutes = data.availableCommutes;
        this.currLocation.touristAttractions = data.touristAttractions;
        this.locationImages=data.locationImags
    })
  }
  
  
  url : string | any = "../../assets/pfp.png";
  currLocation : locationDetail = new locationDetail;
  locationImages: any[] = [];
  managerId : number =this.shared.getUserID();
  locationId : number =this.shared.getLocationID();
  Route(crr : any){
    if(crr==null || crr==""){
      this.rs.routeToAddLoc()
    }
    else{
      this.rs.routeToEditLoc()
    }
  }

}

