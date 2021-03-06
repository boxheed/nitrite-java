/*
 * Copyright (c) 2017-2020. Nitrite author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dizitart.no2.index;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.dizitart.no2.collection.NitriteCollection;

/**
 * Represents options to apply while creating an index.
 *
 * @author Anindya Chatterjee
 * @see NitriteCollection#createIndex(String, IndexOptions)
 * @since 1.0
 */
@EqualsAndHashCode
public class IndexOptions {

    /**
     * Specifies the type of an index to create.
     *
     * @param indexType type of an index.
     * @return type of an index to create.
     */
    @Getter
    @Setter
    private String indexType;

    /**
     * Indicates whether an index to be created in a non-blocking
     * way.
     *
     * @param async if set to `true` then the index will be created asynchronously;
     * otherwise create index operation will wait until all existing
     * documents are indexed.
     * @return `true` if index is to be created asynchronously; otherwise `false`.
     * @see NitriteCollection#createIndex(String, IndexOptions)
     */
    @Getter
    @Setter
    private boolean async = false;

    /**
     * Creates an {@link IndexOptions} with the specified `indexType`. Index creation
     * will be synchronous with this option.
     *
     * @param indexType the type of index to be created.
     * @return a new synchronous index creation option.
     */
    public static IndexOptions indexOptions(String indexType) {
        return indexOptions(indexType, false);
    }

    /**
     * Creates an {@link IndexOptions} with the specified `indexType` and `async` flag.
     *
     * @param indexType the type of index to be created.
     * @param async     if set to `true` then the index would be created asynchronously;
     *                  otherwise synchronously.
     * @return a new index creation option.
     */
    public static IndexOptions indexOptions(String indexType, boolean async) {
        IndexOptions options = new IndexOptions();
        options.setIndexType(indexType);
        options.setAsync(async);
        return options;
    }
}

