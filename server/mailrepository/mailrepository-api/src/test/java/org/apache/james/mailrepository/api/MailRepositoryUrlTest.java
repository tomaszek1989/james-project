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

package org.apache.james.mailrepository.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class MailRepositoryUrlTest {
    @Test
    void shouldMatchBeanContract() {
        EqualsVerifier.forClass(MailRepositoryUrl.class)
            .withIgnoredFields("protocol", "path")
            .verify();
    }

    @Test
    void constructorShouldThrowWhenNull() {
        assertThatThrownBy(() -> MailRepositoryUrl.from(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void constructorShouldThrowWhenNoSeparator() {
        assertThatThrownBy(() -> MailRepositoryUrl.from("invalid"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getProtocolShouldReturnValue() {
        assertThat(MailRepositoryUrl.from("proto://abc").getProtocol())
            .isEqualTo(new Protocol("proto"));
    }

    @Test
    void getProtocolShouldReturnValueWhenEmpty() {
        assertThat(MailRepositoryUrl.from("://abc").getProtocol())
            .isEqualTo(new Protocol(""));
    }

    @Test
    void fromEncodedShouldReturnDecodedValue() throws Exception {
        assertThat(MailRepositoryUrl.fromEncoded("url%3A%2F%2FmyRepo"))
            .isEqualTo(MailRepositoryUrl.from("url://myRepo"));
    }

    @Test
    void fromPathAndProtocolShouldReturnTheFullValue() {
        assertThat(MailRepositoryUrl.fromPathAndProtocol(MailRepositoryPath.from("myRepo"), "url"))
            .isEqualTo(MailRepositoryUrl.from("url://myRepo"));
    }

    @Test
    void encodedValueShouldEncodeUnderlyingValue() throws Exception {
        assertThat(MailRepositoryUrl.from("url://myRepo").urlEncoded())
            .isEqualTo("url%3A%2F%2FmyRepo");
    }

    @Test
    void getPathShouldReturnValue() {
        assertThat(MailRepositoryUrl.from("proto://abc").getPath())
            .isEqualTo(MailRepositoryPath.from("abc"));
    }

    @Test
    void getPathShouldReturnValueWhenEmtpyPath() {
        assertThat(MailRepositoryUrl.from("proto://").getPath())
            .isEqualTo(MailRepositoryPath.from(""));
    }

    @Test
    void getPathShouldReturnValueWhenSeveralProtocolSeparators() {
        assertThat(MailRepositoryUrl.from("proto://abc://def").getPath())
            .isEqualTo(MailRepositoryPath.from("abc://def"));
    }
}
