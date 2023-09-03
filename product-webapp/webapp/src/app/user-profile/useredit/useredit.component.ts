import { Component, OnInit } from '@angular/core';
import { RouterService } from 'app/router.service';
import { SharedDataService } from 'app/services/shared-data.service';
import { UserService } from 'app/services/user.service';
import Swal from 'sweetalert2';

export class useredit {
  username: string = '';
  name: string = '';
  email: string = '';
  password: string = '';
  contact: number | null = null;
  age: number | null = null;
  gender: ("male" | "female" | "other" | string) = '';
  profilePicture: any;
  walletBalance: number = 0;
  isManager: boolean = false;
  userType: string = "USER";

}



@Component({
  selector: 'app-useredit-user',
  templateUrl: './useredit.component.html',
  styleUrls: ['./useredit.component.css']
})
export class UserEditComponent implements OnInit {
  constructor(private rs: RouterService, private userService: UserService, private sharedData: SharedDataService) { }

  hide = true;
  inputField: string = '';
  currUser: useredit = new useredit;
  url: string | any = "../../assets/pfp.png";
  userId: number = 0;

  ngOnInit(): void {
    this.userId = this.sharedData.getUserID();
    this.userService.getData(this.userId).subscribe((data) => {
      this.currUser = data;
      this.convertBase64ToImage(this.currUser.profilePicture);
      console.log(this.currUser);
    });

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

  imageUpload(event: any) {
    console.log(event);
    if (event.target.files[0]) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = ((e): any => {
        this.url = e.target?.result;
        //var bstring : any = e.target?.result;
        //console.log(bstring);
        this.currUser.profilePicture = event.target.files[0];
        // console.log("profile pic -" ,this.currUser.pfp)
        ;
      })
    }
  }



  onSubmit() {
    console.log(this.currUser);

    if (!this.currUser.email || !this.currUser.age || !this.currUser.contact || !this.currUser.gender
      || !this.currUser.password || !this.currUser.name || !this.currUser.username)
      Swal.fire({
        title: 'Error!',
        text: 'All Fields are necessary',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
    else if (this.currUser.password.length < 1)
      Swal.fire({
        title: 'Error!',
        text: 'Password should be of minimum 8 Length',
        icon: 'error',
        confirmButtonText: 'Okay',
      });

    else if (this.currUser.age < 18)
      Swal.fire({
        title: 'Error!',
        text: 'Age should be greatr than 18',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
    else if (this.currUser.contact.toString().length !== 10)
      Swal.fire({
        title: 'Error!',
        text: 'Enter a Valid 10 Digit Contact number',
        icon: 'error',
        confirmButtonText: 'Okay',
      });
    else {
      const formData = new FormData();
      formData.append('profilePicture', this.currUser.profilePicture);
      formData.append('username', this.currUser.username);
      formData.append('name', this.currUser.name);
      formData.append('email', this.currUser.email);
      formData.append('password', this.currUser.password);
      formData.append('contact', this.currUser.contact!.toString());
      formData.append('age', this.currUser.age!.toString());
      formData.append('gender', this.currUser.gender);
      formData.append('walletBalance', this.currUser.walletBalance.toString());
      formData.append('userType', this.currUser.userType);
      console.log(formData);
      
      this.userService.updateUser(this.userId, formData).subscribe((data) => {
        console.log(data);
        Swal.fire({
          title: 'Success!',
          text: 'Update Successful,Redirecting to Profile.',
          icon: 'success',
          confirmButtonText: 'Okay',
        });
        this.rs.routeToUserProfile();
      }, (err) => {
        console.log(err);
        Swal.fire({
          title: 'Error!',
          text: 'Update Unsuccessful',
          icon: 'error',
          confirmButtonText: 'Okay',
        });
      });
    }
  }
}
