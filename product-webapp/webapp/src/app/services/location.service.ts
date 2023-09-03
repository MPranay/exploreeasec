import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private httpClient: HttpClient) { }

  addManager(manager: FormData){
    return this.httpClient.post('https://exploreease.stackroute.io/api/v2/manager/add', manager,{responseType:'text'});
  }

  getReviews(locationID: number|undefined){
    return this.httpClient.get('https://exploreease.stackroute.io/api/v4/reviews/getAllReviewsByLocation/'+locationID);
  }

  addReview(review: any){
    return this.httpClient.post('https://exploreease.stackroute.io/api/v4/reviews/add', review);
  }
  getlocationName(locationID:number|undefined){
    return this.httpClient.get('https://exploreease.stackroute.io/api/v2/manager/location/get/'+locationID);
  }

  addLocationDetails( managerId : number,formData:FormData){
    return this.httpClient.put('https://exploreease.stackroute.io/api/v2/manager/update/location/'+managerId,formData);
  }

  getManager(email: string){
    return this.httpClient.get('https://exploreease.stackroute.io/api/v2/manager/get/'+email);
  }
  getManagerById(managerId: number){
    return this.httpClient.get('https://exploreease.stackroute.io/api/v2/manager/getbyID/'+managerId);
  }
  updateManager(email: string , manager:any){
    return this.httpClient.put('https://exploreease.stackroute.io/api/v2/manager/update/'+email,manager);
  }
  
}
