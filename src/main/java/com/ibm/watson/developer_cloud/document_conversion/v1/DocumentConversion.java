/**
 * Copyright 2015 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibm.watson.developer_cloud.document_conversion.v1;

import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.document_conversion.v1.helpers.*;
import com.ibm.watson.developer_cloud.document_conversion.v1.model.*;
import com.ibm.watson.developer_cloud.service.WatsonService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * The IBM Watson Document Conversion service converts provided source
 * documents (HTML, Word, PDF) into JSON Answer Units, Normalized HTML,
 * or Normalized Text.
 *
 * @version v1
 * @see <a
 *      href="http://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/document-conversion.html">
 *      Document Conversion</a>
 */
public class DocumentConversion extends WatsonService {

    /**
     * The Constant TOKEN. (value is "token")
     */
    public static final String TOKEN = "token";

    /**
     * The Constant LIMIT. (value is "limit")
     */
    public static final String LIMIT = "limit";

    /**
     * The Constant NAME. (value is "name")
     */
    public static final String NAME = "name";

    /**
     * The Constant SINCE. (value is "since")
     */
    public static final String SINCE = "since";

    /**
     * The Constant MEDIA_TYPE. (value is "media_type")
     */
    public static final String MEDIA_TYPE = "media_type";

    /**
     * The Constant JOB_ID. (value is "job_id")
     */
    public static final String JOB_ID = "job_id";

    /**
     * The Constant STATUS. (value is "status")
     */
    public static final String STATUS = "status";

    /**
     * The DOCUMENTS_PATH.  (value is "/v1/documents")
     **/
    public final static String DOCUMENTS_PATH = "/v1/documents";

    /**
     * The BATCHES_PATH.  (value is "/v1/batches")
     **/
    public final static String BATCHES_PATH = "/v1/batches";

    /**
     * The JOBS_PATH.  (value is "/v1/jobs")
     **/
    public final static String JOBS_PATH = "/v1/jobs";

    /**
     * The OUTPUT_PATH.  (value is "/v1/output")
     **/
    public final static String OUTPUT_PATH = "/v1/output";

    /**
     * The CONVERT_DOCUMENT_PATH.  (value is "/v1/convert_document")
     **/
    public final static String CONVERT_DOCUMENT_PATH = "/v1/convert_document";

    /**
     * The default limit for get requests
     **/
    public static final int DEFAULT_LIMIT = 100;

    /** The default URL for the service */
    private static final String URL = "https://gateway.watsonplatform.net/document-conversion-experimental/api";

    // Helper classes used by the service to delegate API calls to
    private final BatchHelper batchHelper;
    private final DocumentHelper documentHelper;
    private final BatchDocumentHelper batchDocumentHelper;
    private final JobHelper jobHelper;
    private final OutputHelper outputHelper;
    private final ConvertDocumentHelper convertDocumentHelper;


    /**
     * Sets the endpoint url for the service
     */
    public DocumentConversion() {
        setEndPoint(URL);

        // Initialize the helpers
        this.batchHelper = new BatchHelper(this);
        this.documentHelper = new DocumentHelper(this);
        this.batchDocumentHelper = new BatchDocumentHelper(this);
        this.jobHelper = new JobHelper(this);
        this.outputHelper = new OutputHelper(this);
        this.convertDocumentHelper = new ConvertDocumentHelper(this);
    }

    /**
     * Gets a collection of all existing batches in the service
     * GET /v1/batches
     * @return All batches
     */
    public BatchCollection getBatchCollection() {
        return getBatchCollection(null);
    }

    /**
     * Gets a collection of all existing batches with optional query parameters for filtering results.
     * GET /v1/batches
     *
     * @param batchListParams The parameters to be used in the batch list service call.
     *                        The parameters - token, limit, name and since are optional
     * <ul>
     * <li> String token - The reference to the starting element of the requested page which is provided
     *                     by the server, pass null to get the first page </li>
     * <li> int limit - The number of batches to get, pass 0 to use the default limit from server (100) </li>
     * <li> String name - The name of batches to get, pass null to exclude this filter </li>
     * <li> Date since - The date to filter on, batches created on or after the provided date and time format
     *                   will be returned, pass null to exclude this filter </li>
     * </ul>
     * @return Batches based on filtering parameters provided
     */
    public BatchCollection getBatchCollection(Map<String, Object> batchListParams) {
        return batchHelper.getBatchCollection(batchListParams);
    }

    /**
     * Creates a new batch with name and properties
     *
     * POST /v1/batches
     * @param name the name of the created batch
     * @param properties the properties for the created batch
     * @return requested Batch
     */
    public Batch createBatch(final String name, final List<Property> properties) {
        return batchHelper.createBatch(name, properties);
    }

    /**
     * Gets an existing batch
     *
     * GET /v1/batches/{batch_id}
     * @param batchId id for the batch to be updated
     * @return requested Batch
     */
    public Batch getBatch(final String batchId) {
        return batchHelper.getBatch(batchId);
    }

    /**
     * Updates an existing batch with the provided name and properties
     *
     * PUT /v1/batches/{batch_id}
     * @param batchId id for the batch to be updated
     * @param name name of the batch to be updated
     * @param properties properties of the batch to be updated
     * @return updated Batch
     *
     */
    public Batch updateBatch(final String batchId, final String name,
                             final List<Property> properties) {
        return batchHelper.updateBatch(batchId, name, properties);
    }

    /**
     * Gets a collection of uploaded documents
     * GET /v1/documents
     * @return All documents
     */
    public DocumentCollection getDocumentCollection() {
        return getDocumentCollection(null);
    }

    /**
     * Gets a collection of all existing documents with optional query parameters for filtering results.
     * GET /v1/documents
     *
     * @param docListParams The parameters to be used in the documents list service call.
     *                      The parameters - token, limit, name, since and media_type are optional
     * <ul>
     * <li> String token - The reference to the starting element of the requested page which is provided
     *                     by the server, pass null to get the first page </li>
     * <li> int limit - The number of documents to get, pass 0 to use the default limit from server (100) </li>
     * <li> String name - The name of the documents to get, pass null to exclude this filter </li>
     * <li> Date since - The date to filter on, documents created on or after the provided date and time format
     *                  will be returned, pass null to exclude this filter </li>
     * <li> String media_type - The Internet media type to filter on, pass null to exclude this filter </li>
     * @return Documents based on filtering parameters provided
     **/
    public DocumentCollection getDocumentCollection(Map<String, Object> docListParams) {
        return documentHelper.getDocumentCollection(docListParams);
    }

    /**
     * Uploads the document to the service with the given media type
     *
     * POST /v1/documents
     * @param document the document to be uploaded
     * @return Document
     */
    public Document uploadDocument(final File document) {
        return documentHelper.uploadDocument(document);
    }

    /**
     * Uploads the document to the service with the given media type
     *
     * POST /v1/documents
     * @param document the document to be uploaded
     * @return Document
     */
    public Document uploadDocument(final File document, final String mediaType) {
        return documentHelper.uploadDocument(document, mediaType);
    }


    /**
     * Retrieves a document from the service with the given id
     *
     * GET /v1/documents/{document_id}
     * @param documentId id of the document to be retrieved
     * @return requested Document
     */
    public InputStream getDocument(final String documentId) {
        return documentHelper.getDocument(documentId);
    }

    /**
     * Gets a collection of existing documents in the batch
     *
     * GET /v1/batches/{batch_id}/documents
     * @param batchId The id for the batch whose documents are returned
     * @return All documents in a batch
     */
    public BatchDocumentCollection getBatchDocumentCollection(final String batchId) {
        return getBatchDocumentCollection(batchId, null);
    }

    /**
     * Gets a list of existing documents in the batch with optional query parameters for filtering results.
     *
     * GET /v1/batches/{batch_id}/documents
     *
     * @param batchId The id for the batch whose documents are returned
     * @param batchDocListParams The parameters to be used in the batch documents list service call.
     *                         The parameters - token, limit, since, job_id and media_type are optional
     * <ul>
     * <li> String token - The reference to the starting element of the requested page which is provided
     *                     by the server, pass null to get the first page </li>
     * <li> int limit - The number of documents in a batch to get, pass 0 to use the default limit from server (100) </li>
     * <li> Date since - The date to filter on, documents added to the batch on or after the provided date and time format
     *              will be returned. </li>
     * </ul>
     * @return Documents in a batch based on the filtering parameters provided
     */
    public BatchDocumentCollection getBatchDocumentCollection(String batchId, Map<String, Object> batchDocListParams) {
        return batchDocumentHelper.getBatchDocumentCollection(batchId, batchDocListParams);
    }


    /**
     * Retrieves the documents from the batch whose ids are specified
     *
     * GET /v1/batches/{batch_id}/documents/{document_id}
     * @param batchId
     *          id of the batch to be retrieved
     * @param documentId
     *          id of the document to be retrieved
     * @return document from the batch
     */
    public BatchDocumentResponse getBatchDocument(final String batchId, final String documentId) {
        return batchDocumentHelper.getBatchDocument(batchId, documentId);
    }

    /**
     * Adds a document to the batch whose ids are specified
     *
     * PUT /v1/batches/{batch_id}/documents/{document_id}
     * @param batchId
     *          id of the batch to be retrieved
     * @param documentId
     *          id of the document to be retrieved
     * @return document from the batch
     */
    public BatchDocumentResponse addDocumentToBatch(final String batchId, final String documentId) {
        return batchDocumentHelper.addDocumentToBatch(batchId, documentId);
    }

    /**
     * Synchronously converts a new document without persistence into an Answers object
     * POST /v1/convert_document
     *
     * @param document The file to convert
     * @return Converted document as an Answer
     */
    public Answers convertDocumentToAnswer(final File document) {
        return convertDocumentHelper.convertDocumentToAnswer(document);
    }

    /**
     * Synchronously converts a new document without persistence
     * POST /v1/convert_document
     *
     * @param document The file to convert
     * @param conversionTarget The conversion target to use
     * @return Converted document in the specified format
     */
    public InputStream convertDocument(final File document, final ConversionTarget conversionTarget) {
        return convertDocumentHelper.convertDocument(document, conversionTarget);
    }

    /**
     * Synchronously converts a new document without persistence
     * POST /v1/convert_document
     *
     * @param document The file to convert
     * @param mediaType Internet media type of the file
     * @param conversionTarget The conversion target to use
     * @return Converted document in the specified format
     */
    public InputStream convertDocument(final File document, final String mediaType, final ConversionTarget conversionTarget) {
        return convertDocumentHelper.convertDocument(document, mediaType, conversionTarget);
    }

    /**
     * Synchronously converts a single previously uploaded document into an Answers object
     * POST /v1/convert_document
     *
     * @param documentId The id of the document to convert
     * @return Converted document as an Answer
     */
    public Answers convertDocumentToAnswer(final String documentId) {
        return convertDocumentHelper.convertDocumentToAnswer(documentId);
    }

    /**
     * Synchronously converts a single previously uploaded document
     * POST /v1/convert_document
     *
     * @param documentId The id of the document to convert
     * @param conversionTarget The conversion target to use
     * @return Converted document in the specified format
     */
    public InputStream convertDocument(final String documentId, final ConversionTarget conversionTarget) {
        return convertDocumentHelper.convertDocument(documentId, conversionTarget);
    }

    /**
     * Gets a collection of all jobs in the service
     * GET /v1/jobs
     * @return All jobs
     */
    public JobCollection getJobCollection() {
        return getJobCollection(null);
    }


    /**
     * Gets a list of all jobs with optional query parameters for filtering results.
     * GET /v1/jobs
     *
     * @param jobListParams The parameters to be used in the job list service call.
     *                      The parameters - token, limit, since, name, since and status are optional
     * <ul>
     * <li> String token - The reference to the starting element of the requested page which is provided
     *              by the server, pass null to get the first page </li>
     * <li> int limit - The number of jobs to get, pass 0 to use the default limit from server (100) </li>
     * <li> String name - The name of the jobs to get, pass null to exclude this filter </li>
     * <li> Date since - The date to filter on, jobs created on or after the provided date and time format will
     *              be returned, pass null to exclude this filter </li>
     * <li> String status - The status of the job to filter on, pass null to exclude this filter </li>
     * </ul>
     *
     * @return Jobs based on filtering parameters provided
     */
    public JobCollection getJobCollection(Map<String, Object> jobListParams) {
        return jobHelper.getJobCollection(jobListParams);
    }

    /**
     * Creates a new job by submitting a batch for processing
     * POST /v1/jobs
     * @param name The name of the job
     * @param batchId The id of the batch to process
     * @param conversionTarget The conversion target to use
     * @return JobResponse
     */
    public JobResponse createJob(final String name, final String batchId, final ConversionTarget conversionTarget) {
        return createJob(name, batchId, conversionTarget, null);
    }

    /**
     * Creates a new job by submitting a batch for processing
     * POST /v1/jobs
     * @param name The name of the job
     * @param batchId The id of the batch to process
     * @param conversionTarget The conversion target to use
     * @param config The configuration to use for the job (optional), pass null to use default config
     * @return JobResponse
     */
    public JobResponse createJob(final String name, final String batchId,
                         final ConversionTarget conversionTarget, final JsonObject config) {
        return jobHelper.createJob(name, batchId, conversionTarget, config);
    }

    /**
     * Gets information about a job
     * GET /v1/jobs/{job_id}
     * @param jobId The id of the job
     * @return Job
     */
    public Job getJob(final String jobId) {
        return jobHelper.getJob(jobId);
    }

    /**
     * Gets the job processing log
     * GET /v1/jobs/{job_id}/log
     * @param jobId The id of the job
     * @return Job's processing log
     */
    public InputStream getJobLog(final String jobId) {
        return jobHelper.getJobLog(jobId);
    }

    /**
     * Gets a collection of all generated outputs
     * GET /v1/output
     * @return All Outputs
     */
    public OutputCollection getOutputCollection() {
        return getOutputCollection(null);
    }

    /**
     * Gets a collection of all generated outputs with optional query parameters for filtering results.
     * GET /v1/output
     *
     * @param outputListParams The parameters to be used in the output list service call.
     *                         The parameters - token, limit, since, job_id and media_type are optional
     * <ul>
     * <li> String token - The reference to the starting element of the requested page which is provided
     *              by the server, pass null to get the first page </li>
     * <li> int limit - The number of outputs to get, pass 0 to use the default limit from server (100) </li>
     * <li> Date since - The date to filter on, outputs created on or after the provided date and time format
     *              will be returned. </li>
     * <li> String job_id - The id of a job to filter outputs by, pass null to exclude this filter </li>
     * <li> String media_type - The Internet media type to filter on, pass null to exclude this filter </li>
     * </ul>
     * @return Outputs based on filtering parameters provided
     */
    public OutputCollection getOutputCollection(Map<String, Object> outputListParams) {
        return outputHelper.getOutputCollection(outputListParams);
    }

    /**
     * Gets the content of the output of the document conversion process. This can represent a single converted document
     * or the combined output of multiple input documents.
     * @param outputId The id of the output to get
     * @return The requested Output
     */
    public InputStream getOutput(final String outputId) {
        return outputHelper.getOutput(outputId);
    }

    @Override
    public HttpResponse execute(final HttpRequestBase request) {
        return super.execute(request);
    }

}
