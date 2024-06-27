package com.alura.cursos.forohub.domain.topics;

import com.alura.cursos.forohub.domain.courses.CourseRepository;
import com.alura.cursos.forohub.domain.users.UserRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.alura.cursos.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class TopicService {
  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CourseRepository courseRepository;

  public DataAnswerTopic createTopic(DataCreateTopic dataCreateTopic) {

    if (!userRepository.findById(dataCreateTopic.idUser()).isPresent()) {
      throw new IntegrityValidation("id user not found");
    }

    if (!courseRepository.findById(dataCreateTopic.idCourse()).isPresent()) {
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

  public Page<DataListTopic> getTopics(Pageable pageable) {
    Page<Topic> topicPage = topicRepository.findByIsDeletedFalse(pageable);
    return topicPage.map(DataListTopic::new);
  }

  public Page<DataListTopic> getTopicsByCourseNameAndDateCreation(String courseName, String year, Pageable pageable) {
    var course = courseRepository.findByName(courseName);
    if (course == null) {
      throw new IntegrityValidation("Course does not exist");
    }

    LocalDateTime startDate;
    try {
      int yearNumber = Integer.parseInt(year);
      startDate = LocalDateTime.of(yearNumber, 1, 1, 0, 0);
    } catch (NumberFormatException e) {
      throw new IntegrityValidation("Invalid year format");
    }

    var topics = topicRepository.findByCourseAndDateCreationAfter(course, startDate, pageable);

    var dataListTopics = topics.getContent().stream()
      .map(DataListTopic::new)
      .collect(Collectors.toList());

    return new PageImpl<>(dataListTopics, pageable, topics.getTotalElements());
  }

  public DataAnswerTopic detailsTopic(@PathVariable Long id) {
    Topic topic = topicRepository.getReferenceById(id);
    return new DataAnswerTopic(topic);
  }

  public DataAnswerTopic updateTopic(DataAccurateTopic dataAccurateTopic) {
    if (!topicRepository.findById(dataAccurateTopic.id()).isPresent()) {
      throw new IntegrityValidation("id user not found");
    }

    Topic topic = topicRepository.getReferenceById(dataAccurateTopic.id());
    topic.putData(dataAccurateTopic);

    return new DataAnswerTopic(topic);
  }

  public void deletedTopic(Long id) {
    Topic topic = topicRepository.findById(id)
      .orElseThrow(() -> new IntegrityValidation("Topic not found with id: " + id));

    topic.deletedTopic();
    topicRepository.save(topic);
  }
}

