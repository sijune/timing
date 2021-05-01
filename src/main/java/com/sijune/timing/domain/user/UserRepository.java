package com.sijune.timing.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //Email 체크를 통해 가입된 사용자 인지 판단


}