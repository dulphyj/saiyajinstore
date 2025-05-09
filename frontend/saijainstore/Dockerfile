# Etapa 1: build de Angular
FROM node:19-alpine AS dev
WORKDIR /app
COPY package.json package.json
RUN npm install

# Etapa 2: build de Angular
FROM node:19-alpine AS builder
WORKDIR /app
COPY --from=dev /app/node_modules ./node_modules
COPY . .
RUN npm run build -- --configuration production

# Etapa 3: imagen final con Nginx
FROM nginx:alpine
EXPOSE 80
# Copia archivos compilados al directorio que sirve Nginx
COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html
RUN rm etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

# Inicia Nginx en modo foreground
CMD ["nginx", "-g", "daemon off;"]
