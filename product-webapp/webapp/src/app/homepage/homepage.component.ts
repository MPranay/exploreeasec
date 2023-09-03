import { Component, OnInit } from '@angular/core';
import { RouterService } from 'app/router.service';

export class carouselImage {
  src : string = '';
  heading : string = '';
  desc : string = '';
}

export const observer = new IntersectionObserver((entries)=>{
  entries.forEach((entry)=>{
    //console.log('entry working')
    if(entry.isIntersecting) entry.target.classList.add('show');
    else entry.target.classList.remove('show');
  })
})

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})



export class HomepageComponent  implements OnInit{
  constructor(private rs : RouterService) {}

  index : number = 0;

  imageUrls : carouselImage[] = [
    {
      src : '../../assets/Leh.png',
      heading : "Leh , Ladakh",
      desc : "Leh Ladakh: Land of High Passes and Transcendent Beauty"
    },
    {
      src : '../../assets/TajMahal.jpg',
      heading : "Taj Mahal , Agra",
      desc : "Eternal Splendor: Journey to the Taj Mahal, India's Timeless Jewel"
    },
    {
      src : '../../assets/srinagar.jpg',
      heading : "Srinagar , Jammu & Kashmir",
      desc : "Srinagar: The Jewel of Kashmir's Paradise, Where Nature Meets Serenity"
    },
  ];


  registerButton(){
    this.rs.routeToRegister();
  }

  loginButton(){
    this.rs.routeToLogin();
  }
  scrollToEnd(){
    window.scrollTo(0, document.body.scrollHeight);
  }

  ngOnInit(): void {
    const hiddenElements = document.querySelectorAll('.hidden');
    hiddenElements.forEach((element)=>{
    //console.log('hidden loop working')
    observer.observe(element);
})
  }

}
