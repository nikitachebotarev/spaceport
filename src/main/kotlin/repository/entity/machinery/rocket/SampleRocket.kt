package repository.entity.machinery.rocket

import repository.entity.machinery.propulsion.*

data class SampleRocket(
    val nozzle: Nozzle,
    val fuelTank: Tank,
    val oxidizerTank: Tank,
    val fuelPump: Pump,
    val oxidizerPump: Pump,
    val chamber: CombustionChamber,
    val fuel: Fuel,
    val oxidizer: Oxidizer
)