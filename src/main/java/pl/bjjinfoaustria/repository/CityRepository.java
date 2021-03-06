package pl.bjjinfoaustria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.bjjinfoaustria.entity.City;
@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	
	@Query(value="select * from cities c where c.region_id = :regionId" ,nativeQuery = true)
	public List<City> findCitiesByRegionId(@Param("regionId") long regionId);

}
