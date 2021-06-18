package repository.repository.impl

import repository.entity.util.Report
import repository.repository.ReportsRepository

class ReportsRepositoryImpl() : ReportsRepository {

    override val reports: MutableList<Report> = mutableListOf()
}
