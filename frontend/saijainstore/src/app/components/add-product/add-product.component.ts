import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../common/product';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../../common/category';
import { CloudinaryService } from '../../services/cloudinary.service';
import { ToastrService } from 'ngx-toastr';
import { environment } from '../../../environments/environment';
import { HeaderAdminComponent } from "../header-admin/header-admin.component";
import { SessionStorageService } from '../../services/session-storage.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderAdminComponent],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnInit {

  categories: Category[] = [];
  product: Product = new Product(null, '', '', '', 0, '', 1, 0);
  isSubmitting: boolean = false;
  message: string = '';
  selectFile!: File;
  imagePreview: string | ArrayBuffer | null = null;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private cloudinaryService: CloudinaryService,
    private sesionStorage: SessionStorageService,
    private toastr: ToastrService
  ) { }
  ngOnInit(): void {
    this.getProductById();
    this.getCategories();
    this.product.userId = this.sesionStorage.getItem('token').id;

  }

  getCategories(): void {
    this.categoryService.getAllCategories().subscribe(category =>
      this.categories = category);
  }


  createProduct(): void {
    this.checkCategory();

    if (this.selectFile) {
      this.cloudinaryService.uploadImage(this.selectFile).subscribe({
        next: (response) => {
          this.product.urlImage = response.url;
          this.saveProduct();
        },
        error: (error) => {
          console.error("Error uploading image", error);
          this.message = 'the image exceeds the allowed size';
          this.isSubmitting = false;
        }
      });
    } else {
      this.product.urlImage = this.getDefaultImage();
      this.saveProduct();
    }
  }

  private saveProduct(): void {
    this.productService.createProduct(this.product).subscribe({
      next: () => {
        this.toastr.success("product created successfully", "Success")
        setTimeout(() => this.router.navigate(['/admin/products']), 700);
      },
      error: (error) => {
        console.error("Error creating product", error);
        this.toastr.error("Error creating product", "Error")
      },
      complete: () => {
        this.isSubmitting = false;
      }
    });
  }

  updateProduct(): void {
    this.checkCategory();
    const formData = new FormData();
    formData.append("product", new Blob([JSON.stringify(this.product)], { type: "application/json" }));
    if (this.selectFile) {
      formData.append("file", this.selectFile);
    }
    this.productService.updateProduct(this.product.id!, formData).subscribe({
      next: () => {
        this.toastr.success('product updated successfully', 'Succes')
        setTimeout(() => this.router.navigate(['/admin/products']), 700);
      },
      error: (error) => {
        console.error("Error updating product", error);
        this.toastr.error("Error updating product", "Error")
      },
      complete: () => {
        this.isSubmitting = false;
      }
    })
  }


  onCancel(): void {
    this.router.navigate(['/admin/products']);
  }

  getProductById(): void {
    this.activatedRoute.params.subscribe(
      prod => {
        let id = prod['id'];
        if (id) {
          this.productService.getProductById(id).subscribe(
            p => {
              this.product.id = p.id;
              this.product.code = p.code;
              this.product.description = p.description;
              this.product.name = p.name;
              this.product.price = p.price;
              this.product.urlImage = p.urlImage ? p.urlImage : this.getDefaultImage();
              this.product.categoryId = p.categoryId;
            });
        }
      }
    )
  }

  private getDefaultImage(): string {
    return `${environment.imgDefault}`;
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectFile = file;

      const reader = new FileReader();
      reader.onload = (e) => {
        this.imagePreview = e.target?.result ?? null;
      };
      reader.readAsDataURL(file);
    }
  }

  checkCategory(): void {
    this.isSubmitting = true;
    this.message = '';

    if (this.product.categoryId === 0) {
      this.message = 'Select a valid category';
      this.isSubmitting = false;
      return;
    }
  }

  onSubmit() {
    if (this.product.id) {
      this.updateProduct();
    } else {
      this.createProduct();
    }
  }
}
