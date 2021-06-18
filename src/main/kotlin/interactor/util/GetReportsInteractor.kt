package interactor.util

import repository.repository.ReportsRepository

class GetReportsInteractor(private val reportsRepository: ReportsRepository) {

    fun execute(): List<String> {
        return reportsRepository.reports.takeLast(10).map { it.text }
    }
}