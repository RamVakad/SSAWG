package sswag.dto;

import io.swagger.annotations.ApiModelProperty;

public class NewAttachmentDTO {

    @ApiModelProperty(position = 0)
    private String fileName;

    @ApiModelProperty(position = 1)
    private String contentType;

    @ApiModelProperty(position = 2)
    private byte[] bitsInBytes;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBitsInBytes() {
        return bitsInBytes;
    }

    public void setBitsInBytes(byte[] bitsInBytes) {
        this.bitsInBytes = bitsInBytes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
