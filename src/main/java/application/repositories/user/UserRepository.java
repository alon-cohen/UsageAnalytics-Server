//package application.repositories.user;
//
//import application.model.User;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//
//@Repository
//public interface UserRepository extends CrudRepository<User, String>, UserRepositoryCustom{
//    Optional<User> findById(String id);
//    Optional<User> findByEmail(String email);
//    Optional<User> findByFirstName(String username);
//    Optional<User> findByPhoneNumber(String phoneNumber);
//}