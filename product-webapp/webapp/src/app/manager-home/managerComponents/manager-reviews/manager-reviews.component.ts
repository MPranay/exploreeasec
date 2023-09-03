import { Component } from '@angular/core';

@Component({
  selector: 'app-manager-reviews',
  templateUrl: './manager-reviews.component.html',
  styleUrls: ['./manager-reviews.component.css']
})
export class ManagerReviewsComponent {

  review : any[] = [{
    user : "Ram",
    rating : 4,
    comment : " Very Good"
  },
  {
    user : "Raju",
    rating : 5,
    comment : "Good"
  },
  {
    user : "Ravi",
    rating : 4,
    comment : " Very Good"
  },
  {
    user : "Shrey",
    rating : 4,
    comment : " Very Good"
  },
  {
    user : "Raju",
    rating : 5,
    comment : "Good"
  },
  {
    user : "Ravi",
    rating : 4,
    comment : " Very Good"
  },] 

}
