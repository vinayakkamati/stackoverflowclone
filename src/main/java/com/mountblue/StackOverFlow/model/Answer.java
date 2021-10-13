package com.mountblue.StackOverFlow.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;

    @Column(name = "content")
    private String content;

    @Column(name = "createdAt")
    private Date createTime;

    @Column(name = "user_nmae")
    private String userName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "answer",
            cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = true)
    private Question question;

    public Answer() {
    }

    public Answer(Integer answerId, String content, Date createTime, String userName, String email,
                  List<Comment> comments, Question question) {
        this.answerId = answerId;
        this.content = content;
        this.createTime = createTime;
        this.userName = userName;
        this.email = email;
        this.comments = comments;
        this.question = question;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
