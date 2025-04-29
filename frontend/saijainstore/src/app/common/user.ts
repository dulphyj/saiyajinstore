import { UserType } from "./user-type";

export class User {
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
