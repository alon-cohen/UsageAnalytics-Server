package application.repositories.VerticalDayUsage;

import application.model.VerticalDayUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface VerticalDayUsageRepository extends MongoRepository<VerticalDayUsage, String> {

//  @Query("{ date : { $gte : { $date : 2015-05-16T07:55:23.257Z}},  $lte : { $date : 2015-05-16T07:55:23.257Z}}}")
    public VerticalDayUsage findOneByDate(Date date);

    public VerticalDayUsage findOneByDateAndVendor(Date date, String vendor);

    @Query(value = "{'date':{ $gte: ?0, $lte: ?1}}")
    public Collection<VerticalDayUsage> findByDateBetween(Date from, Date to);

    Long removeByDateAndVendor(Date date, String vendor);

}