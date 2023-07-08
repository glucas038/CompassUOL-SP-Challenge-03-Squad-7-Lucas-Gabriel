package br.com.compassuol.pb.challenge.msproducts.repository;

import br.com.compassuol.pb.challenge.msproducts.dto.UserDTO;
import br.com.compassuol.pb.challenge.msproducts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<List<User>> findAllByEmail(String email);
    Boolean existsByEmail(String email);

}
