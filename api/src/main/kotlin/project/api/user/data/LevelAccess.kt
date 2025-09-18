package project.api.user.data

enum class LevelAccess(val access: String) {
    ADMIN("administrator"),
    HR("recursos humanos"),
    MANAGER("manager")
}