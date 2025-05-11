# 🎨 Saiyajin Store - Frontend

This is the frontend of the **Saiyajin Store** project, built with **Angular 18**. It consumes a REST API provided by the Spring Boot backend and provides a full e-commerce experience including product browsing, cart management, user registration/login, PayPal payments, and an admin panel.

---

## 🚀 Features

- 🛍️ Product browsing and filtering
- 🛒 Cart and checkout flow
- 👤 User registration and login
- 🔒 Route protection using guards
- 🧾 Authenticated requests with interceptors
- 💳 PayPal integration for payments
- ⚙️ Admin panel for managing:
  - Products
  - Categories
  - Users
  - Orders

---

## 🧰 Technologies Used

- Angular 18+
- TypeScript
- Bootstrap
- Angular Router
- Angular Services for API consumption
- Interceptors for JWT header handling
- Route Guards for access control
- ngx-toastr for notifications

---

## 📦 Running the App Locally

1. Go into the project directory:

```bash
cd frontend/saijainstore
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
ng serve
```

4. Open your browser and go to:

http://localhost:4200

## ⚙️ Environment Configuration

Edit the environment files:

src/environments/environment.ts

src/environments/environment.prod.ts

Example content:

```bash
export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080/api/v1'
};
```

## 🔐 Security

**Guards:** Restrict access to certain routes (e.g., admin panel, user dashboard)

**Interceptors:** Automatically attach JWT tokens to outgoing HTTP requests via the Authorization header


## 🧪 Testing
📝 There are currently no unit or e2e tests configured.

## 🧼 Code Style

- TypeScript best practices

- Separation of concerns using components, services, and modules

- Responsive UI using Bootstrap

## 📌 Notes

The app relies on the backend being available at the API base URL defined in the environment files

Make sure the backend is running before using the frontend

