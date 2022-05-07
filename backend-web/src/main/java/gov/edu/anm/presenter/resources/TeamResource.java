package gov.edu.anm.presenter.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.edu.anm.presenter.entities.Team;
import gov.edu.anm.presenter.repositories.TeamRepository;

@RestController
@RequestMapping(value = "/teams")
public class TeamResource {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<List<Team>> findAll() {
        return ResponseEntity.ok().body(teamRepository.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(teamRepository.findById(id).get());
    }

}
