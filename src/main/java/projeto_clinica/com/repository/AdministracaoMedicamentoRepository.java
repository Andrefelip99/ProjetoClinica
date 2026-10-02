package projeto_clinica.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto_clinica.com.model.AdministracaoMedicamento;

public interface AdministracaoMedicamentoRepository
                extends JpaRepository<AdministracaoMedicamento, Long> {

        @Query("""
                        select a from AdministracaoMedicamento a
                        join fetch a.prescricaoItem
                        join fetch a.funcionario
                        where a.id = :id
                        """)
        Optional<AdministracaoMedicamento> findByIdWithAssociations(@Param("id") Long id);

        @Query("""
                        select a from AdministracaoMedicamento a
                        join fetch a.prescricaoItem
                        join fetch a.funcionario
                        where a.prescricaoItem.id = :prescricaoItemId
                        """)
        List<AdministracaoMedicamento> findByPrescricaoItemIdWithAssociations(
                        @Param("prescricaoItemId") Long prescricaoItemId);
}
