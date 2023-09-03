import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouterService } from 'app/router.service';

export class carouselImage {
  src : string = '';
  heading : string = '';
  desc : string = '';
  locationId : number = 0;
  state : string = '';
}

export class carouselImages {
  locationImags : any[] = [];
  location : string = '';
  locationDetails : string = '';
  locationId : number = 0;
  locationImgs: any;
  // state : string = '';
}

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  constructor(private http : HttpClient, private rs: RouterService){}

  filterField : string = 'Select One';
  index : number = 0;
  imageUrls : carouselImages[] =  [];
  imgurl: carouselImages= new carouselImages;
  mostPopular : carouselImage[] = [];
  indianStates: any[] = [];
  filterData : any[] = [];
  filteredItems : carouselImage[] = [];
  filterDiv : boolean = false;

  filterButton(){
    this.filteredItems = [];
    this.filterDiv = true;
    this.filterData.forEach((item)=> 
    {
      if(item.state === this.filterField)
      this.filteredItems.push(item);
    });


    console.log(this.filteredItems , '- search array')
      
  }
  closeFilterButton(){
    this.filteredItems = [];
    this.filterDiv = false;
  }

  
  ngOnInit(): void {
    this.http.get<string[]>('assets/data.json').subscribe(data => {
      this.indianStates = data;
      
    })
    this.http.get<carouselImages[]>('http://localhost:9090/api/v2/manager/location/getAll').subscribe(data =>{
      data.forEach(d => {
        this.imgurl=d;
        this.imageUrls.push(this.imgurl)
      });
      // console.log(this.imgurl.locationImgs);
      
      
    
  })
    this.mostPopular = [
      {
        src : '../../assets/goa.jpg',
        heading : "Baga Beach",
        desc : "Lost in the embrace of Baga Beach, where golden sands meet the endless horizon, time becomes an art of leisure and the rhythm of waves composes a symphony of serenity.",
        locationId : 18,
        state : "Goa"
      },
      {
        src : '../../assets/indiaGate.jpg',
        heading : "India Gate , Delhi",
        desc : "India Gate, an eternal sentinel of valor and unity, stands tall as a tribute to the heroes who etched their sacrifice into the annals of our nation's pride, a monument that unites every heartbeat with the spirit of India",
        locationId : 8,
        state : "Delhi",
      },
      {
        src : '../../assets/redFort.jpg',
        heading : "Red Fort , New Delhi",
        desc : "Red Fort: A resplendent testament to India's grand history, where scarlet walls echo the tales of emperors and the spirit of a nation's legacy.",
        locationId : 6,
        state : "Delhi"
      },    
    ];
  
    this.mostPopular.forEach((item)=>{
      this.filterData.push(item);
    });
    this.imageUrls.forEach((item)=>{
        this.filterData.push(item);
      }) 
  
  }

    routeTo(Id: number){
      this.rs.routeToLocation(Id);
    }

}


