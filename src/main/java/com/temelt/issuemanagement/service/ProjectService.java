package com.temelt.issuemanagement.service;

import com.temelt.issuemanagement.dto.ProjectDto;
import com.temelt.issuemanagement.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto project);

    ProjectDto getById(Long id);

   Page<Project> getAllPageable(Pageable pageable);

  Project getByProjectCode(String projectCode);

   List<Project> getByProjectContains(String projectCode);


    Boolean delete(Project project);

    ProjectDto update(Long id, ProjectDto projectDto);
}
