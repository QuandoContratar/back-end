package project.api.app.match


//data class CandidateDTO(
//    val id: Int,
//    val name: String,
//    val email: String?,
//    val birthDate: String?,
//    val city: String?,
//    val state: String?
//) {
//    companion object {
//        fun from(c: Candidate): project.api.app.match.CandidateDTO {
//            return CandidateDTO(
//                id = c.idCandidate ?: 0,
//                name = c.name ?: "",
//                email = c.email,
//                birthDate = c.birth?.toString(),
//                city = null, // se tiver cidade no profile, mapeia aqui
//                state = c.state
//            )
//        }
//    }
//}
//
//data class VacancyDTO(
//    val id: Int,
//    val title: String?,
//    val area: String?,
//    val workModel: WorkModel?,
//    val location: String?
//) {
//    companion object {
//        fun from(v: Vacancy): VacancyDTO {
//            return VacancyDTO(
//                id = v.id ?: 0,
//                title = v.position_job,
//                area = v.area,
//                workModel = v.workModel,
//                location = v.location
//            )
//        }
//    }
//}
//
//
//data class CandidateMatchDTO(
//    val candidateId: Int,
//    val candidateName: String,
//    val vacancyId: Int,
//    val vacancyJob: String,
//    val score: Double,
//    val matchLevel: MatchLevel
//) {
//
//
//companion object {
//
//    // seu método atual (não mexe!)
//    fun from(candidate: Candidate, vacancy: Vacancy, score: Double, matchLevel: MatchLevel): CandidateMatchDTO {
//        return CandidateMatchDTO(
//            candidateId = candidate.idCandidate ?: 0,
//            candidateName = candidate.name ?: "",
//            vacancyId = vacancy.id ?: 0,
//            vacancyJob = vacancy.position_job ?: "",
//            score = score,
//            matchLevel = matchLevel
//        )
//    }
//
//    // novo método — resolve seu erro
//    fun from(match: CandidateMatch): CandidateMatchDTO {
//        return CandidateMatchDTO(
//            candidateId = match.candidate.idCandidate ?: 0,
//            candidateName = match.candidate.name ?: "",
//            vacancyId = match.vacancy.id ?: 0,
//            vacancyJob = match.vacancy.position_job ?: "",
//            score = match.score,
//            matchLevel = match.matchLevel
//        )
//    }
//}}

data class CandidateMatchDTO(
    val matchId: Int,
    val candidateId: Int,
    val candidateName: String,
    val vacancyId: Int,
    val vacancyJob: String,
    val score: Double,
    val matchLevel: MatchLevel,
    val managerName: String,
    val status: String
) {
    companion object {

        fun from(match: CandidateMatch): CandidateMatchDTO {
            return CandidateMatchDTO(
                matchId = match.id ?: 0,
                candidateId = match.candidate.idCandidate ?: 0,
                candidateName = match.candidate.name ?: "",
                vacancyId = match.vacancy.id ?: 0,
                vacancyJob = match.vacancy.position_job ?: "",
                managerName = match.vacancy.manager?.name ?: "Não informado",   // <-- pegar gestor corretamente
                score = match.score,
                matchLevel = match.matchLevel,
                status = match.status.name.lowercase()
            )
        }
    }
}




//    companion object {
//        fun from(candidate: Candidate, vacancy: Vacancy, score: Double, matchLevel: MatchLevel): CandidateMatchDTO {
//            return CandidateMatchDTO(
//                candidateId = candidate.idCandidate ?: 0,
//                candidateName = candidate.name ?: "",
//                vacancyId = vacancy.id ?: 0,
//                vacancyJob = vacancy.position_job ?: "",
//                score = score,
//                matchLevel = matchLevel
//            )
//        }
//    }
//}

