# Confluent JDBC CDC Processor

## Overview
The MongoDB BOB CDC Processor integrates Confluent JDBC connector with MongoDB Kafka Connector. It uses the [JDBC Connector](https://www.confluent.io/hub/confluentinc/kafka-connect-jdbc) as a source to capture change events from JDBC source connector, and the [MongoDB Kafka Connector](https://github.com/mongodb/mongo-kafka) as a sink to synchronize those changes to MongoDB. This setup enables efficient, real-time data synchronization between Oracle and MongoDB.

## Features
- Real-time replication of data from Oracle to MongoDB.
- Supports CDC operations: insert, update, replace, and delete.
- Extensible CDC handler for additional transformations.
- Enables compatibility with the [Confluent JDBC Source Connector](https://docs.confluent.io/kafka-connect-jdbc/current/index.html) via a custom CDC handler.

## Compatibility with Confluent JDBC Source Connector
The `MongoDBBOBCdcHandler` transforms Confluent JDBC events into a format compatible with the MongoDB Sink Connector. This ensures seamless interoperability where data changes captured from relational databases using the JDBC Source Connector can be applied to MongoDB through the MongoDB Sink Connector. The handler supports upserts, deletes, and other common operations in a normalized format.

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/AddySrivastava/MongoDB-Kafka-CDC-Handler.git
    ```

2. Install and configure the Kafka connectors:
   - Source Connector: Confluent JDBC Kafka Connector.
   - Sink Connector: MongoDB Kafka Connector.

3. Build the project using Maven:
    ```bash
    mvn clean package
    ```

4. Configure your Kafka Connect environment to use the source and sink connectors along with this CDC processor.

## Usage
The `MongoDBBOBCdcHandler` class extends the generic `CdcHandler` to process CDC events from JDBC connector. It maps the following operations to MongoDB:

- **Insert**: Synchronizes new documents.
- **Update**: Applies partial updates.
- **Replace**: Fully replaces documents.
- **Delete**: Removes documents from MongoDB.

## Oracle Table Format

```bash
CREATE TABLE mongo_change_events (
change_id       VARCHAR2(256),     -- From "_id._data"
operation_type  VARCHAR2(20),      -- "insert", "update", "delete", etc.

cluster_time    CLOB CHECK (cluster_time IS JSON),   -- Timestamp info
full_document   CLOB CHECK (full_document IS JSON),  -- Original document
namespace       CLOB CHECK (namespace IS JSON),      -- { db, coll }
document_key    CLOB CHECK (document_key IS JSON),   -- Usually {_id}
raw_event       CLOB CHECK (raw_event IS JSON)       -- Full raw change stream event
);
```


## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
