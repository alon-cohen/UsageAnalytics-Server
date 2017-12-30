package application.repositories.user.PlatformDayUsage;

import application.model.PlatformsDayUsage;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

public interface PlatformDayUsageRepository extends MongoRepository<PlatformsDayUsage, String> {

//    @Query("{ date : { $gte : { $date : 2015-05-16T07:55:23.257Z}},  $lte : { $date : 2015-05-16T07:55:23.257Z}}}")
    public PlatformsDayUsage findOneByDate(Date date);


    //public Collection<PlatformsDayUsage> findByDate(Date date);

    @Query(value = "{'date':{ $gte: ?0, $lte: ?1}}")
    public Collection<PlatformsDayUsage> findByDateBetween(Date from, Date to);


//    Query query = new Query(
//             Criteria.where("username").is(username)
//                    .andOperator(
//                            Criteria.where("start").lt(DateUtils.ceiling(date)),
//                            Criteria.where("start").gte(DateUtils.floor(date))
//                    )
//    );
//
//    Query query2 = new Query(Criteria.where("b").elemMatch(
//            Criteria.where("startDate").lte(date)
//                    .and("endDate").gte(date)
//    ));
//
//    @Query(value = query2)
//    public Collection<PlatformsDayUsage> findByDateBetween(Date from, Date to);
//



}