package com.alura.cursos.forohub.domain.topics;

import com.alura.cursos.forohub.domain.courses.CourseRepository;
import com.alura.cursos.forohub.domain.users.UserRepository;
import com.alura.cursos.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TopicService {
  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CourseRepository courseRepository;

  public DataAnswerTopic createTopic(DataCreateTopic dataCreateTopic) {

    if (dataCreateTopic.idUser() != null && !userRepository.findById(dataCreateTopic.idUser()).isPresent()) {
      throw new IntegrityValidation("id user not found");
    }

    if (dataCreateTopic.idCourse() != null && !courseRepository.findById(dataCreateTopic.idCourse()).isPresent()) {
      throw new IntegrityValidation("id course not found");
    }

    var user = userRepository.findById(dataCreateTopic.idUser()).get();
    var course = courseRepository.findById(dataCreateTopic.idCourse()).get();

    var topic = new Topic(
      dataCreateTopic.title(),
      dataCreateTopic.message(),
      user,
      course
    );

    topicRepository.save(topic);
    return new DataAnswerTopic(topic);
  }
}

