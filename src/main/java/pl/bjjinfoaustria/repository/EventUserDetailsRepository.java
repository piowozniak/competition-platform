package pl.bjjinfoaustria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.bjjinfoaustria.entity.Competitor;

public interface EventUserDetailsRepository extends JpaRepository<Competitor, Long>{

}
