package webbrain.incomeexpenseapp.service.implement;

import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.MeasurementCreateDto;
import webbrain.incomeexpenseapp.entity.Measurement;
import webbrain.incomeexpenseapp.exception.MeasurementNotFoundException;
import webbrain.incomeexpenseapp.repository.MeasurementRepository;
import webbrain.incomeexpenseapp.service.MeasurementService;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public Measurement save(MeasurementCreateDto dto) {
        Measurement measurement = new Measurement(
                dto.getName());
        measurementRepository.save(measurement);
        return measurement;
    }

    @Override
    public List<Measurement> findAll() {
        List<Measurement> findAllMeasurement = measurementRepository.findAll();
        return findAllMeasurement;
    }

    @Override
    public Measurement findById(Long id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            throw new MeasurementNotFoundException("Such measurement id {" + id + "}");
        Measurement measurement = optionalMeasurement.get();
        return measurement;
    }

    @Override
    public Measurement edit(Long id, MeasurementCreateDto dto) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            throw new MeasurementNotFoundException("Such measurement id {" + id + "}");
        Measurement measurement = optionalMeasurement.get();
        if(dto.getName() != null && !dto.getName().equals(measurement.getName())){
            measurement.setName(dto.getName());
        }
        return measurement;
    }

    @Override
    public void delete(Long id) {
        measurementRepository.deleteById(id);
    }
}
