package func.physics

import repository.entity.machinery.propulsion.Pump
import repository.entity.machinery.propulsion.Tank

class PumpFunction {

    fun evaluate(pump: Pump, tank: Tank): Int {
        val pumpedAmount = pump.power
        return if (tank.amount > pumpedAmount) {
            pumpedAmount
        } else {
            return tank.amount
        }
    }
}