import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardPostComponent } from './board-post.component';

describe('BoardPostComponent', () => {
  let component: BoardPostComponent;
  let fixture: ComponentFixture<BoardPostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoardPostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
