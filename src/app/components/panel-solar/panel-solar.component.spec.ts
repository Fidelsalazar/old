import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelSolarComponent } from './panel-solar.component';

describe('PanelSolarComponent', () => {
  let component: PanelSolarComponent;
  let fixture: ComponentFixture<PanelSolarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PanelSolarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PanelSolarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
