FROM node:20-alpine AS builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

ARG FRONTEND_BACKEND_URL

RUN sed -i "s|__BACKEND_URL__|${FRONTEND_BACKEND_URL}|g" src/environments/environment.prod.ts

RUN npm run build -- --configuration=production


FROM nginx:stable-alpine

COPY --from=builder /app/dist/saijainstore/browser /usr/share/nginx/html

EXPOSE 80