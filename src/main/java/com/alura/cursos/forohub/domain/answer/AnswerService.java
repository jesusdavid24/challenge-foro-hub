package com.alura.cursos.forohub.domain.answer;

import com.alura.cursos.forohub.domain.topics.TopicRepository;
import com.alura.cursos.forohub.domain.users.UserRepository;
import com.alura.cursos.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

  public Page<DataListAnswer> getAnswer(Pageable pageable) {
    Page<Answer> answerPage = answerRepository.findByIsDeletedFalse(pageable);
    return answerPage.map(DataListAnswer::new);
  }

  public DataAnswer detailsAnswer(@PathVariable Long id) {
    Answer answer = answerRepository.getReferenceById(id);
    return new DataAnswer(answer);
  }

  public DataAnswer updateAnswer(DataUpdateAnswer dataUpdateAnswer) {
    if(!answerRepository.findById(dataUpdateAnswer.id()).isPresent()) {
      throw new IntegrityValidation("id answer not found");
    }

    Answer answer = answerRepository.getReferenceById(dataUpdateAnswer.id());
    answer.putData(dataUpdateAnswer);

    return new DataAnswer(answer);
  }

  public void deletedAnswer(Long id) {
    Answer answer = answerRepository.findById(id)
      .orElseThrow(() -> new IntegrityValidation("Topic not found with id"));

    answer.deletedAnswer();
    answerRepository.save(answer);
  }

}
