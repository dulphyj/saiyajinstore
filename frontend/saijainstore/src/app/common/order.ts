import { OrderProduct } from "./order-product";
import { OrderState } from "./order-state";

export class Order {
    constructor(
        public id: number | null,
        public dateCreated: Date,
        public orderProducts: OrderProduct[],
        public userId: number,
        public state: OrderState
    ) { }

    getTotal(): number {
        let total = 0;
        this.orderProducts.forEach((orderProduct) => {
            total += orderProduct.price * orderProduct.quantity;
            console.log("total ", total)
        });
        return total;
    }
}
