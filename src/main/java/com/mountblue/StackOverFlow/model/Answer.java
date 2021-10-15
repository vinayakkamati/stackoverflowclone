package com.mountblue.StackOverFlow.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;


    @Column(name = "content", columnDefinition = "text" )
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
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id ")
    private User  author;


    @Column(name = "is_accepted")
    private Boolean isAccepted = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "positive_votes",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> positiveVotes;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "negative_votes",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> negativeVotes;

    public Answer() {
    }

    public Answer(Integer answerId, String content, Date createTime, String userName, String email, List<Comment> comments, Question question, User author,
                  Boolean isAccepted, Set<User> positiveVotes, Set<User> negativeVotes) {
        this.answerId = answerId;
        this.content = content;
        this.createTime = createTime;
        this.userName = userName;
        this.email = email;
        this.comments = comments;
        this.question = question;
        this.author = author;
        this.isAccepted = isAccepted;
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Set<User> getPositiveVotes() {
        return positiveVotes;
    }

    public void setPositiveVotes(Set<User> positiveVotes) {
        this.positiveVotes = positiveVotes;
    }

    public Set<User> getNegativeVotes() {
        return negativeVotes;
    }

    public void setNegativeVotes(Set<User> negativeVotes) {
        this.negativeVotes = negativeVotes;
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
