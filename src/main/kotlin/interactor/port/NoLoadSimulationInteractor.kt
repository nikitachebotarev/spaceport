package interactor.port

import repository.entity.util.Report
import func.physics.CombustionChamberExplosionFunction
import func.physics.CombustionFunction
import func.physics.PumpFunction
import repository.repository.MessagesRepository
import repository.repository.ReportsRepository
import repository.repository.SpacePortRepository

class NoLoadSimulationInteractor(
    private val spacePortRepository: SpacePortRepository,
    private val messagesRepository: MessagesRepository,
    private val reportsRepository: ReportsRepository,
    private val combustionFunction: CombustionFunction,
    private val pumpFunction: PumpFunction,
    private val combustionChamberExplosionFunction: CombustionChamberExplosionFunction
) {

    fun execute() {
        var rocket = spacePortRepository.rocket
        while (rocket.fuelTank.amount > 0) {
            val pumpedFuel = pumpFunction.evaluate(rocket.fuelPump, rocket.fuelTank)
            val pumpedOxidizer = pumpFunction.evaluate(rocket.oxidizerPump, rocket.oxidizerTank)
            val exhaust = combustionFunction.evaluate(rocket.fuel, rocket.oxidizer, pumpedFuel, pumpedOxidizer)
            if (combustionChamberExplosionFunction.evaluate(exhaust, rocket.chamber)) {
                reportsRepository.reports.add(Report(messagesRepository.chamberBlowUpMessage))
                return
            } else {
                rocket = rocket.copy(
                    fuelTank = rocket.fuelTank.copy(amount = rocket.fuelTank.amount - pumpedFuel),
                    oxidizerTank = rocket.oxidizerTank.copy(amount = rocket.oxidizerTank.amount - pumpedOxidizer)
                )
            }
        }
        reportsRepository.reports.add(Report(messagesRepository.noLoadSimulationCompleteMessage))
    }
}