package resources.accessingdatapostgressql.database;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import resources.accessingdatapostgressql.database.driverlocation;

import java.util.ArrayList;
import java.util.Collection;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LocationRepository extends CrudRepository<driverlocation, Integer> {
    @Query(value = "select * from driverlocation2 where id = ?1", nativeQuery = true)
    ArrayList<driverlocation> findUserUsingId(int id);

//    @Query(value = "select * from driverlocation where h3index = '/x618144247449124863'", nativeQuery = true)
    @Query(value = "select * from driverlocation1 where h3index in :indices", nativeQuery = true)
//    ArrayList<driverlocation> findUserUsingLongLat(@Param("indices")long indices);
    ArrayList<driverlocation> findUserUsingLongLat1(@Param("indices") Collection<Long> indices);
//    (16.950750946350603, 105.79485839786565, '/x89416eb0137ffff',1)
    @Query(value = "select * from driverlocation2 where h3index in :indices", nativeQuery = true)
    ArrayList<driverlocation> findUserUsingLongLat2(@Param("indices") Collection<Long> indices);
    @Query(value = "select * from driverlocation3 where h3index in :indices", nativeQuery = true)
    ArrayList<driverlocation> findUserUsingLongLat3(@Param("indices") Collection<Long> indices);

    @Transactional
    @Modifying
    @Query(value ="UPDATE driverlocation1 SET h3index = :index WHERE driverid = :id", nativeQuery = true)
    void UpdateLongLat1(@Param("index") Long h3, @Param("id") int id);

    @Transactional
    @Modifying
    @Query(value ="UPDATE driverlocation2 SET h3index = :index WHERE driverid = :id", nativeQuery = true)
    void UpdateLongLat2(@Param("index") Long h3, @Param("id") int id);

    @Transactional
    @Modifying
    @Query(value ="UPDATE driverlocation3 SET h3index = :index WHERE driverid = :id", nativeQuery = true)
    void UpdateLongLat3(@Param("index") Long h3, @Param("id") int id);
}
