import { Component } from '@angular/core';
import { RouterService } from '../router.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor (private rs : RouterService) {}

  registerUser()
  {
    this.rs.routeToUserRegistration();
  }

  registerManager()
  {
    this.rs.routeToManager();
  }

}
