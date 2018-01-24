package application.repositories.LastUpdates;

import application.model.LastUpdates;
import application.model.PlatformsDayUsage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface LastUpdatesRepository extends MongoRepository<LastUpdates, String> {
    public LastUpdates findOneByVendor(String vendor);

    Long removeByVendor(String vendor);
}