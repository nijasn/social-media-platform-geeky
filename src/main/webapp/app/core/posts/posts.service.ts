import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PostsService {
  headerObj: any = {
    'Content-Type': 'application/json',
    Accept: 'application/json',
  };
  constructor(private http: HttpClient) {}

  addPost(payload: any): any {
    return this.http.post('api/posts', payload);
  }

  getAllPosts(): any {
    return this.http.get('api/posts/all', {
      headers: this.headerObj,
    });
  }
}
