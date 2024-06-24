package com.alura.cursos.forohub.domain.anwer;

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
  private boolean solution;
  @ManyToOne
  private Topic topics;
  @ManyToOne
  private User user;
}
