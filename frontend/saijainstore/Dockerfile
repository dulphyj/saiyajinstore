# Etapa 1: build de Angular
FROM node:20-alpine AS builder

# Crear directorio de la app
WORKDIR /app

# Copiar archivos necesarios
COPY package.json package-lock.json ./
COPY angular.json ./angular.json

# Instalar dependencias
RUN npm install

# Instalar Angular CLI globalmente
RUN npm install -g @angular/cli

# Copiar el resto del proyecto
COPY . .

# Compilar el proyecto Angular
RUN npm run ng build -- --configuration=production

# Etapa 2: imagen final con Nginx
FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*

# Copiar archivos compilados al directorio que sirve Nginx
COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

# Reemplazar la configuración de Nginx
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
