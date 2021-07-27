package pojoHelpers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class tleResponse {

        @JsonProperty("requested_timestamp")
        public int getRequested_timestamp() {
            return this.requested_timestamp; }
        public void setRequested_timestamp(int requested_timestamp) {
            this.requested_timestamp = requested_timestamp; }
        int requested_timestamp;
        @JsonProperty("tle_timestamp")
        public int getTle_timestamp() {
            return this.tle_timestamp; }
        public void setTle_timestamp(int tle_timestamp) {
            this.tle_timestamp = tle_timestamp; }
        int tle_timestamp;
        @JsonProperty("id")
        public String getId() {
            return this.id; }
        public void setId(String id) {
            this.id = id; }
        String id;
        @JsonProperty("name")
        public String getName() {
            return this.name; }
        public void setName(String name) {
            this.name = name; }
        String name;
        @JsonProperty("header")
        public String getHeader() {
            return this.header; }
        public void setHeader(String header) {
            this.header = header; }
        String header;
        @JsonProperty("line1")
        public String getLine1() {
            return this.line1; }
        public void setLine1(String line1) {
            this.line1 = line1; }
        String line1;
        @JsonProperty("line2")
        public String getLine2() {
            return this.line2; }
        public void setLine2(String line2) {
            this.line2 = line2; }
        String line2;
    }

