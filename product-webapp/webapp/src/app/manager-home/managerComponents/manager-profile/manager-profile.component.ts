import { Component } from '@angular/core';
import { LocationService } from 'app/services/location.service';
import { SharedDataService } from 'app/services/shared-data.service';
export class manager {
  managerId : number=0;
  username : string = 'ManagerName';
  name : string = 'ManagerFullname';
  email : string = 'manager@gmail.com';
  password : string = '';
  contact : number  = 1234567891;
  age : number  = 25;
  gender : ("male" | "female" | "other" | string) = 'male';
  profilePicture : any ;
  locationId : number = 5;
  userType : string = "MANAGER";
  location : string = 'Agra';
  
  
  }

@Component({
  selector: 'app-manager-profile',
  templateUrl: './manager-profile.component.html',
  styleUrls: ['./manager-profile.component.css']
})
export class ManagerProfileComponent {

  constructor(private locationService:LocationService,private sharedData: SharedDataService){
    locationService.getManagerById(sharedData.getUserID()).subscribe((data:any)=>{
      
      this.managerOne.managerId=data.managerId;
      this.managerOne.username=data.username;
      this.managerOne.email=data.email;
      this.managerOne.contact=data.contact;
      this.managerOne.gender=data.gender;
      this.managerOne.location=data.location;
      this.managerOne.locationId=data.locationId;
      this.managerOne.age=data.age;
      this.managerOne.password=data.password;
      this.managerOne.name=data.name
      this.managerOne.profilePicture=data.profilePicture;
      this.convertBase64ToImage(data.profilePicture);
    });
    
  }
  url: string | any = "../../../../assets/pfp.png";
  fieldEditDisabled : boolean = true;
  passEditDisbaled : boolean = true;
  imageChanged : boolean = false;
  managerOne : manager = new manager;
  pfpOriginal : any = this.managerOne.profilePicture; //Save original for Reset Feature
  curPass : string = '';
  newPass : string ='';



  enableFieldEdit(){
    this.fieldEditDisabled = false;
  }
  disableFieldEdit(){
    this.fieldEditDisabled = true;
  }
  enablePasswordEdit(){
    this.passEditDisbaled = false;
  }
  disablePasswordEdit(){
    this.passEditDisbaled = true;
  }
  reset(){
    this.fieldEditDisabled  = true;
    this.passEditDisbaled  = true;
  }
  updateImage(){

    //Profile picture is already updated ,
    // just send rest of the data along with 
    //new profile picture to update profile picture only
  }
  resetImage(){
    this.managerOne.profilePicture = this.pfpOriginal;
    this.url = "../../../../assets/pfp.png"; //set original profile picture here declared above
    this.imageChanged = false;
  }

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
      this.url = mimeType + base64String;
      console.log(this.url);

    } else {
      console.log('Unknown image type');
    }
  }

  selectedFile: File | null = null;

  imageUpload(event: any) {
    this.selectedFile = event.target.files[0] as File;
      
    }
  
  onConfirm(){
    
    const formData = new FormData;
    formData.append('username', this.managerOne.username);
    formData.append('managerId', this.managerOne.managerId.toString());
      formData.append('name', this.managerOne.name);
      
      
      formData.append('contact', this.managerOne.contact.toString());
      formData.append('age', this.managerOne.age.toString());
      formData.append('gender', this.managerOne.gender);
      if(this.selectedFile){
      formData.append('profilePicture', this.selectedFile);
      }
    this.locationService.updateManager(this.managerOne.email,formData).subscribe();

  }

}
