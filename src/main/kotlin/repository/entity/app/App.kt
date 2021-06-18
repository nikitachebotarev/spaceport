package repository.entity.app

import repository.repository.MessagesRepository
import repository.repository.ReportsRepository
import repository.repository.SpacePortRepository

data class App(
    val spacePortRepository: SpacePortRepository,
    val messagesRepository: MessagesRepository,
    val reportsRepository: ReportsRepository
)
