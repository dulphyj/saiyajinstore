# Arguments to avoid too many pulls from Docker Hub
ARG NODE_URI
ARG NGINX_URI

# Stage 1: Build Angular App
FROM ${NODE_URI:-node}:lts AS builder
WORKDIR /app

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Copy package.json and install dependencies
# Copiar archivos necesarios primero
COPY package*.json ./
COPY angular.json ./
COPY tsconfig*.json ./
COPY src/ ./src
COPY nginx/ ./nginx 
RUN npm install --legacy-peer-deps

# Copy the entire project and build the Angular app
COPY . .
RUN npm run build --configuration production

# Stage 2: Serve Angular App with Nginx
FROM ${NGINX_URI:-nginx}:stable-alpine AS deployer
COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

# Optionally configure Nginx
COPY nginx/nginx.conf /etc/nginx/nginx.conf


EXPOSE 80

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
