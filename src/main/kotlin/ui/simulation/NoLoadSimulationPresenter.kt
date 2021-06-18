package ui.simulation

import interactor.port.NoLoadSimulationInteractor
import interactor.util.GetReportsInteractor
import ui.base.BasePresenter
import java.lang.IllegalStateException

class NoLoadSimulationPresenter(
    private val noLoadSimulationInteractor: NoLoadSimulationInteractor,
    private val getReportsInteractor: GetReportsInteractor
) : BasePresenter<NoLoadSimulationPresenter.Action, NoLoadSimulationPresenter.Result> {

    private var state = State.IDLE

    override fun action(action: Action): Result {
        when (action) {
            Action.START -> {
                if (state == State.IDLE
                    || state == State.RESULT
                ) {
                    noLoadSimulationInteractor.execute()
                    val reports = getReportsInteractor.execute()
                    return Result.Success(reports)
                } else {
                    throw IllegalStateException()
                }
            }
            Action.RETRY -> {
                if (state == State.IDLE
                    || state == State.RESULT
                ) {
                    noLoadSimulationInteractor.execute()
                    val reports = getReportsInteractor.execute()
                    return Result.Success(reports)
                } else {
                    throw IllegalStateException()
                }
            }
        }
    }

    enum class Action {
        START, RETRY
    }

    sealed class Result {
        class Success(val reports: List<String>) : Result()
        class Error(val error: String) : Result()
    }

    private enum class State {
        IDLE, RESULT
    }
}