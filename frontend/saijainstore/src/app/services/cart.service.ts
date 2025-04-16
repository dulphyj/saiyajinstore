import { Injectable } from '@angular/core';
import { ItemCart } from '../common/item-cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private items: Map<number, ItemCart> = new Map<number, ItemCart>();
  itemList: ItemCart[] = [];

  constructor() { }

  addItemCart(item: ItemCart): void {
    this.items.set(item.id, item);
  }

  deleteItemCart(productId: number): void {
    this.items.delete(productId);
    this.items.forEach((value, key) => {
      console.log(key, value);
    });
  }

  totalCart() {
    let totalCard: number = 0;
    this.items.forEach((value, key) => {
      totalCard += value.getTotalPrice();
    });
    return totalCard;
  }

  converToListFromMap() {
    this.itemList.splice(0, this.itemList.length);
    this.items.forEach((value, key) => {
      this.itemList.push(value);
      console.log(this.itemList, value)
    });
    return this.itemList;
  }
}


