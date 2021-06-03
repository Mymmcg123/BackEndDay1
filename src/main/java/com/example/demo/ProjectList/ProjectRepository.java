package com.example.demo.ProjectList;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectList,Long> {
    @Query("SELECT s FROM ProjectList s WHERE s.name = ?1")
    Optional<ProjectList> findProjectListByName(String name);

    @Query("FROM ProjectList ORDER BY rating ASC")
    List<ProjectList>findAllOrderByRatingAsc();

    @Query("FROM ProjectList ORDER BY date ASC")
    List<ProjectList>findAllOrderByDateAsc();
}


