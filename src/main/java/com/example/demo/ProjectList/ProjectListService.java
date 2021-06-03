package com.example.demo.ProjectList;

import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProjectListService {

    private static ProjectRepository projectRepository;
//    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectListService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectList> getProjectList(){
        return projectRepository.findAll();
    }

    public static void addNewProjectList(ProjectList projectList) {
        Optional<ProjectList> projectListOptional = projectRepository.findProjectListByName(projectList.getName());
        if(projectListOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        projectRepository.save(projectList);
    }


    public void deleteProject(Long projectId) {
               boolean exists = projectRepository.existsById(projectId);
               if(!exists){
                   throw new IllegalStateException("project with id" + projectId + "does not exists");
               }
               projectRepository.deleteById(projectId);
    }

    @Transactional
    public void updateProject(Long projectId, String name, String description) {
        ProjectList projectList = projectRepository.findById(projectId).orElseThrow(()->new IllegalStateException("project with id" + projectId + "does not exist"));

        if(name != null && name.length()>0 && !Objects.equals(projectList.getName(),name)){
            projectList.setName(name);
        }
        if(description != null && description.length()>0 && !Objects.equals(projectList.getDescription(),description)){
            Optional<ProjectList>projectListOptional = projectRepository.findProjectListByName(description);
            if(projectListOptional.isPresent()){
                throw new IllegalStateException("description taken");
            }
            projectList.setDescription(description);

        }

    }

    public List<ProjectList> findAllOrderByRatingAsc() {
        return projectRepository.findAllOrderByRatingAsc();
    }

    public List<ProjectList> findAllOrderByDateAsc() {
        return projectRepository.findAllOrderByDateAsc();
    }
}
