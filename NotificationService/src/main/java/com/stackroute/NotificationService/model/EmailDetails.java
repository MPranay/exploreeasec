package com.stackroute.NotificationService.model;

public class EmailDetails {

    public String recipient;
    public String msgbody;
    public String subject;
    public String attachment;

    public EmailDetails() {
    }

    public EmailDetails(String recipient, String msgbody, String subject, String attachment) {
        this.recipient = recipient;
        this.msgbody = msgbody;
        this.subject = subject;
        this.attachment = attachment;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

//    public String getMsgbody() {
//        return msgbody;
//    }

    public void setMsgbody(String msgbody) {
        this.msgbody = msgbody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "EmailDetails{" +
                "recipient='" + recipient + '\'' +
                ", msgbody='" + msgbody + '\'' +
                ", subject='" + subject + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }


    public String getMsgBody() {
        return msgbody;
    }

}
