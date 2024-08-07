package slash.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slash.social.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String emailId);

    UserEntity findByPassword(String password);

    UserEntity findByUserId(Long userId);

}
