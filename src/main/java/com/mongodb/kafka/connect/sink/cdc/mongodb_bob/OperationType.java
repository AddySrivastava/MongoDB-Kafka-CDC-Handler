/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Original Work: Apache License, Version 2.0, Copyright 2017 Hans-Peter Grahsl.
 */

package com.mongodb.kafka.connect.sink.cdc.mongodb_bob;

// https://docs.mongodb.com/manual/reference/change-events/
public enum OperationType {
    CREATE_COLLECTION("create"),
    CREATE_INDEXES("createIndexes"),
    INSERT("insert"),
    REPLACE("replace"),
    REFINE_COLLECTION_SHARD_KEY("refineCollectionShardKey"),
    RESHARD_COLLECTION("reshardCollection"),
    SHARD_COLLECTION("shardCollection"),
    UPDATE("update"),
    MODIFY_COLLECTION("modify"),
    DELETE("delete"),
    DROP_COLLECTION("drop"),
    DROP_DATABASE("dropDatabase"),
    DROP_INDEXES("dropIndexes"),
    RENAME_COLLECTION("rename"),
    INVALIDATE("invalidate"),
    UNKNOWN("unknown");

    private final String value;

    OperationType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OperationType fromString(final String value) {
        for (OperationType operationType : OperationType.values()) {
            if (value.equalsIgnoreCase(operationType.value)) {
                return operationType;
            }
        }
        return UNKNOWN;
    }
}