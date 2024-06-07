import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AireComponent } from './aire.component';

describe('AireComponent', () => {
  let component: AireComponent;
  let fixture: ComponentFixture<AireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AireComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
