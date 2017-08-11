import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './layout/header.component';
import { NavBarComponent } from './layout/nav-bar.component';
import {AppRoutingModule} from './app-routing.module';
import { IndexComponent } from './index/index.component';
import { BoardListComponent } from './board-list/board-list.component';
import {HttpModule} from '@angular/http';
import {BoardService} from './service/board.service';
import {APP_CONFIG, APP_DI_CONFIG} from './app-config.constants';
import { BoardPostComponent } from './board-post/board-post.component';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavBarComponent,
    IndexComponent,
    BoardListComponent,
    BoardPostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    BoardService,
    {provide: APP_CONFIG, useValue: APP_DI_CONFIG},
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
