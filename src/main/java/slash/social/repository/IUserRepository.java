package slash.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slash.social.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmailId(String emailId);

    UserEntity findByPassword(String password);
}
