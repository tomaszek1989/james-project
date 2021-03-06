/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.blob.objectstorage;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public final class ContainerName {
    public static ContainerName of(String value) {
        return new ContainerName(value);
    }

    private final String container;

    private ContainerName(String value) {
        this.container = value;
    }

    public String value() {
        return container;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainerName that = (ContainerName) o;
        return Objects.equal(container, that.container);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(container);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("container", container)
            .toString();
    }
}
