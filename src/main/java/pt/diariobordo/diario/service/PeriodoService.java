package pt.diariobordo.diario.service;

import org.springframework.stereotype.Service;
import pt.diariobordo.diario.dto.PeriodoDtoResponse;
import pt.diariobordo.diario.dto.PeriodoDtoResquest;
import pt.diariobordo.diario.entity.Periodo;
import pt.diariobordo.diario.repository.PeriodoRepository;

import java.util.List;

@Service
public class PeriodoService {
    private final PeriodoRepository periodoRepository;

    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    public Periodo novoPeriodo(PeriodoDtoResquest periodoDtoResquest) {
        Periodo periodo = new Periodo(periodoDtoResquest.dataInicio(), periodoDtoResquest.dataFim(), periodoDtoResquest.horaInicio(), periodoDtoResquest.horaFim());
        return periodoRepository.save(periodo);
    }

    public List<PeriodoDtoResponse> listaPeriodos() {
        return this.periodoRepository.findAll().stream().map(PeriodoDtoResponse::new).toList();
    }

    

}
