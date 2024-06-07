import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PresurizadorComponent } from './presurizador.component';

describe('PresurizadorComponent', () => {
  let component: PresurizadorComponent;
  let fixture: ComponentFixture<PresurizadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PresurizadorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PresurizadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
