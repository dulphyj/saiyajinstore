# Etapa 1: dependencias
FROM node:19-alpine AS dev
WORKDIR /app
COPY package.json package-lock.json* ./

# Herramientas básicas para compilar paquetes nativos
RUN apk add --no-cache python3 make g++ \
    && npm install \
    && apk del python3 make g++

# Etapa 2: build de Angular
FROM node:19-alpine AS builder
WORKDIR /app

# Copiar node_modules de la etapa anterior
COPY --from=dev /app/node_modules ./node_modules

# Copiar archivos necesarios para el build
COPY --from=dev /app/package.json ./package.json
COPY --from=dev /app/package-lock.json ./package-lock.json
COPY --from=dev /app/angular.json ./angular.json 
COPY --from=dev /app/tsconfig.json ./tsconfig.json
COPY . .

# Angular CLI en el PATH local
ENV PATH="/app/node_modules/.bin:$PATH"

RUN npm run build

# Etapa 3: imagen final con Nginx
FROM nginx:alpine
EXPOSE 80

COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf  # Elimina la configuración por defecto de Nginx
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]  # Inicia Nginx
