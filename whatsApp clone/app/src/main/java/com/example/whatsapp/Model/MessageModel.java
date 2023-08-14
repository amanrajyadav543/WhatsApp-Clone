package com.example.whatsapp.Model;

public class MessageModel {

    String UId , message ;

    Long timestamp;

    public MessageModel(String UId, String message, Long timestamp) {
        this.UId = UId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageModel(String UId, String message) {
        this.UId = UId;
        this.message = message;
    }
    public MessageModel(){

    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
