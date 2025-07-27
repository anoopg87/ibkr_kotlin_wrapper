package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's ContractDetails model.
 */
data class ContractDetailsKT(
    val contract: ContractKT,
    val marketName: String?,
    val minTick: Double?,
    val orderTypes: String?,
    val validExchanges: String?,
    val priceMagnifier: Int?,
    val underConId: Int?,
    val longName: String?,
    val contractMonth: String?,
    val industry: String?,
    val category: String?,
    val subcategory: String?,
    val timeZoneId: String?,
    val tradingHours: String?,
    val liquidHours: String?,
    val evRule: String?,
    val evMultiplier: Double?,
    val secIdList: List<TagValueKT>?,
    val cusip: String?,
    val ratings: String?,
    val descAppend: String?,
    val bondType: String?,
    val couponType: String?,
    val callable: Boolean?,
    val putable: Boolean?,
    val coupon: Double?,
    val convertible: Boolean?,
    val maturity: String?,
    val issueDate: String?,
    val nextOptionDate: String?,
    val nextOptionType: String?,
    val nextOptionPartial: Boolean?,
    val notes: String?
)
