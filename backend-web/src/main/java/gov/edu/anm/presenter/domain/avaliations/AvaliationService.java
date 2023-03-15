package gov.edu.anm.presenter.domain.avaliations;

import java.util.List;

public interface AvaliationService {
    Avaliation findById(Long userId, Long teamId);
    List<Avaliation> findAll();
    Avaliation addAvaliationToTeam(Long userId,Long teamId, Double value);
    void deleteAvaliation(Long userId, Long teamId);

}
