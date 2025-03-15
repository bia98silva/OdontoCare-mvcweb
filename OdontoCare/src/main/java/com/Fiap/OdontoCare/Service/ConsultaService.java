package com.Fiap.OdontoCare.Service;

import com.Fiap.OdontoCare.DTO.ConsultaDTO;
import com.Fiap.OdontoCare.Entity.Consulta;
import com.Fiap.OdontoCare.Entity.Dentista;
import com.Fiap.OdontoCare.Entity.Paciente;
import com.Fiap.OdontoCare.Repository.ConsultaRepository;
import com.Fiap.OdontoCare.Repository.DentistaRepository;
import com.Fiap.OdontoCare.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    public long count() {
        return consultaRepository.count();
    }
    public List<Consulta> listarProximasConsultas() {
        return consultaRepository.findByDataConsultaAfter(LocalDate.now());
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta save(Consulta consultaDTO) {
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        Dentista dentista = dentistaRepository.findById(consultaDTO.getDentistaId())
                .orElseThrow(() -> new IllegalArgumentException("Dentista não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setStatus(consultaDTO.getStatus());
        consulta.setDetalhes(consultaDTO.getDetalhes());
        consulta.setPaciente(paciente);
        consulta.setDentista(dentista);

        return consultaRepository.save(consulta);
    }

    public Consulta update(ConsultaDTO consultaDTO) {
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        Dentista dentista = dentistaRepository.findById(consultaDTO.getDentistaId())
                .orElseThrow(() -> new IllegalArgumentException("Dentista não encontrado"));

        Consulta consulta = consultaRepository.findById(consultaDTO.getIdConsulta())
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setStatus(consultaDTO.getStatus());
        consulta.setDetalhes(consultaDTO.getDetalhes());
        consulta.setPaciente(paciente);
        consulta.setDentista(dentista);

        return consultaRepository.save(consulta);
    }


    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }


    public void update(Consulta consulta) {
        consultaRepository.save(consulta);
    }

}
