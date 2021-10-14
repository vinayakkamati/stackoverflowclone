package com.mountblue.StackOverFlow.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @Column(name = "title")
    private String  title;
    @Column(name = " description " ,length = 10485760)
    private String description;
    @Column(name ="view_counts")
    private int viewCount;
    @Column(name = "vote_counts")
    private int voteCount;
    @Column(name = "created_at")
    private Date  createDate=null;
    @Column(name = "updated_at")
    private Date updateDate = null;

  /*  @OneToMany(mappedBy = "question",
            cascade = {CascadeType.REMOVE})
    List<Comment> comments = new ArrayList<>();*/

    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL)
    List<Answer> answers = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "question_tags",
            joinColumns = { @JoinColumn(name = "question_id")},
            inverseJoinColumns = { @JoinColumn (name = "tag_id")})
    private Set<Tag> tags = new HashSet<>();

    public Question() {
    }

    public Question(int questionId, String title, String description, int viewCount, int voteCount,
                    Date createDate, Date updateDate) {
        this.questionId = questionId;
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
        this.voteCount = voteCount;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
