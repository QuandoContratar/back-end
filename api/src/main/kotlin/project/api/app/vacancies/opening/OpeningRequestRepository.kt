package project.api.app.vacancies.opening


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OpeningRequestRepository : JpaRepository<OpeningRequest, Int> {
    fun findByGestorId(gestorId: Int): List<OpeningRequest>
    fun findByStatus(status: OpeningStatus): List<OpeningRequest>

    @Query("""
        SELECT o FROM OpeningRequest o 
        WHERE LOWER(o.cargo) LIKE LOWER(CONCAT('%', :query, '%')) 
           OR LOWER(o.requisitos) LIKE LOWER(CONCAT('%', :query, '%')) 
           OR LOWER(o.gestor.name) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    fun search(@Param("query") query: String): List<OpeningRequest>
}