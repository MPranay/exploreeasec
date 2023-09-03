
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable,OnInit } from '@angular/core';
import { user } from 'app/user';
import { Observable, map } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService implements OnInit {

  private url:string='';
  constructor(private httpClient :HttpClient) {
    this.url = 'https://exploreease.stackroute.io/api/v6/'
}
ngOnInit(): void {
}

  getData(id:number): Observable<any> {
   
    return this.httpClient.get<Array<any>>(this.url+"users/"+id);    
  }
  registerUser(user:any){
    return this.httpClient.post(this.url+"register",user,{responseType:'text'});
  }
  getUser(email: string){
    return this.httpClient.get(this.url+"users/getByEmail/"+email);
  }
  updateUser(id:number, user:any): Observable<any>{
    return this.httpClient.put(this.url+"users/update/"+id,user,{responseType:'text'});
  }
}

