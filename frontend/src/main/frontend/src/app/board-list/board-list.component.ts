import {Component, OnInit} from '@angular/core';
import {BoardService} from '../service/board.service';
import {IBoard} from '../model/board';

@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styles: [],
  providers: [BoardService]
})
export class BoardListComponent implements OnInit {

  private boardList: IBoard[];

  constructor(private boardService: BoardService) { }

  ngOnInit() {
    this.boardService.findAll().subscribe(board => this.boardList = board);
  }

}
