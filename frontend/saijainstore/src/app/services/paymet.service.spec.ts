import { TestBed } from '@angular/core/testing';

import { PaymetService } from './paymet.service';

describe('PaymetService', () => {
  let service: PaymetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaymetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
