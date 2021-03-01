# multi-container-app
This is sample spring boot app that exposes REST API to manage hotels.
Hotels informations are stored in Postgres.
API calls rate limit is cached in Redis.
Each new hotel entry is sent to kafka topic.