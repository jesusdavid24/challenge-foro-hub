package com.alura.cursos.forohub.domain.answer;

import com.alura.cursos.forohub.domain.topics.Topic;
import com.alura.cursos.forohub.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
    this.topics = topics;
    this.user = user;
    this.isDeleted = false;
  }
}
