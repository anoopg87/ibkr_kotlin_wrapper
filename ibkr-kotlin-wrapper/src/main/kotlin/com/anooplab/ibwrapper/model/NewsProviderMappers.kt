package com.anooplab.ibwrapper.model

import com.ib.client.NewsProvider

fun NewsProvider.toKT(): NewsProviderKT = NewsProviderKT(
    code = providerCode(),
    name = providerName()
)

fun NewsProviderKT.toIbApi(): NewsProvider = NewsProvider(
    code ?: "",
    name ?: ""
)
