package com.dell.sdp.pravega.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "event",
        "eventCode",
        "eventSubcode",
        "step",
        "summary",
        "detail",
        "business_identifier",
        "alternate_business_identifier",
        "global_transaction_id",
        "transactionMode",
        "domain",
        "environment",
        "service_name",
        "date_time",
        "guid",
        "serviceVersion",
        "app_name",
        "appUser",
        "host_name",
        "thread_name",
        "payload",
        "receiver",
        "isPayloadTempered",
        "nvp"
})
public class JSONData {

    @JsonProperty("status")
    private String status;
    @JsonProperty("event")
    private String event;
    @JsonProperty("eventCode")
    private Object eventCode;
    @JsonProperty("eventSubcode")
    private Object eventSubcode;
    @JsonProperty("step")
    private String step;
    @JsonProperty("summary")
    private Object summary;
    @JsonProperty("detail")
    private Object detail;
    @JsonProperty("business_identifier")
    private String businessIdentifier;
    @JsonProperty("alternate_business_identifier")
    private Object alternateBusinessIdentifier;
    @JsonProperty("global_transaction_id")
    private String globalTransactionId;
    @JsonProperty("transactionMode")
    private Object transactionMode;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("environment")
    private String environment;
    @JsonProperty("service_name")
    private Object serviceName;
    @JsonProperty("date_time")
    private String dateTime;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("serviceVersion")
    private Object serviceVersion;
    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("appUser")
    private String appUser;
    @JsonProperty("host_name")
    private String hostName;
    @JsonProperty("thread_name")
    private String threadName;
    @JsonProperty("payload")
    private Object payload;
    @JsonProperty("receiver")
    private Object receiver;
    @JsonProperty("isPayloadTempered")
    private Object isPayloadTempered;
    @JsonProperty("nvp")
    private String nvp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public JSONData() {
    }

    /**
     *
     * @param dateTime
     * @param hostName
     * @param serviceVersion
     * @param globalTransactionId
     * @param payload
     * @param event
     * @param summary
     * @param appUser
     * @param receiver
     * @param transactionMode
     * @param appName
     * @param nvp
     * @param businessIdentifier
     * @param serviceName
     * @param threadName
     * @param eventCode
     * @param environment
     * @param domain
     * @param eventSubcode
     * @param guid
     * @param step
     * @param detail
     * @param isPayloadTempered
     * @param status
     * @param alternateBusinessIdentifier
     */
    public JSONData(String status, String event, Object eventCode, Object eventSubcode, String step, Object summary, Object detail, String businessIdentifier, Object alternateBusinessIdentifier, String globalTransactionId, Object transactionMode, String domain, String environment, Object serviceName, String dateTime, String guid, Object serviceVersion, String appName, String appUser, String hostName, String threadName, Object payload, Object receiver, Object isPayloadTempered, String nvp) {
        super();
        this.status = status;
        this.event = event;
        this.eventCode = eventCode;
        this.eventSubcode = eventSubcode;
        this.step = step;
        this.summary = summary;
        this.detail = detail;
        this.businessIdentifier = businessIdentifier;
        this.alternateBusinessIdentifier = alternateBusinessIdentifier;
        this.globalTransactionId = globalTransactionId;
        this.transactionMode = transactionMode;
        this.domain = domain;
        this.environment = environment;
        this.serviceName = serviceName;
        this.dateTime = dateTime;
        this.guid = guid;
        this.serviceVersion = serviceVersion;
        this.appName = appName;
        this.appUser = appUser;
        this.hostName = hostName;
        this.threadName = threadName;
        this.payload = payload;
        this.receiver = receiver;
        this.isPayloadTempered = isPayloadTempered;
        this.nvp = nvp;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("event")
    public String getEvent() {
        return event;
    }

    @JsonProperty("event")
    public void setEvent(String event) {
        this.event = event;
    }

    @JsonProperty("eventCode")
    public Object getEventCode() {
        return eventCode;
    }

    @JsonProperty("eventCode")
    public void setEventCode(Object eventCode) {
        this.eventCode = eventCode;
    }

    @JsonProperty("eventSubcode")
    public Object getEventSubcode() {
        return eventSubcode;
    }

    @JsonProperty("eventSubcode")
    public void setEventSubcode(Object eventSubcode) {
        this.eventSubcode = eventSubcode;
    }

    @JsonProperty("step")
    public String getStep() {
        return step;
    }

    @JsonProperty("step")
    public void setStep(String step) {
        this.step = step;
    }

    @JsonProperty("summary")
    public Object getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(Object summary) {
        this.summary = summary;
    }

    @JsonProperty("detail")
    public Object getDetail() {
        return detail;
    }

    @JsonProperty("detail")
    public void setDetail(Object detail) {
        this.detail = detail;
    }

    @JsonProperty("business_identifier")
    public String getBusinessIdentifier() {
        return businessIdentifier;
    }

    @JsonProperty("business_identifier")
    public void setBusinessIdentifier(String businessIdentifier) {
        this.businessIdentifier = businessIdentifier;
    }

    @JsonProperty("alternate_business_identifier")
    public Object getAlternateBusinessIdentifier() {
        return alternateBusinessIdentifier;
    }

    @JsonProperty("alternate_business_identifier")
    public void setAlternateBusinessIdentifier(Object alternateBusinessIdentifier) {
        this.alternateBusinessIdentifier = alternateBusinessIdentifier;
    }

    @JsonProperty("global_transaction_id")
    public String getGlobalTransactionId() {
        return globalTransactionId;
    }

    @JsonProperty("global_transaction_id")
    public void setGlobalTransactionId(String globalTransactionId) {
        this.globalTransactionId = globalTransactionId;
    }

    @JsonProperty("transactionMode")
    public Object getTransactionMode() {
        return transactionMode;
    }

    @JsonProperty("transactionMode")
    public void setTransactionMode(Object transactionMode) {
        this.transactionMode = transactionMode;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("environment")
    public String getEnvironment() {
        return environment;
    }

    @JsonProperty("environment")
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @JsonProperty("service_name")
    public Object getServiceName() {
        return serviceName;
    }

    @JsonProperty("service_name")
    public void setServiceName(Object serviceName) {
        this.serviceName = serviceName;
    }

    @JsonProperty("date_time")
    public String getDateTime() {
        return dateTime;
    }

    @JsonProperty("date_time")
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonProperty("serviceVersion")
    public Object getServiceVersion() {
        return serviceVersion;
    }

    @JsonProperty("serviceVersion")
    public void setServiceVersion(Object serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    @JsonProperty("app_name")
    public String getAppName() {
        return appName;
    }

    @JsonProperty("app_name")
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @JsonProperty("appUser")
    public String getAppUser() {
        return appUser;
    }

    @JsonProperty("appUser")
    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    @JsonProperty("host_name")
    public String getHostName() {
        return hostName;
    }

    @JsonProperty("host_name")
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @JsonProperty("thread_name")
    public String getThreadName() {
        return threadName;
    }

    @JsonProperty("thread_name")
    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @JsonProperty("payload")
    public Object getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @JsonProperty("receiver")
    public Object getReceiver() {
        return receiver;
    }

    @JsonProperty("receiver")
    public void setReceiver(Object receiver) {
        this.receiver = receiver;
    }

    @JsonProperty("isPayloadTempered")
    public Object getIsPayloadTempered() {
        return isPayloadTempered;
    }

    @JsonProperty("isPayloadTempered")
    public void setIsPayloadTempered(Object isPayloadTempered) {
        this.isPayloadTempered = isPayloadTempered;
    }

    @JsonProperty("nvp")
    public String getNvp() {
        return nvp;
    }

    @JsonProperty("nvp")
    public void setNvp(String nvp) {
        this.nvp = nvp;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("event", event).append("eventCode", eventCode).append("eventSubcode", eventSubcode).append("step", step).append("summary", summary).append("detail", detail).append("businessIdentifier", businessIdentifier).append("alternateBusinessIdentifier", alternateBusinessIdentifier).append("globalTransactionId", globalTransactionId).append("transactionMode", transactionMode).append("domain", domain).append("environment", environment).append("serviceName", serviceName).append("dateTime", dateTime).append("guid", guid).append("serviceVersion", serviceVersion).append("appName", appName).append("appUser", appUser).append("hostName", hostName).append("threadName", threadName).append("payload", payload).append("receiver", receiver).append("isPayloadTempered", isPayloadTempered).append("nvp", nvp).append("additionalProperties", additionalProperties).toString();
    }

}