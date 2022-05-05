package app.flipn.airquality.consumer.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DateObserved",
    "HourObserved",
    "LocalTimeZone",
    "ReportingArea",
    "StateCode",
    "Latitude",
    "Longitude",
    "ParameterName",
    "AQI",
    "Category"
})
public class Observation {

    @JsonProperty("DateObserved")
    private String dateObserved;
    @JsonProperty("HourObserved")
    private Integer hourObserved;
    @JsonProperty("LocalTimeZone")
    private String localTimeZone;
    @JsonProperty("ReportingArea")
    private String reportingArea;
    @JsonProperty("StateCode")
    private String stateCode;
    @JsonProperty("Latitude")
    private Double latitude;
    @JsonProperty("Longitude")
    private Double longitude;
    @JsonProperty("ParameterName")
    private String parameterName;
    @JsonProperty("AQI")
    private Integer aqi;
    @JsonProperty("Category")
    private Category category;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *             log.debug("Received k:" + msg.getKey());
     *             log.debug("Received p,t:" + msg.getProducerName()
     *                     + "," + msg.getEventTime() );
     *             log.debug("Received ID:" + msg.getMessageId() );
     */
    private String key;

    private String producerName;

    private String eventTime;

    private String messageId;

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Observation() {
        super();
    }

    /**
     * 
     * @param dateObserved
     * @param latitude
     * @param localTimeZone
     * @param aqi
     * @param stateCode
     * @param parameterName
     * @param hourObserved
     * @param category
     * @param reportingArea
     * @param longitude
     */
    public Observation(String dateObserved, Integer hourObserved, String localTimeZone, String reportingArea, String stateCode, Double latitude, Double longitude, String parameterName, Integer aqi, Category category) {
        super();
        this.dateObserved = dateObserved;
        this.hourObserved = hourObserved;
        this.localTimeZone = localTimeZone;
        this.reportingArea = reportingArea;
        this.stateCode = stateCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parameterName = parameterName;
        this.aqi = aqi;
        this.category = category;
    }

    public Observation(String dateObserved, Integer hourObserved, String localTimeZone, String reportingArea, String stateCode, Double latitude, Double longitude, String parameterName, Integer aqi, Category category, Map<String, Object> additionalProperties, String key, String producerName, String eventTime, String messageId) {
        super();
        this.dateObserved = dateObserved;
        this.hourObserved = hourObserved;
        this.localTimeZone = localTimeZone;
        this.reportingArea = reportingArea;
        this.stateCode = stateCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parameterName = parameterName;
        this.aqi = aqi;
        this.category = category;
        this.additionalProperties = additionalProperties;
        this.key = key;
        this.producerName = producerName;
        this.eventTime = eventTime;
        this.messageId = messageId;
    }

    @JsonProperty("DateObserved")
    public String getDateObserved() {
        return dateObserved;
    }

    @JsonProperty("DateObserved")
    public void setDateObserved(String dateObserved) {
        this.dateObserved = dateObserved;
    }

    @JsonProperty("HourObserved")
    public Integer getHourObserved() {
        return hourObserved;
    }

    @JsonProperty("HourObserved")
    public void setHourObserved(Integer hourObserved) {
        this.hourObserved = hourObserved;
    }

    @JsonProperty("LocalTimeZone")
    public String getLocalTimeZone() {
        return localTimeZone;
    }

    @JsonProperty("LocalTimeZone")
    public void setLocalTimeZone(String localTimeZone) {
        this.localTimeZone = localTimeZone;
    }

    @JsonProperty("ReportingArea")
    public String getReportingArea() {
        return reportingArea;
    }

    @JsonProperty("ReportingArea")
    public void setReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
    }

    @JsonProperty("StateCode")
    public String getStateCode() {
        return stateCode;
    }

    @JsonProperty("StateCode")
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @JsonProperty("Latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("Latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("Longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("Longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("ParameterName")
    public String getParameterName() {
        return parameterName;
    }

    @JsonProperty("ParameterName")
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @JsonProperty("AQI")
    public Integer getAqi() {
        return aqi;
    }

    @JsonProperty("AQI")
    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    @JsonProperty("Category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("Category")
    public void setCategory(Category category) {
        this.category = category;
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
        return new StringJoiner(", ", Observation.class.getSimpleName() + "[", "]")
                .add("dateObserved='" + dateObserved + "'")
                .add("hourObserved=" + hourObserved)
                .add("localTimeZone='" + localTimeZone + "'")
                .add("reportingArea='" + reportingArea + "'")
                .add("stateCode='" + stateCode + "'")
                .add("latitude=" + latitude)
                .add("longitude=" + longitude)
                .add("parameterName='" + parameterName + "'")
                .add("aqi=" + aqi)
                .add("category=" + category)
                .add("additionalProperties=" + additionalProperties)
                .add("key='" + key + "'")
                .add("producerName='" + producerName + "'")
                .add("eventTime='" + eventTime + "'")
                .add("messageId='" + messageId + "'")
                .toString();
    }
}