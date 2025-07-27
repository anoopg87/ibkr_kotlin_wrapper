package com.anooplab.ibwrapper.model

import com.ib.client.Bar
import com.anooplab.ibwrapper.api.toDoubleSafe
import com.anooplab.ibwrapper.api.toLongSafe

fun Bar.toKT(): BarKT = BarKT(
    time = time(),
    open = open(),
    high = high(),
    low = low(),
    close = close(),
    volume = (volume() as com.ib.client.Decimal).toLongSafe(),
    count = count(),
    wap = (wap() as com.ib.client.Decimal).toDoubleSafe()
)