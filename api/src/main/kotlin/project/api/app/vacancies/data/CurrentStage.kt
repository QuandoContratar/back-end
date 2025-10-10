package project.api.app.vacancies.data

enum class CurrentStage(val stage: String) {
    WAITING_SCREENING("Aguardando triagem"),
    INITIAL_SCREENING("Triagem inicial"),
    CULTURAL_FIT_ASSESSMENT("Avaliação de fit cultural"),
    TECHNICAL_TEST("Teste técnico"),
    TECHNICAL_INTERVIEW("Entrevista técnica"),
    FINAL_INTERVIEW("Entrevista final"),
    OFFER_AND_CLOSING("Proposta e fechamento"),
    HIRING("Contratação")
}