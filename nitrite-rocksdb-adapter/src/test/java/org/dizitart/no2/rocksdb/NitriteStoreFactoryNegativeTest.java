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

package org.dizitart.no2.rocksdb;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.collection.NitriteCollection;
import org.dizitart.no2.exceptions.NitriteException;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.dizitart.no2.collection.Document.createDocument;
import static org.dizitart.no2.rocksdb.DbTestOperations.getRandomTempDbFile;
import static org.junit.Assert.assertEquals;

/**
 * @author Anindya Chatterjee.
 */
public class NitriteStoreFactoryNegativeTest {
    private final String fileName = getRandomTempDbFile();

    @Rule
    public Retry retry = new Retry(3);

    @Test(expected = NitriteException.class)
    public void testOpenSecuredWithoutCredential() throws IOException {
        try(Nitrite db = TestUtil.createDb(fileName, "test-user", "test-password")) {
            NitriteCollection dbCollection = db.getCollection("test");
            dbCollection.insert(createDocument("test", "test"));
            db.commit();
            db.close();


            try(Nitrite db2 = TestUtil.createDb(fileName)){
                dbCollection = db2.getCollection("test");
                assertEquals(dbCollection.find().size(), 1);
            }
        } finally {
            TestUtil.deleteFile(fileName);
        }
    }

    @Test(expected = NitriteException.class)
    public void testOpenUnsecuredWithCredential() throws IOException {
        try(Nitrite db = TestUtil.createDb(fileName)) {
            NitriteCollection dbCollection = db.getCollection("test");
            dbCollection.insert(createDocument("test", "test"));
            db.commit();
            db.close();

            try(Nitrite db2 = TestUtil.createDb(fileName, "test-user", "test-password")) {
                dbCollection = db2.getCollection("test");
                assertEquals(dbCollection.find().size(), 1);
            }
        } finally {
            TestUtil.deleteFile(fileName);
        }
    }

    @Test(expected = NitriteException.class)
    public void testWrongCredential() throws IOException {
        try(Nitrite db = TestUtil.createDb(fileName, "test-user", "test-password")) {
            NitriteCollection dbCollection = db.getCollection("test");
            dbCollection.insert(createDocument("test", "test"));
            db.commit();
            db.close();

            try(Nitrite db2 = TestUtil.createDb(fileName, "test-user", "test-password2")) {
                dbCollection = db2.getCollection("test");
                assertEquals(dbCollection.find().size(), 1);
            }
        } finally {
            TestUtil.deleteFile(fileName);
        }
    }
}
