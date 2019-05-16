package com.mchain.apollo.common.entity.base;

import java.io.Serializable;

/**
 * 描述: 区块链存储的文件信息
 *
 * @author alex
 * @create 2019-01-24 11:38
 */

public class FileInfo implements Serializable {
    private static final long serialVersionUID = 4016582074563345149L;

    private String fileId;
    private String fileHash;
    private String fileName;
    private String fileSize;
    private String fileType;
    private String uploadTime;
    private String dataType;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
