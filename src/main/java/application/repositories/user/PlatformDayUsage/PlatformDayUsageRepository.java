package application.repositories.user.PlatformDayUsage;

import application.model.PlatformsDayUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface PlatformDayUsageRepository extends MongoRepository<PlatformsDayUsage, String> {

//    @Query("{ date : { $gte : { $date : 2015-05-16T07:55:23.257Z}},  $lte : { $date : 2015-05-16T07:55:23.257Z}}}")
    public PlatformsDayUsage findOneByDay(Date date);


    public Collection<PlatformsDayUsage> findByDay(Date date);


}