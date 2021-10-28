#!/usr/bin/env bash

#fail script iy any command fails
set -e 

# build backend
cd backend
./mvnw clean package -DskipTests
# build frontend
cd ../frontend
npm install --no-save # --no-save avoids modifiying package-lock.json as a side-effect
npm run build
# For production:
#npm run build --configuration=production

# start containers via docker-compose
cd ../
docker-compose up --build