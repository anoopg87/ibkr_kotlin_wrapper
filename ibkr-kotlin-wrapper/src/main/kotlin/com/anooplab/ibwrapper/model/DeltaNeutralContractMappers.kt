package com.anooplab.ibwrapper.model

import com.ib.client.DeltaNeutralContract

fun DeltaNeutralContract.toKT(): DeltaNeutralContractKT = DeltaNeutralContractKT(
    conId = conid(),
    delta = delta(),
    price = price()
)

