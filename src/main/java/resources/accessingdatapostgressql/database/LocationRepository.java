package resources.accessingdatapostgressql.database;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import resources.accessingdatapostgressql.database.driverlocation;

import java.util.ArrayList;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LocationRepository extends CrudRepository<driverlocation, Integer> {
    @Query(value = "select * from driverlocation where id = ?1", nativeQuery = true)
    ArrayList<driverlocation> findUserUsingId(int id);

//    @Query(value = "select * from driverlocation where h3index = '/x618144247449124863'", nativeQuery = true)
    @Query(value = "select * from driverlocation where h3index = ?1", nativeQuery = true)
    ArrayList<driverlocation> findUserUsingLongLat(byte[] indices);
//    ArrayList<driverlocation> findUserUsingLongLat(@Param("indices") Collection<String> indices);
//    (16.950750946350603, 105.79485839786565, '/x89416eb0137ffff',1)
}