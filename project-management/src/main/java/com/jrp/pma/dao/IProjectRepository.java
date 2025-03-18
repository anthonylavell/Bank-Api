package com.jrp.pma.dao;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IProjectRepository extends PagingAndSortingRepository<Project, Long> {

    @Override
    public List<Project> findAll();

    @Query(nativeQuery =true, value = "SELECT stage as label, COUNT(*) as value "+
            "FROM project " +
            "GROUP BY stage")
    public List<ChartData> getProjectStatus();
}
