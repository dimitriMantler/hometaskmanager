package de.martin701.hometaskmanager.repository;

import de.martin701.hometaskmanager.entity.TempTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempTableRepository extends JpaRepository<TempTable, Long>{

}
