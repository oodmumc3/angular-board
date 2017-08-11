import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BoardService} from '../service/board.service';
import {Observable} from 'rxjs/Observable';
import {IBoard} from '../model/board';
import {Router} from '@angular/router';

@Component({
  selector: 'app-board-post',
  templateUrl: './board-post.component.html',
  styles: ['span.error {color:red}']
})
export class BoardPostComponent implements OnInit {

  private boardForm: FormGroup;
  private genders: string[];

  constructor(
    private formBuilder: FormBuilder,
    private boardService: BoardService,
    private router: Router
  ) {
    this.genders = [ 'male', 'female' ];
  }

  ngOnInit(): void {
    /*
    this.boardForm = new FormGroup({
      'userData': new FormGroup({
        'title': new FormControl('Jin', Validators.required),
        'writer': new FormControl('', Validators.required),
        'gender': new FormControl('male', Validators.required),
      }),
      'contents': new FormControl('', Validators.required),
      'hobbies': new FormArray([
        new FormControl('Cooking')
      ])
    });
    */

    this.boardForm = this.formBuilder.group({
      'userData': this.formBuilder.group({
        'title': ['Jin', Validators.required],
        'writer': ['', [Validators.required, this.exampleValidator]],
        'gender': ['male', Validators.required]
      }),
      'contents': ['', Validators.required],
      'hobbies': this.formBuilder.array([
        this.formBuilder.control('Cooking')
      ])
    });

  }

  onSubmit(): void {
    let board: IBoard = {
      title: this.boardForm.controls['userData'].get('title').value as string,
      writer: this.boardForm.controls['userData'].get('writer').value as string,
      contents: this.boardForm.controls['contents'].value as string,
      datetime: new Date(),
      id: null
    };

    this.boardService.save(board).subscribe(result => {
      if (result) {
        this.router.navigate(['/board']);
      }
    });
  }

  onAddHobby(): void {
    (<FormArray>this.boardForm.controls['hobbies'])
      .push(new FormControl('', Validators.required));
  }

  exampleValidator(control: FormControl): { [s: string]: boolean } {
    if (control.value === 'Example') {
      return { example: true };
    }
    return null;
  }

}
