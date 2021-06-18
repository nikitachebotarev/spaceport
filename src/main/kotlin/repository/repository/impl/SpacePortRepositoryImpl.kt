package repository.repository.impl

import repository.entity.machinery.propulsion.*
import repository.entity.machinery.rocket.SampleRocket
import repository.entity.materials.Material
import repository.repository.SpacePortRepository

class SpacePortRepositoryImpl : SpacePortRepository {

    override var rocket: SampleRocket = SampleRocket(
        Nozzle(1, 0),
        Tank(100, 100),
        Tank(100, 100),
        Pump(1),
        Pump(1),
        CombustionChamber(1, Material(1)),
        Fuel(1f),
        Oxidizer(1f)
    )
}