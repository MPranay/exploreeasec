import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  constructor(private httpClient: HttpClient) { }
  private locationName: string = '';
  private ticketPrice=2000
  selectedIndex: boolean = false;
  private locationID: number = 0;

  setTemporaryLocationName(name: string){
    this.locationName = name;
  }
  getTemporaryLocationName(){
    return this.locationName;
  }
  
  setTicketPrice(tp: number){
    this.ticketPrice = tp;
  }
  getTicketPrice(){
    return this.ticketPrice;
  }

  setSelectedIndex(){
    this.selectedIndex = !this.selectedIndex;
  }
  getSelectedIndex(){
    return this.selectedIndex;
  }

  setUserID(id: number){
    localStorage.setItem('userID', id.toString());
  }

  getUserID(){
    var id = localStorage.getItem('userID');
    if(id == null){
      return 0;
    }

    return Number.parseInt(id);
  }

  setLocationID(id: number){
    localStorage.setItem('locationID', id.toString());
  }

  getLocationID(){
    var id = localStorage.getItem('locationID');
    if(id == null){
      return 0;
    }

    return Number.parseInt(id);
  }

  setToken(token:string){
    localStorage.setItem('token', token);
  }

  getToken(){
    return localStorage.getItem('token');
  }

  authenticateUser(user: any){
    
    return this.httpClient.post('https://exploreease.stackroute.io/login/authenticate', user);
  }

  sendEmail(data: any){
    return this.httpClient.post('https://exploreease.stackroute.io/api/v7/sendMail', data,{responseType:'text'});
  }

  sendSupport(data: any){
    return this.httpClient.post('https://exploreease.stackroute.io/api/v8/customercare/add', data);
  }

}
