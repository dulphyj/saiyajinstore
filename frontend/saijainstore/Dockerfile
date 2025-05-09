# Etapa 1: build de Angular
FROM node:19-alpine AS builder

# Establece directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de dependencias e instala
COPY package.json ./
RUN npm install

# Copia el resto del proyecto y compila Angular en modo producción
COPY . .
RUN npm run build -- --configuration production

# Etapa 2: imagen final con Nginx
FROM nginx:alpine

# Copia archivos compilados al directorio que sirve Nginx
COPY --from=builder /app/dist/saijainstore /usr/share/nginx/html

# Expone el puerto 80
EXPOSE 80

# Inicia Nginx en modo foreground
CMD ["nginx", "-g", "daemon off;"]
