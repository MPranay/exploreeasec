import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/services/user.service';
import { SharedDataService } from 'app/services/shared-data.service';
import { RouterService } from 'app/router.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  userId: number = 0;
  title = 'UserProfile';
  displayedColumns: string[] = ['locationImgs','location','publishedAt'];
  constructor(private UserService:UserService, private sharedData: SharedDataService, private routerService: RouterService){};

  ngOnInit(): void {
    this.userId = this.sharedData.getUserID();
    this.getUser();
    
    
   
  } 
  dataSource: any;
  curPass : string ='';
  newPass : string = '';
  fieldEditDisabled : boolean  = true;
  passEditDisbaled : boolean = true;
  imageChanged : boolean = false;
  bookingData: any;
  userData: any;
  panelOpenState = false;
  imageUrl: string | null = null;
  age : string = '';
  selectedFile: File | null = null;
  url: string | any = "../../../../assets/pfp.png";

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
  resetImage(){
 //set original profile picture here declared above
    this.imageChanged = false;
  }
  imageUpload(event: any) {
    this.imageChanged = true;
    this.selectedFile = event.target.files[0] as File;  
  }

  confirm(){
    this.userData.age = this.convertDateToAge(this.age);
  }



  //Calender to Date

  convertDateToAge(date : string){
      const birthdateArr = '2000-09-19'.split('-');
      const birthYear = parseInt(birthdateArr[0]);
      const birthMonth = parseInt(birthdateArr[1]) - 1; // Months are zero-indexed
      const birthDay = parseInt(birthdateArr[2]);
  
      const today = new Date();
      const currentYear = today.getFullYear();
      const currentMonth = today.getMonth();
      const currentDay = today.getDate();
  
      let age = currentYear - birthYear;
  
      if (currentMonth < birthMonth || (currentMonth === birthMonth && currentDay < birthDay)) {
        age--;
      }
  
      alert(age);
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

 

  editProfile(){
    this.routerService.routeToEdit();
  }
}
