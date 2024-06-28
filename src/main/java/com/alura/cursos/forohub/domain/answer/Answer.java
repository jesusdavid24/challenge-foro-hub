package com.alura.cursos.forohub.domain.answer;

import com.alura.cursos.forohub.domain.topics.Topic;
import com.alura.cursos.forohub.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "answers")
@Entity(name = "Answer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;

  private Boolean solved;

  private LocalDateTime dateCreation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topic_id")
  private Topic topics;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  private Boolean isDeleted;

  public Answer(String message, Boolean solved, Topic topics, User user) {
    this.message = message;
    this.solved = solved != null ? solved : false;
    this.dateCreation = LocalDateTime.now();
    this.topics = topics;
    this.user = user;
    this.isDeleted = false;
  }

  public void putData(DataUpdateAnswer data) {
    if (data.message() != null) {
      this.message = data.message();
      this.dateCreation = LocalDateTime.now();
    }
  }

  public void deletedAnswer() {this.isDeleted = true;}
}
