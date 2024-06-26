package com.alura.cursos.forohub.domain.topics;

import com.alura.cursos.forohub.domain.courses.CourseRepository;
import com.alura.cursos.forohub.domain.users.UserRepository;
import org.springframework.data.domain.Pageable;
import com.alura.cursos.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

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

    if (topicRepository.findByTitleAndMessage(dataCreateTopic.title(), dataCreateTopic.message()).isPresent()) {
      throw new IntegrityValidation("There already exists a topic with the same title and message");
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

  public Page<DataListTopic> getTopics(
    @PageableDefault(size = 10, sort = "dateCreation" )
    Pageable pageable
  ) {
    Page<Topic> topicPage = topicRepository.findByIsDeletedFalse(pageable);
    return topicPage.map(DataListTopic::new);
  }
}

