package com.qq.bean;

import java.io.Serializable;


public class UserInfo implements Serializable{
    private static final long serialVersionUID = 4180299203292107281L;

    private Long qq; //qq号
    private String nickname; //昵称
    private String password; //密码
    private Boolean sex; //性别 男=1,女=0
    private String birthday; //生日
    private String star; //星座
    private String study; //学历
    private String blood; //血型
    private String phone; //电话
    private String email; //邮箱
    private String address; //住址
    private Integer id;
    private String sign;    //个性签名

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (qq != null ? !qq.equals(userInfo.qq) : userInfo.qq != null) return false;
        return id != null ? id.equals(userInfo.id) : userInfo.id == null;
    }

    @Override
    public int hashCode() {
        int result = qq != null ? qq.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "qq=" + qq +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", star='" + star + '\'' +
                ", study='" + study + '\'' +
                ", blood='" + blood + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                ", sign='" + sign + '\'' +
                '}';
    }
}
