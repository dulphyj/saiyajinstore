export class JwtClient {
    constructor(
        public token: string,
        public id: number,
        public type: string
    ) { }
}
