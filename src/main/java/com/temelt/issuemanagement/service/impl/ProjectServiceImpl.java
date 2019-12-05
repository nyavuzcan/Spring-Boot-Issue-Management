package com.temelt.issuemanagement.service.impl;

import com.temelt.issuemanagement.dto.ProjectDto;
import com.temelt.issuemanagement.entity.Project;
import com.temelt.issuemanagement.repository.ProjectRepository;
import com.temelt.issuemanagement.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper){
        this.projectRepository=projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto save(ProjectDto project) {
        //BUSINESS LOGİCLER BURADA KONTROL EDİLECEK. //VERİ TABANINDA AYNI PROJECT CODE DENK GELMEYECEK KONTROLÜ YAPILSIN
        Project projectCheck=projectRepository.getByProjectCode(project.getProjectCode());
        if(projectCheck!=null)
            throw new IllegalArgumentException("Project Code Already Exist");

        /*if(project.getProjectCode()==null){   *******DTO ÜZERİNDE @NOTNULL ANNN İLE YAPTIK. CONTROLLERE DÜŞMEDEN HATA DÖNECEK
            throw new IllegalArgumentException("Project code cannot be null");
        }*/
        Project p=modelMapper.map(project,Project.class);
        p=projectRepository.save(p);
        project.setId(p.getId());

        return project;
    }

    @Override
    public ProjectDto getById(Long id) {
        Project p=projectRepository.getOne(id);

        return modelMapper.map(p,ProjectDto.class);
    }

    @Override
    public Page<Project> getAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Project getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<Project> getByProjectContains(String projectCode) {
        return null;
    }

    @Override
    public Boolean delete(Project project) {
        projectRepository.delete(project);
        return Boolean.TRUE;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto projectDto) {
        Project projectDb=projectRepository.getOne(id);
        if(projectDb==null)
            throw new IllegalArgumentException("Project Does Not Exist Id:"+ id);
        Project projectCheck=projectRepository.getByprojectCodeAndIdNot(projectDto.getProjectCode(),id);
        if(projectCheck!=null)
            throw new IllegalArgumentException("Project Code Already Exist");
        projectDb.setProjectCode(projectDto.getProjectCode());
        projectDb.setProjectName(projectDto.getProjectName());

        projectRepository.save(projectDb);
        return  modelMapper.map(projectDb,ProjectDto.class);
    }

}
