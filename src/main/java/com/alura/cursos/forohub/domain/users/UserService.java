package com.alura.cursos.forohub.domain.users;

import com.alura.cursos.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
      dataRecordsUsers.email(),
      hashedPassword
    );

    userRepository.save(user);
    return new DataAnswerUser(user);
  }

  public List<DataListUser> getUsers() {
    List<User> activeUsers = userRepository.findByIsActiveTrue();
    return activeUsers.stream()
      .map(DataListUser::new)
      .collect(Collectors.toList());
  }

  public DataAnswerUser updateUser(DataUpdateUser dataUpdateUser) {
    if (!userRepository.findById(dataUpdateUser.id()).isPresent()) {
      throw new IntegrityValidation("id user not found");
    }

    User user = userRepository.getReferenceById(dataUpdateUser.id());

    if (dataUpdateUser.password() != null) {
      String hashedPassword = passwordEncoder.encode(dataUpdateUser.password());
      user.setPassword(hashedPassword);
    }

    user.setName(dataUpdateUser.name());
    user.setEmail(dataUpdateUser.email());
    userRepository.save(user);

    return new DataAnswerUser(user);
  }

  public void deletedUser(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IntegrityValidation("User not found with id"));

    user.deletedUser();
    userRepository.save(user);
  }
}
