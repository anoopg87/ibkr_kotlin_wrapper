package com.anooplab.ibwrapper.model

import com.ib.client.OrderState


fun OrderState.toKT(): OrderStateKT = OrderStateKT(
    status = status().toKT(),
    initMarginBefore = initMarginBefore(),
    maintMarginBefore = maintMarginBefore(),
    equityWithLoanBefore = equityWithLoanBefore(),
    initMarginChange = initMarginChange(),
    maintMarginChange = maintMarginChange(),
    equityWithLoanChange = equityWithLoanChange(),
    initMarginAfter = initMarginAfter(),
    maintMarginAfter = maintMarginAfter(),
    equityWithLoanAfter = equityWithLoanAfter(),
    commission = commission(),
    minCommission = minCommission(),
    maxCommission = maxCommission(),
    commissionCurrency = commissionCurrency(),
    warningText = warningText()
)
