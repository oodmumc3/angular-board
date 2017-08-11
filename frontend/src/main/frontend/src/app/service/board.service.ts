import {Inject, Injectable, OnInit} from '@angular/core';
import {IAppConfig} from '../app-config';
import {APP_CONFIG} from '../app-config.constants';
import {Http, RequestOptions, RequestOptionsArgs} from '@angular/http';
import 'rxjs/add/operator/map';
import {IBoard} from '../model/board';
import {Observable} from 'rxjs/Observable';
import {Headers} from '@angular/http';

@Injectable()
export class BoardService implements OnInit{

  private API_URL: string = 'board';
  private headers: Headers = new Headers({'Content-Type': 'application/json'});

  constructor(
    @Inject( APP_CONFIG ) private config: IAppConfig,
    private http: Http
  ) {
    this.API_URL = `${config.API_END_POINT}/board`;
  }

  ngOnInit(): void {
  }

  findAll(): Observable<IBoard[]> {
    return this.http.get(this.API_URL)
      .map(resp => resp.json() as IBoard[]);
  }

  save(board: IBoard): Observable<any> {
    return this.http.post(this.API_URL, JSON.stringify(board), new RequestOptions({headers: this.headers}));
  }
}
