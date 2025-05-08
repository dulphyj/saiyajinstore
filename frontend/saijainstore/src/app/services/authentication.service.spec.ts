/*import { TestBed } from '@angular/core/testing';
import { AuthenticationService } from './authentication.service';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { User } from '../common/user';
import { Userdto } from '../common/userdto';
import { JwtClient } from '../common/jwt-client';
import { UserType } from '../common/user-type';

describe('AuthenticationService', () => {

  let service: AuthenticationService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClientTesting(), AuthenticationService]
    });
    service = TestBed.inject(AuthenticationService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should register a user', () => {
    const dummyUser: User = {
      id: 1,
      userName: 'test',
      password: 'test',
      email: 'test@test.com',
      modified: false,
      firstName: '',
      lastName: '',
      userType: UserType.ADMIN
    };

    service.register(dummyUser).subscribe(user => {
      expect(user).toEqual(dummyUser);
    });

    const req = httpMock.expectOne(`${service['apiUrl']}/register`);
    expect(req.request.method).toBe('POST');
    req.flush(dummyUser);

    it('should login a user and return token', () => {
      const userDto: Userdto = { username: 'test', password: 'test' };
      const jwt: JwtClient = {
        token: 'fake-jwt-token',
        id: 0,
        type: ''
      };

      service.login(userDto).subscribe(res => {
        expect(res).toEqual(jwt);
      });

      const req = httpMock.expectOne(`${service['apiUrl']}/login`);
      expect(req.request.method).toBe('POST');
      req.flush(jwt);
    });
  });
})
*/