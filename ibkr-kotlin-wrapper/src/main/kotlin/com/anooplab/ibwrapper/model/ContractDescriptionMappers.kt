package com.anooplab.ibwrapper.model

import com.ib.client.ContractDescription
import toIbApi
import toKT

fun ContractDescription.toKT(): ContractDescriptionKT = ContractDescriptionKT(
    contract = contract()?.toKT(),
    derivativeSecTypes = derivativeSecTypes()?.toList()
)

fun ContractDescriptionKT.toIbApi(): ContractDescription = ContractDescription().apply {
    contract(contract?.toIbApi())
    derivativeSecTypes(derivativeSecTypes?.toTypedArray())
}
