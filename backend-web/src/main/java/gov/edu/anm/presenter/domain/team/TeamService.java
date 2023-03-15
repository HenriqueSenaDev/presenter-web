package gov.edu.anm.presenter.domain.team;

import gov.edu.anm.presenter.api.common.dtos.team.TeamUpdateDto;
import gov.edu.anm.presenter.domain.avaliations.Avaliation;
import gov.edu.anm.presenter.domain.event.Event;

import java.util.List;

public interface TeamService {
    Team findById(Long id);

    List<Team> findAll();

    List<Avaliation> findTeamAvaliations(Long id);

    Event findTeamEvent(Long id);

    Team updateTeam(TeamUpdateDto teamUpdateDto, Long id);

    void deleteTeam(Long id);
}
