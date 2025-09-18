package project.api.app.users.data

enum class LevelAccess(val access: String) {
    ADMIN("administrator"),
    HR("recursos humanos"),
    MANAGER("manager")
}