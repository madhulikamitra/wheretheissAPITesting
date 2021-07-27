package pojoHelpers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class satelliteResponse {

        @JsonProperty("name")
        public String getName() {
            return this.name; }
        public void setName(String name) {
            this.name = name; }
        String name;
        @JsonProperty("id")
        public int getId() {
            return this.id; }
        public void setId(int id) {
            this.id = id; }
        int id;
        @JsonProperty("latitude")
        public double getLatitude() {
            return this.latitude; }
        public void setLatitude(double latitude) {
            this.latitude = latitude; }
        double latitude;
        @JsonProperty("longitude")
       public double getLongitude() {
            return this.longitude; }
        void setLongitude(double longitude) {
            this.longitude = longitude; }
        double longitude;
        @JsonProperty("altitude")
        public double getAltitude() {
            return this.altitude; }
        public void setAltitude(double altitude) {
            this.altitude = altitude; }
        double altitude;
        @JsonProperty("velocity")
        public double getVelocity() {
            return this.velocity; }
        public void setVelocity(double velocity) {
            this.velocity = velocity; }
       double velocity;
        @JsonProperty("visibility")
        public String getVisibility() {
            return this.visibility; }
        public void setVisibility(String visibility) {
            this.visibility = visibility; }
        String visibility;
        @JsonProperty("footprint")
        public double getFootprint() {
            return this.footprint; }
        public void setFootprint(double footprint) {
            this.footprint = footprint; }
      double footprint;
        @JsonProperty("timestamp")
        public long getTimestamp() {
            return this.timestamp; }
        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp; }
        long timestamp;
        @JsonProperty("daynum")
        public double getDaynum() {
            return this.daynum; }
        public void setDaynum(double daynum) {
            this.daynum = daynum; }
 double daynum;
        @JsonProperty("solar_lat")
        public double getSolar_lat() {
            return this.solar_lat; }
        public void setSolar_lat(double solar_lat) {
            this.solar_lat = solar_lat; }
      double solar_lat;
        @JsonProperty("solar_lon")
        public double getSolar_lon() {
            return this.solar_lon; }
        public void setSolar_lon(double solar_lon) {
            this.solar_lon = solar_lon; }
  double solar_lon;
        @JsonProperty("units")
        public String getUnits() {
            return this.units; }
        public void setUnits(String units) {
            this.units = units; }
     String units;
    }




