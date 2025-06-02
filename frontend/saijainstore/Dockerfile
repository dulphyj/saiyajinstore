# Arguments to avoid too many pulls from Docker Hub
ARG NODE_URI
ARG NGINX_URI

# Stage 1: Build Angular App
FROM ${NODE_URI:-node}:lts-alpine3.15 AS builder
WORKDIR /app

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Copy package.json and install dependencies
COPY package.json /app
RUN npm install --legacy-peer-deps

# Copy the entire project and build the Angular app
COPY . /app
RUN npm run build --prod

# Stage 2: Serve Angular App with Nginx
FROM ${NGINX_URI:-nginx}:stable-alpine AS deployer
COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

# Optionally configure Nginx
COPY nginx/nginx.conf /etc/nginx/nginx.conf


EXPOSE 80

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
