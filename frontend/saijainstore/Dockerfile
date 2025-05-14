# Etapa 1: dependencias
FROM node:19-alpine AS dev
WORKDIR /app
COPY package.json package-lock.json* ./

#instalación de devDependencies (por defecto NODE_ENV=production en muchas plataformas)
ENV NODE_ENV=development

# Herramientas básicas para compilar paquetes nativos
RUN apk add --no-cache python3 make g++ \
    && npm install \
    && apk del python3 make g++

# Etapa 2: build de Angular
FROM node:19-alpine AS builder
WORKDIR /app
COPY --from=dev /app/node_modules ./node_modules
COPY . .

# Angular CLI en el PATH local
ENV PATH="/app/node_modules/.bin:$PATH"

RUN npm run build -- --configuration production

# Etapa 3: imagen final con Nginx
FROM nginx:alpine
EXPOSE 80

# ⚠️ Si no usas Angular Universal, este es el path correcto
COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]

