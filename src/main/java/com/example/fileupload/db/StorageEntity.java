package com.example.fileupload.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Created by spielerl on 07/10/2017.
 */
@Entity
@Table(name = "STORAGE_ENTITY")
public class StorageEntity extends AbstractPersistable<Long>{
    @Version //optimistic lock
    private long version;

    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Column(name = "SIZE")
    private long size;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getVersion() {
        return version;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
