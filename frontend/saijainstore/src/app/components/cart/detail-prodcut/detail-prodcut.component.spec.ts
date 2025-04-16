import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailProdcutComponent } from './detail-prodcut.component';

describe('DetailProdcutComponent', () => {
  let component: DetailProdcutComponent;
  let fixture: ComponentFixture<DetailProdcutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailProdcutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailProdcutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
