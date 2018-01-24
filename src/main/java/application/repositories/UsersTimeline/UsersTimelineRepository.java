package application.repositories.UsersTimeline;

import application.model.TimeUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface UsersTimelineRepository extends MongoRepository<TimeUsage, String> {
//  @Query("{ date : { $gte : { $date : 2015-05-16T07:55:23.257Z}},  $lte : { $date : 2015-05-16T07:55:23.257Z}}}")
    public TimeUsage findOneByDate(Date date);

    public TimeUsage findOneByDateAndVendor(Date date, String vendor);

    @Query(value = "{'date':{ $gte: ?0, $lte: ?1}}")
    public Collection<TimeUsage> findByDateBetween(Date from, Date to);

    public Collection<TimeUsage> findByVendor(String vendor);

    Long removeByDateAndVendor(Date date, String vendor);
}