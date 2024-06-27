package com.alura.cursos.forohub.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  private BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository) {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }
  public DataAnswerUser createUser(DataRecordsUsers dataRecordsUsers) {
    String hashedPassword = passwordEncoder.encode(dataRecordsUsers.password());

    var user = new User(
      dataRecordsUsers.name(),
      dataRecordsUsers.name(),
      hashedPassword
    );

    userRepository.save(user);
    return new DataAnswerUser(user);
  }
}
