package com.anooplab.ibwrapper.model

import com.ib.client.FamilyCode

fun FamilyCode.toKT(): FamilyCodeKT = FamilyCodeKT(
    accountID = accountID(),
    familyCodeStr = familyCodeStr()
)

fun FamilyCodeKT.toIbApi(): FamilyCode = FamilyCode().apply {
    accountID(accountID ?: "")
    familyCodeStr(familyCodeStr ?: "")
}
