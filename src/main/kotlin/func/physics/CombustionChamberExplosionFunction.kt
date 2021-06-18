package func.physics

import repository.entity.machinery.propulsion.CombustionChamber
import repository.entity.machinery.propulsion.Exhaust

class CombustionChamberExplosionFunction {

    fun evaluate(exhaust: Exhaust, combustionChamber: CombustionChamber): Boolean {
        return exhaust.energy > combustionChamber.thickness * combustionChamber.material.strength
    }
}