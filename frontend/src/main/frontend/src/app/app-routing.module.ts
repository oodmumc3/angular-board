import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IndexComponent} from './index/index.component';
import {BoardListComponent} from './board-list/board-list.component';
import {BoardPostComponent} from './board-post/board-post.component';

const routes: Routes = [
  {path: '', component: IndexComponent},
  {path: 'board', component: BoardListComponent},
  {path: 'board/post', component: BoardPostComponent},
  {path: '**', redirectTo: ''}
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports : [ RouterModule ]
})
export class AppRoutingModule { }
