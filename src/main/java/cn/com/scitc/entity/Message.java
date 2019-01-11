package cn.com.scitc.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class Message  implements Serializable {

    private static final long serialVersionUID = 3731356920451876662L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String text; //聊天内容

    private String receiver; //接受者

    private String sender;//发送者


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


}
