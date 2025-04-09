export class Product {
    constructor(
        public id: null | number,
        public name: string,
        public code: string,
        public description: string,
        public price: number,
        public urlImage: string,
        public userId: number,
        public categoryId: number,
    ) { }
}
