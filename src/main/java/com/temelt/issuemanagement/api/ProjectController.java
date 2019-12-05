package com.temelt.issuemanagement.api;

import com.temelt.issuemanagement.dto.ProjectDto;
import com.temelt.issuemanagement.repository.ProjectRepository;
import com.temelt.issuemanagement.service.impl.ProjectServiceImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {
    /*
     *
     * Http Methodları
     * GET
     * POST
     * PUT
     * DELETE
     */
    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable("id") Long id) {
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);

    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid  @RequestBody ProjectDto projectDto){
        return ResponseEntity.ok(projectServiceImpl.save(projectDto));
    }
   // @RequestMapping(path = "update",method = RequestMethod.PUT) uzun yol
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") Long id,@Valid @RequestBody ProjectDto projectDto){
        //SOLID SINGLE RESPONSIBILITY HER İŞ SADECE YÜKLEYİCİSİ OLAN İŞİ YÜKLEMELİ ÖRN SAVE METODU İÇİN HEM UPDATE HEM İNSERT YAPILABİLİRKEN YAPMAMALIYIZ

       return ResponseEntity.ok( projectServiceImpl.update(id,projectDto));
    }


}
