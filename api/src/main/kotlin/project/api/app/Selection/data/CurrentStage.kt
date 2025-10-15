package project.api.app.Selection.data

enum class CurrentStage(val description: String) {
    aguardando_triagem("aguardando_triagem"),
    triagem_inicial("triagem_inicial"),
    avaliacao_fit_cultural("avaliacao_fit_cultural"),
    teste_tecnico("teste_tecnico"),
    entrevista_tecnica("entrevista_tecnica"),
    entrevista_final("entrevista_final"),
    proposta_fechamento("proposta_fechamento"),
    contratacao("contratacao")
}
