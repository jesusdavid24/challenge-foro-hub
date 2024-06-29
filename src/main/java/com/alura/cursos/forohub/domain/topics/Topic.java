package com.alura.cursos.forohub.domain.topics;

import com.alura.cursos.forohub.domain.answer.Answer;
import com.alura.cursos.forohub.domain.courses.Course;
import com.alura.cursos.forohub.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String message;

  private LocalDateTime dateCreation;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private TopicStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Answer> answers = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private Course course;

  private Boolean isDeleted;
  public Topic(
    String title,
    String message,
    User user,
    Course course)
  {
    this.title = title;
    this.message = message;
    this.dateCreation = LocalDateTime.now();
    this.status = TopicStatus.UNANSWERED;
    this.user = user;
    this.course = course;
    this.isDeleted = false;
  }

  public void setStatus(TopicStatus status) {
    this.status = status;
  }

  public void putData(DataUpdateTopic data) {
    if (data.title() != null) {
      this.title = data.title();
    }
    if (data.message() != null) {
      this.message = data.message();
    }
  }

  public void deletedTopic() {this.isDeleted = true;}
}
