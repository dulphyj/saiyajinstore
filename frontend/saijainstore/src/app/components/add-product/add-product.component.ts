import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../common/product';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { Router } from '@angular/router';
import { Category } from '../../common/category';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnInit {

  categories: Category[] = [];
  product: Product = new Product(null, '', '', '', 0, '', 1, 0);
  isSubmitting: boolean = false;
  message: string = '';

  constructor(private productService: ProductService, private categoryService: CategoryService, private router: Router) { }
  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    this.categoryService.getAllCategories().subscribe(category =>
      this.categories = category);
  }


  createProduct(): void {
    this.isSubmitting = true;
    this.message = '';

    if (this.product.categoryId === 0) {
      this.message = 'Select a valid category';
      this.isSubmitting = false;
      return;
    }

    this.productService.createProduct(this.product).subscribe({
      next: (response) => {
        console.log("Product successfully created:", response);
        this.message = 'Product successfully created!';
        setTimeout(() => this.router.navigate(['/admin/products']), 700);
      },
      error: (error) => {
        console.error("Error creating product", error);
        this.message = 'Error creating product';
      },
      complete: () => {
        this.isSubmitting = false;
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/admin/products']);
  }
}
