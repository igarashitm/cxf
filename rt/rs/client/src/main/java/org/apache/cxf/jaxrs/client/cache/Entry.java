/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.jaxrs.client.cache;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

public class Entry implements Serializable {
    private static final long serialVersionUID = -3551501551331222546L;
    private Map<String, String> cacheHeaders = Collections.emptyMap();
    private byte[] data;
    private MultivaluedMap<String, String> headers;
    private long expiresValue;
    private long initialTimestamp = now();

    public Entry(final byte[] bytes, final MultivaluedMap<String, String> headers,
                 final Map<String, String> cacheHeaders, final long expiresHeaderValue) {
        this.data = bytes;
        this.headers = headers;
        this.cacheHeaders = cacheHeaders;
        this.expiresValue = expiresHeaderValue;
    }

    public Entry() {
        // no-op
    }

    public boolean isOutDated() {
        return now() - initialTimestamp > expiresValue * 1000;
    }

    public Map<String, String> getCacheHeaders() {
        return cacheHeaders;
    }

    public void setCacheHeaders(final Map<String, String> cacheHeaders) {
        this.cacheHeaders = cacheHeaders;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    public MultivaluedMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final MultivaluedMap<String, String> headers) {
        this.headers = headers;
    }

    public long getExpiresValue() {
        return expiresValue;
    }

    public void setExpiresValue(final long expiresValue) {
        this.expiresValue = expiresValue;
    }

    public long getInitialTimestamp() {
        return initialTimestamp;
    }

    public void setInitialTimestamp(final long initialTimestamp) {
        this.initialTimestamp = initialTimestamp;
    }

    private static long now() {
        return System.currentTimeMillis();
    }
}