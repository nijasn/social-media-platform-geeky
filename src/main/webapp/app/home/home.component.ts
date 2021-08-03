import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { PostsService } from '.././core/posts/posts.service';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';

@Component({
  selector: 'mpl-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  imageSrc: any = [];
  mediaImages: any;
  content: any = '';
  post: any;
  posts: any = [];
  invalidForm: any = false;
  private readonly destroy$ = new Subject<void>();

  constructor(private accountService: AccountService, private router: Router, private postService: PostsService) {}

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));
    this.getPosts();
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  selectFiles = (): void => {
    document.getElementById('media')?.click();
  };

  removeImage = (index: number): void => {
    this.mediaImages.splice(index, 1);
    this.imageSrc.splice(index, 1);
  };

  isNull = (x: any): boolean => x === null || x === undefined || x === '';

  submitPost = (): void => {
    this.invalidForm = this.isNull(this.content);
    const formData = new FormData();
    formData.append('content', this.content);
    formData.append('media', this.imageSrc);

    this.postService.addPost(formData).subscribe((res: any) => {
      this.post = res;
      this.invalidForm = false;
      this.getPosts();
    });
  };

  getPosts = (): void => {
    this.postService.getAllPosts().subscribe((res: any) => {
      this.posts = res;
    });
  };

  onFileSelect = (eventO: any): void => {
    if (!this.isNull(eventO.target.files[0])) {
      this.mediaImages = eventO.target.files;
      for (let i = 0; i < this.mediaImages.length; i++) {
        const reader = new FileReader();
        reader.onload = (event_: any) => {
          this.imageSrc.push(event_.target.result);
        };
        reader.readAsDataURL(eventO.target.files[i]);
      }
    }
  };
}
