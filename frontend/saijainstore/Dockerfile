# Etapa 1: build de Angular
FROM node:19-alpine AS builder
WORKDIR /app

# Copia dependencias y las instala
COPY package.json package-lock.json* ./
RUN npm install

# Copia el resto del código
COPY . .

# Ejecuta el build en producción
RUN npx ng build --configuration production

# Etapa 2: imagen final con Nginx
FROM nginx:alpine
EXPOSE 80

# Copia los archivos compilados al contenedor nginx
COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

# Configura nginx
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

# Inicia Nginx
CMD ["nginx", "-g", "daemon off;"]
