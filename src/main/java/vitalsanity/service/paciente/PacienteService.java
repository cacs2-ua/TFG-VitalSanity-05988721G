package vitalsanity.service.paciente;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitalsanity.dto.paciente.BuscarPacienteResponse;
import vitalsanity.dto.paciente.PacienteData;
import vitalsanity.model.Paciente;
import vitalsanity.model.Usuario;
import vitalsanity.repository.PacienteRepository;
import vitalsanity.repository.UsuarioRepository;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PacienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Metodo para buscar paciente por nifNie (ignora mayusculas/minusculas)
    public BuscarPacienteResponse buscarPacientePorNifNie(String nifNie) {
        if (nifNie == null || nifNie.trim().isEmpty()) {
            return null;
        }
        List<Usuario> usuarios = usuarioRepository.findByNifNie(nifNie);
        for (Usuario usuario : usuarios) {
            if (usuario.getNifNie().equalsIgnoreCase(nifNie)
                    && usuario.getPaciente() != null
                    && usuario.getTipo() != null
                    && usuario.getTipo().getId().equals(4L)) {
                Paciente paciente = usuario.getPaciente();
                BuscarPacienteResponse response = new BuscarPacienteResponse();
                response.setId(usuario.getPaciente().getId());
                response.setNombreCompleto(usuario.getNombreCompleto());
                response.setNifNie(usuario.getNifNie());
                response.setGenero(paciente.getGenero());
                // Se asume que la fecha de nacimiento esta en formato "yyyy-MM-dd"
                try {
                    LocalDate fechaNacimiento = LocalDate.parse(paciente.getFechaNacimiento(), DateTimeFormatter.ISO_LOCAL_DATE);
                    int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
                    response.setEdad(edad);
                } catch (Exception e) {
                    response.setEdad(0);
                }
                return response;
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public PacienteData encontrarPorId(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        if (paciente == null) return null;
        else return modelMapper.map(paciente, PacienteData.class);
    }
}
