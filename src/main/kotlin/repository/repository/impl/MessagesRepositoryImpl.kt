package repository.repository.impl

import repository.repository.MessagesRepository

class MessagesRepositoryImpl() : MessagesRepository {

    override val chamberBlowUpMessage: String
        get() = listOf(
            "We got chamber blew up. Aborting test. Over.",
            "Unfortunately chamber was blow up. We must stop the test.",
            "Fire in the propulsion system, its seems like chamber blew up."
        ).random()

    override val noLoadSimulationCompleteMessage: String
        get() = listOf(
            "Okay. We running out of fuel. All systems stable. Test complete."
        ).random()

}
