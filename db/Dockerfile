# Use the latest MySQL image
FROM mysql/mysql-server:latest

# Set the working directory
WORKDIR /tmp

# Copy all SQL files from world-db to the working directory
COPY world-db/*.sql /tmp/

# Copy the main SQL file to docker-entrypoint-initdb.d.
# Scripts and SQL files in this folder are executed on container startup.
# This is specific to MySQL.
COPY world-db/world.sql /docker-entrypoint-initdb.d/

# Set the root password and allow root connections from any host
ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_ROOT_HOST=%