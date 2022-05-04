package com.example.task;


import com.example.user.User;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 250, name = "content")
    private String content;

    @Column(name = "status", nullable = false)
    private boolean status = false;

    @Column(length = 250, name = "image_id")
    private String imageId;

    @Column(length = 45, name = "deadline")
    private String deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", imageId='" + imageId + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
