package application.repositories.user.LastUpdatesRepository;

import application.model.LastUpdates;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LastUpdatesRepository extends MongoRepository<LastUpdates, String> {

}