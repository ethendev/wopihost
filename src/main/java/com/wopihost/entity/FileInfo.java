package com.wopihost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 文件属性对象
 *
 * 由于wopi的接口不遵守驼峰命名规则，所以需要用@JsonProperty指定别名，
 * 同时在getter上@JsonIgnore，防止出现2个名字
 * Created by admin on 2017/4/15.
 */
public class FileInfo implements Serializable {

    /**
     * 文件名
     */
    @JsonProperty("BaseFileName")
    public String baseFileName;

    /**
     * 文件所有者的唯一编号
     */
    @JsonProperty("OwnerId")
    public String ownerId;

    /**
     * 文件大小，以bytes为单位
     */
    @JsonProperty("Size")
    public long size;

    /**
     * 文件的256位bit的SHA-2编码散列内容
     */
    @JsonProperty("SHA256")
    public String sha256;

    /**
     * 文件版本号，文件如果被编辑，版本号也要跟着改变
     */
    @JsonProperty("Version")
    public long version;

    /**
     * 允许外部服务的连接
     */
    @JsonProperty("AllowExternalMarketplace")
    public boolean allowExternalMarketplace;

    /**
     * 更改文件的权限
     */
    @JsonProperty("UserCanWrite")
    public boolean userCanWrite;

    @JsonProperty("SupportsUpdate")
    public boolean supportsUpdate;

    @JsonIgnore
    public String getBaseFileName() {
        return baseFileName;
    }

    public void setBaseFileName(String baseFileName) {
        this.baseFileName = baseFileName;
    }

    @JsonIgnore
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonIgnore
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @JsonIgnore
    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @JsonIgnore
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @JsonIgnore
    public boolean isAllowExternalMarketplace() {
        return allowExternalMarketplace;
    }

    public void setAllowExternalMarketplace(boolean allowExternalMarketplace) {
        this.allowExternalMarketplace = allowExternalMarketplace;
    }

    @JsonIgnore
    public boolean isUserCanWrite() {
        return userCanWrite;
    }

    public void setUserCanWrite(boolean userCanWrite) {
        this.userCanWrite = userCanWrite;
    }

    @JsonIgnore
    public boolean isSupportsUpdate() {
        return supportsUpdate;
    }

    public void setSupportsUpdate(boolean supportsUpdate) {
        this.supportsUpdate = supportsUpdate;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "baseFileName='" + baseFileName + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", size=" + size +
                ", sha256='" + sha256 + '\'' +
                ", version=" + version +
                ", allowExternalMarketplace=" + allowExternalMarketplace +
                ", userCanWrite=" + userCanWrite +
                ", supportsUpdate=" + supportsUpdate +
                '}';
    }

}
