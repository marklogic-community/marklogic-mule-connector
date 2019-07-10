/**
 * MarkLogic Mule Connector
 *
 * Copyright © 2019 MarkLogic Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 * This project and its code and functionality is not representative of MarkLogic Server and is not supported by MarkLogic.
 */
package com.marklogic.mule.extension.connector.api.operation;

/**
 * Created by jkrebs on 7/10/2019.
 * This enum has the various strategies for dealing with Job Report document that already exists when the flow starts.
 * The document is technically overwritten in any case, the question is, are the existing statistics carried forward,
 * or discarded.
 */
public enum MarkLogicExistingJobReportDocumentStrategy
{
    OVERWRITE,  // Delete the existing report document, and create a new one
    APPEND      // Add the statistics from this job to those already in the existing report document
}
