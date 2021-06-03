package com.example.demo.ProjectList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project-list")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProjectListController {

    private final ProjectListService projectListService;

    @Autowired
    public ProjectListController(ProjectListService projectListService) {
        this.projectListService = projectListService;
    }

    @GetMapping
    public List<ProjectList> getProjectList(){
        return projectListService.getProjectList();
    }

    @GetMapping(path = "/rating")
    public List<ProjectList> getRatingByProject(){
        return projectListService.findAllOrderByRatingAsc();
    }

    @GetMapping(path = "/date")
    public List<ProjectList> getDateByProject(){
        return projectListService.findAllOrderByDateAsc();
    }

    @PostMapping
    public void addNewProject(@RequestBody ProjectList projectList){
        ProjectListService.addNewProjectList(projectList);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId){
        projectListService.deleteProject(projectId);
    }

    @PutMapping(path="{projectId}")
    public void updateProject(@PathVariable("projectId") Long projectId,@RequestParam(required = false) String name, @RequestParam(required = false) String description){
        projectListService.updateProject(projectId,name,description);
    }



}
