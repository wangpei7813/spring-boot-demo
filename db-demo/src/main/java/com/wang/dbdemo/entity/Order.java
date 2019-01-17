package com.wang.dbdemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;

    private String orderType;

    private String cityId;

    private String platformId;

    private String platformOrderId;

    private String poiId;

    private String senderAddress;

    private String senderPhone;

    private Integer senderLng;

    private Integer senderLat;

    private String senderName;

    private String receiverAddress;

    private String receiverPhone;

    private Integer receiverLng;

    private Integer receiverLat;

    private String receiverName;

    private String remark;

    private BigDecimal pkgPrice;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    public String getPlatformOrderId() {
        return platformOrderId;
    }

    public void setPlatformOrderId(String platformOrderId) {
        this.platformOrderId = platformOrderId == null ? null : platformOrderId.trim();
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId == null ? null : poiId.trim();
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress == null ? null : senderAddress.trim();
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone == null ? null : senderPhone.trim();
    }

    public Integer getSenderLng() {
        return senderLng;
    }

    public void setSenderLng(Integer senderLng) {
        this.senderLng = senderLng;
    }

    public Integer getSenderLat() {
        return senderLat;
    }

    public void setSenderLat(Integer senderLat) {
        this.senderLat = senderLat;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public Integer getReceiverLng() {
        return receiverLng;
    }

    public void setReceiverLng(Integer receiverLng) {
        this.receiverLng = receiverLng;
    }

    public Integer getReceiverLat() {
        return receiverLat;
    }

    public void setReceiverLat(Integer receiverLat) {
        this.receiverLat = receiverLat;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getPkgPrice() {
        return pkgPrice;
    }

    public void setPkgPrice(BigDecimal pkgPrice) {
        this.pkgPrice = pkgPrice;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}