# Etapa 1: build de Angular
FROM node:18 AS builder

# Crear directorio de la app
WORKDIR /app

# Copiar archivos necesarios
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar el resto del proyecto
COPY . .

# Compilar el proyecto Angular
RUN npm run build -- --configuration production

# Etapa 2: imagen final con Nginx
FROM nginx:alpine
EXPOSE 80

# Copiar archivos compilados al directorio que sirve Nginx
COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

# Reemplazar la configuración de Nginx
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
