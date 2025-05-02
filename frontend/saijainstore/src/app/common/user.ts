import { UserType } from "./user-type";
export class User {
    public modified: boolean = false;
    constructor(
        public id: null | number,
        public userName: string,
        public firstName: string,
        public lastName: string,
        public email: string,
        public password: string,
        public userType: UserType
    ) { }
}
