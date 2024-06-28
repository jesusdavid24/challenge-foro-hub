package com.alura.cursos.forohub.domain.courses;

import com.alura.cursos.forohub.domain.topics.DataUpdateTopic;
import com.alura.cursos.forohub.domain.topics.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "category")
  @Enumerated(EnumType.STRING)
  private Category category;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Topic> topics;

  private Boolean isDeleted;

  public Course(String name, Category category) {
    this.name = name;
    this.category = category;
    this.isDeleted = false;
  }

  public void putData(DataUpdateCourse data) {
    if (data.name() != null) {
      this.name = data.name();
    }
    if (data.category() != null) {
      this.category = data.category();
    }
  }

  public void deletedCourse() {this.isDeleted = true;}
}
