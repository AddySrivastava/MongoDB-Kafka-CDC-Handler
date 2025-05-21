# MongoDB BOB CDC Processor

## Overview
The MongoDB BOB CDC Processor integrates CouchDB with MongoDB using Kafka Connect. It uses the [IBM Cloudant Kafka Connector](https://github.com/IBM/cloudant-kafka-connector) as a source to capture change events from CouchDB, and the [MongoDB Kafka Connector](https://github.com/mongodb/mongo-kafka) as a sink to synchronize those changes to MongoDB. This setup enables efficient, real-time data synchronization between CouchDB and MongoDB.

## Features
- Real-time replication of data from CouchDB to MongoDB.
- Supports CDC operations: insert, update, replace, and delete.
- Extensible CDC handler for additional transformations.
- Enables compatibility with the [Confluent JDBC Source Connector](https://docs.confluent.io/kafka-connect-jdbc/current/index.html) via a custom CDC handler.

## Compatibility with Confluent JDBC Source Connector
The `MongoDBBOBCdcHandler` transforms CouchDB change events into a format compatible with the Confluent JDBC Source Connector. This ensures seamless interoperability where data changes captured from relational databases using the JDBC Source Connector can be applied to MongoDB through the MongoDB Sink Connector. The handler supports upserts, deletes, and other common operations in a normalized format.

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/AddySrivastava/CouchDBCdcProcessor.git
    ```

2. Install and configure the Kafka connectors:
   - Source Connector: IBM Cloudant Kafka Connector.
   - Sink Connector: MongoDB Kafka Connector.

3. Build the project using Maven:
    ```bash
    mvn clean package
    ```

4. Configure your Kafka Connect environment to use the source and sink connectors along with this CDC processor.

## Usage
The `MongoDBBOBCdcHandler` class extends the generic `CdcHandler` to process CDC events from CouchDB. It maps the following operations to MongoDB:

- **Insert**: Synchronizes new documents.
- **Update**: Applies partial updates.
- **Replace**: Fully replaces documents.
- **Delete**: Removes documents from MongoDB.

The handler uses `MongoSinkTopicConfig` for configuration and logs activity through SLF4J.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
