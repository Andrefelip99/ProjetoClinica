package projeto_clinica.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import projeto_clinica.com.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

        @Query("select c from Consulta c join fetch c.paciente join fetch c.medico where c.id = :id")
        java.util.Optional<Consulta> findByIdWithPacienteAndMedico(@Param("id") Long id);

        @Query("select c from Consulta c join fetch c.paciente join fetch c.medico where c.paciente.id = :pacienteId")
        List<Consulta> findByPacienteId(@Param("pacienteId") Long pacienteId);

        @Query("select c from Consulta c join fetch c.paciente join fetch c.medico where c.medico.id = :medicoId")
        List<Consulta> findByMedico_Id(@Param("medicoId") Long medicoId);

        boolean existsByMedico_IdAndDataHoraAndStatusNot(Long medicoId, java.time.LocalDateTime dataHora,
                        projeto_clinica.com.model.enums.StatusConsulta status);

        boolean existsByMedico_IdAndDataHoraAndStatusNotAndIdNot(Long medicoId, java.time.LocalDateTime dataHora,
                        projeto_clinica.com.model.enums.StatusConsulta status, Long consultaId);

}
