import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { HomeService } from '../../services/home.service';
import { Category } from '../../common/category';

@Component({
  selector: 'app-product-filters',
  imports: [CommonModule, FormsModule],
  templateUrl: './product-filters.component.html',
  styleUrl: './product-filters.component.css'
})
export class ProductFiltersComponent {
  @Output() filtersChanged = new EventEmitter<any>();

  filters = {
    name: '',
    price: null,
    categoryId: null
  };

  categories: Category[] = [];

  constructor(private homeService: HomeService) {

  }

  ngOnInit(): void {
    this.homeService.getAllCategories().subscribe(
      data => {
        this.categories = data;
      });
  }



  applyFilters() {
    console.log(this.filters);
    this.filtersChanged.emit(this.filters);
  }

}
