package com.alura.cursos.forohub.domain.topics;

import com.alura.cursos.forohub.domain.anwer.Answer;
import com.alura.cursos.forohub.domain.courses.Course;
import com.alura.cursos.forohub.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topic")
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
  @Enumerated(EnumType.STRING)
  private TopicStatus status;
  @ManyToOne
  private User user;
  @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Answer> answers;
  @ManyToOne
  private Course course;

}
