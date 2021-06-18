package func.physics

import repository.entity.machinery.propulsion.Exhaust
import repository.entity.machinery.propulsion.Fuel
import repository.entity.machinery.propulsion.Oxidizer
import kotlin.math.roundToInt

class CombustionFunction {

    fun evaluate(fuel: Fuel, oxidizer: Oxidizer, fuelAmount: Int, oxidizerAmount: Int): Exhaust {
        val energy = ((fuelAmount * fuel.effectivity) * (oxidizer.effectivity * oxidizerAmount)).roundToInt()
        return Exhaust(energy)
    }
}
