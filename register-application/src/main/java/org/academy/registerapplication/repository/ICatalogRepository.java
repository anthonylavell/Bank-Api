package org.academy.registerapplication.repository;

import org.academy.registerapplication.entity.CourseCatalog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalogRepository extends PagingAndSortingRepository<CourseCatalog,Long> {
    @Override
    public List<CourseCatalog> findAll();
    public CourseCatalog findByCourseCode(String code);
}
