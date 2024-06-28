package com.alura.cursos.forohub.domain.answer;

import com.alura.cursos.forohub.domain.topics.TopicRepository;
import com.alura.cursos.forohub.domain.users.UserRepository;
import com.alura.cursos.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AnswerRepository answerRepository;

  public DataAnswer createAnswer(DataCreateAnswer dataCreateAnswer) {

    if (!userRepository.findById(dataCreateAnswer.idUser()).isPresent()) {
      throw new IntegrityValidation("id user not found");
    }

    if (!topicRepository.findById(dataCreateAnswer.idTopic()).isPresent()) {
      throw new IntegrityValidation("id topic not found");
    }

    var user = userRepository.findById(dataCreateAnswer.idUser()).get();
    var topic = topicRepository.findById(dataCreateAnswer.idTopic()).get();

    var answer = new Answer(
      dataCreateAnswer.message(),
      dataCreateAnswer.solved(),
      topic,
      user
    );

    answerRepository.save(answer);
    return new DataAnswer(answer);
  }

}
