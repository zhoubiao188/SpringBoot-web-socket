package cn.com.scitc.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -67091216056889863L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
