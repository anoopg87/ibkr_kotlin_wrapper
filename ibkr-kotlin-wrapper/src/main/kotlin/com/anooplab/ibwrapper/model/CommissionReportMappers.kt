package com.anooplab.ibwrapper.model

import com.ib.client.CommissionReport

fun CommissionReport.toKT(): CommissionReportKT = CommissionReportKT(
    execId = execId(),
    commission = commission(),
    currency = currency(),
    realizedPNL = realizedPNL(),
    yieldValue = yield(),
    yieldRedemptionDate = yieldRedemptionDate()
)

fun CommissionReportKT.toIbApi(): CommissionReport = CommissionReport().apply {
    execId(execId ?: "")
    commission(commission ?: 0.0)
    currency(currency ?: "")
    realizedPNL(realizedPNL ?: 0.0)
    yield(yieldValue ?: 0.0)
    yieldRedemptionDate(yieldRedemptionDate ?: 0)
}
