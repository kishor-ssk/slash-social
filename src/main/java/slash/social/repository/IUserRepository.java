package slash.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slash.social.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByPhoneNumber(String phoneNumber);

    UserEntity findByPassword(String password);
}
