package projeto_clinica.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.PrescricaoItem;

public interface PrescricaoItemRepository extends JpaRepository<PrescricaoItem, Long> {

        @Query("""
                        select pi from PrescricaoItem pi
                        join fetch pi.prescricao
                        join fetch pi.prescricao.paciente
                        join fetch pi.prescricao.medico
                        where pi.id = :id
                        """)
        Optional<PrescricaoItem> findByIdWithPrescricao(@Param("id") Long id);

        @Query("""
                        select pi from PrescricaoItem pi
                        join fetch pi.prescricao
                        join fetch pi.prescricao.paciente
                        join fetch pi.prescricao.medico
                        where pi.prescricao.id = :prescricaoId
                        """)
        List<PrescricaoItem> findByPrescricaoIdWithPrescricao(@Param("prescricaoId") Long prescricaoId);
}
