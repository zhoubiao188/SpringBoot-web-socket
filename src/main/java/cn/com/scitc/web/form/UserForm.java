package cn.com.scitc.web.form;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserForm {

    private Long id;

    @NotNull(message = "用户名称不能为空")
    @Size(min = 2,max = 10,message = "用户名称的长度只能是2到10位")
    private String nickname; //用户名称

    @NotNull(message = "用户名不能为空")
    @Size(min = 4,max = 11,message = "用户名的长度只能是4到11位")
    private String username; //用户名

    @NotNull(message = "密码不能为空")
    @Size(min = 6,max = 22,message = "密码的长度只能是6到22位")

    private String password; // 用户密码

    private int userType;  //用户头像

    private String headimg; //用户头像

    private Date createTime;

    private Date lastLoginTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", headimg='" + headimg + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
