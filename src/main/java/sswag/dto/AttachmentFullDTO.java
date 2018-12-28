package sswag.dto;

import io.swagger.annotations.ApiModelProperty;

public class AttachmentFullDTO {

    @ApiModelProperty(position = 0)
    private Integer id;

    @ApiModelProperty(position = 1)
    private String fileName;

    @ApiModelProperty(position = 2)
    private String contentType;

    @ApiModelProperty(position = 3)
    private byte[] bitsInBytes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getBitsInBytes() {
        return bitsInBytes;
    }

    public void setBitsInBytes(byte[] bitsInBytes) {
        this.bitsInBytes = bitsInBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
