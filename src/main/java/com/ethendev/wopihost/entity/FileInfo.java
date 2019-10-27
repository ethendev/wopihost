package com.ethendev.wopihost.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * File property object, because wopi does not follow the hump naming rules,
 * we need to specify the alias with @JsonProperty
 *
 * @author ethendev
 * @date 2017/4/15
 */
public class FileInfo implements Serializable {

    /**
     * file name
     */
    @JsonProperty("BaseFileName")
    private String baseFileName;

    /**
     * A string that uniquely identifies the owner of the file
     */
    @JsonProperty("OwnerId")
    private String ownerId;

    /**
     * File size in bytes
     */
    @JsonProperty("Size")
    private long size;

    /**
     * A 256 bit SHA-2-encoded hash of the file contents, as a Base64-encoded string.
     * Used for caching purposes in WOPI clients.
     */
    @JsonProperty("SHA256")
    private String sha256;

    /**
     * The current version of the file based on the server’s file version schema
     * This value must change when the file changes, and version values must never repeat for a given file.
     */
    @JsonProperty("Version")
    private long version;

    /**
     * indicates a WOPI client may allow connections to external services referenced in the file
     */
    @JsonProperty("AllowExternalMarketplace")
    private boolean allowExternalMarketplace = true;

    /**
     * indicates that the WOPI client if allow the user to edit the file
     */
    @JsonProperty("UserCanWrite")
    private boolean userCanWrite = true;

    /**
     * if the host supports the update operations
     */
    @JsonProperty("SupportsUpdate")
    private boolean supportsUpdate = true;

    /**
     * if the host supports the GetLock operation.
     */
    @JsonProperty("SupportsGetLock")
    private boolean supportsGetLock = true;

    /**
     * indicates that the host supports the following WOPI operations:
     * Lock, Unlock, RefreshLock, UnlockAndRelock
     */
    @JsonProperty("SupportsLocks")
    private boolean supportsLocks = true;

    /**
     * user does not have sufficient permission to create new files on the WOPI server
     */
    @JsonProperty("UserCanNotWriteRelative")
    private boolean userCanNotWriteRelative = true;

    public String getBaseFileName() {
        return baseFileName;
    }

    public void setBaseFileName(String baseFileName) {
        this.baseFileName = baseFileName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isAllowExternalMarketplace() {
        return allowExternalMarketplace;
    }

    public void setAllowExternalMarketplace(boolean allowExternalMarketplace) {
        this.allowExternalMarketplace = allowExternalMarketplace;
    }

    public boolean isUserCanWrite() {
        return userCanWrite;
    }

    public void setUserCanWrite(boolean userCanWrite) {
        this.userCanWrite = userCanWrite;
    }

    public boolean isSupportsUpdate() {
        return supportsUpdate;
    }

    public void setSupportsUpdate(boolean supportsUpdate) {
        this.supportsUpdate = supportsUpdate;
    }

    public boolean isSupportsGetLock() {
        return supportsGetLock;
    }

    public void setSupportsGetLock(boolean supportsGetLock) {
        this.supportsGetLock = supportsGetLock;
    }

    public boolean isSupportsLocks() {
        return supportsLocks;
    }

    public void setSupportsLocks(boolean supportsLocks) {
        this.supportsLocks = supportsLocks;
    }

    public boolean isUserCanNotWriteRelative() {
        return userCanNotWriteRelative;
    }

    public void setUserCanNotWriteRelative(boolean userCanNotWriteRelative) {
        this.userCanNotWriteRelative = userCanNotWriteRelative;
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
                ", supportsGetLock=" + supportsGetLock +
                ", supportsLocks=" + supportsLocks +
                '}';
    }

}