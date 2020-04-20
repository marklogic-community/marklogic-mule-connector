/**
 * MarkLogic Mule Connector
 *
 * Copyright © 2020 MarkLogic Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 * This project and its code and functionality is not representative of MarkLogic Server and is not supported by MarkLogic.
 */
package com.marklogic.mule.extension.connector.internal.result.resultset;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.marklogic.client.document.DocumentRecord;
import com.marklogic.client.io.BytesHandle;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.Format;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.mule.extension.connector.api.MarkLogicAttributes;
import com.marklogic.mule.extension.connector.internal.operation.MarkLogicOperations;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.mule.runtime.api.metadata.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jkrebs on 1/19/2020.
 */
public class MarkLogicJSONRecordExtractor extends MarkLogicRecordExtractor {
    
    private static final Logger logger = LoggerFactory.getLogger(MarkLogicJSONRecordExtractor.class);

    // Objects used for handling JSON documents
    private JacksonHandle jacksonHandle = new JacksonHandle();
    private ObjectMapper jsonMapper = new ObjectMapper();
    private StringHandle handle = new StringHandle();

    @Override
    protected Result<Object,MarkLogicAttributes> extractRecord(DocumentRecord record) {
        StringHandle retVal = record.getContent(handle).withFormat(Format.JSON);
        String jsonstr = retVal.get();
        final byte[] jsonbytes = jsonstr.getBytes();
        MarkLogicAttributes attributes = new MarkLogicAttributes(retVal.getMimetype());
        return Result.<Object,MarkLogicAttributes>builder()
                .output(jsonstr)
                .attributes(attributes)
                .attributesMediaType(MediaType.APPLICATION_JAVA)
                .mediaType(MediaType.APPLICATION_JSON)
                .length(jsonbytes.length)
                .build();
    }
}
