import { Component, Input, OnInit } from '@angular/core';
import { HeaderAdminComponent } from "../header-admin/header-admin.component";
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { ToastrService } from 'ngx-toastr';
import { Category } from '../../common/category';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category',
  imports: [HeaderAdminComponent, CommonModule, RouterModule, FormsModule],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit {

  categories: Category[] = [];
  category: Category = new Category(null, '');
  newCategoryName: string = '';

  constructor(private categoryService: CategoryService, private toastr: ToastrService) { }
  ngOnInit(): void {
    this.getCategories();
  }

  getCategories(): void {
    this.categoryService.getAllCategories()
      .subscribe(category => this.categories = category)
  }
  deleteCategoryById(id: number) {
    this.categoryService.deleteCategory(id)
      .subscribe({
        next: () => {
          this.getCategories();
          this.toastr.info('categoría eliminada exitosamente!', 'Deleted');
        },
        error: (err) => {
          console.error(err);
          if (err.status === 500) {
            this.toastr.warning('No se puede eliminar la categoría porque está siendo utilizada por uno o más productos.', 'Warning');
          } else {
            this.toastr.error('Se produjo un error al eliminar la categoría.', 'Error');
          }
        }
      })
  }

  createCategory(): void {
    if (!this.newCategoryName.trim()) {
      return;
    }
    this.category = { id: null, name: this.newCategoryName };
    this.categoryService.createCategory(this.category).subscribe({
      next: (createdCategory) => {
        this.categories.push(createdCategory);
        this.newCategoryName = '';
        this.toastr.success('Category created successfully!', 'Success');
      },
      error: () => {
        this.toastr.error('Failed to create category', 'Error');
      }
    });
  }
}
