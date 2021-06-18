package repository.repository

import repository.entity.util.Report

interface ReportsRepository {
    val reports: MutableList<Report>
}
