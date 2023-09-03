import { Component } from '@angular/core';
import { RouterService } from 'app/router.service';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent {
  constructor(private router: RouterService){}
  redirect(){
    this.router.routeToHome();
  }
}
