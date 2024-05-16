package de.martin701.hometaskmanager.repositories;

import de.martin701.hometaskmanager.entities.TempTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempTableRepository extends JpaRepository<TempTable, Long>{

}
