export class ItemCart {

    constructor(
        public id: number,
        public name: string,
        public price: number,
        public quantity: number
    ) { }

    getTotalPrice(): number {
        return this.price * this.quantity;
    }
}
