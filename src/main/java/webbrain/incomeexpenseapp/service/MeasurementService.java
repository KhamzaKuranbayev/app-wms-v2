package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.MeasurementCreateDto;
import webbrain.incomeexpenseapp.entity.Measurement;

import java.util.List;

public interface MeasurementService {
    Measurement save(MeasurementCreateDto dto);

    List<Measurement> findAll();

    Measurement findById(Long id);

    Measurement edit(Long id, MeasurementCreateDto dto);

    void delete(Long id);
}
