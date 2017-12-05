package com.ssru.travel.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTravel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("placeMost")
    @Expose
    private String placeMost;
    @SerializedName("timeOpen")
    @Expose
    private String timeOpen;
    @SerializedName("timeClose")
    @Expose
    private String timeClose;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("amphoe")
    @Expose
    private Integer amphoe;
    @SerializedName("district")
    @Expose
    private Integer district;
    @SerializedName("province")
    @Expose
    private Integer province;
    @SerializedName("zipcode")
    @Expose
    private Integer zipcode;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("image360")
    @Expose
    private String image360;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPlaceMost() {
        return placeMost;
    }

    public void setPlaceMost(String placeMost) {
        this.placeMost = placeMost;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }

    public String getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(String timeClose) {
        this.timeClose = timeClose;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAmphoe() {
        return amphoe;
    }

    public void setAmphoe(Integer amphoe) {
        this.amphoe = amphoe;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage360() {
        return image360;
    }

    public void setImage360(String image360) {
        this.image360 = image360;
    }
}