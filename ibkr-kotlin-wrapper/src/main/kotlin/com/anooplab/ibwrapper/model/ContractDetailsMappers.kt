package com.anooplab.ibwrapper.model

import com.ib.client.ContractDetails
import com.anooplab.ibwrapper.model.ContractDetailsKT
import com.anooplab.ibwrapper.model.TagValueKT
import toIbApi
import toKT

fun ContractDetails.toKT(): ContractDetailsKT = ContractDetailsKT(
    contract = contract().toKT(),
    marketName = marketName(),
    minTick = minTick(),
    orderTypes = orderTypes(),
    validExchanges = validExchanges(),
    priceMagnifier = priceMagnifier(),
    underConId = underConid(),
    longName = longName(),
    contractMonth = contractMonth(),
    industry = industry(),
    category = category(),
    subcategory = subcategory(),
    timeZoneId = timeZoneId(),
    tradingHours = tradingHours(),
    liquidHours = liquidHours(),
    evRule = evRule(),
    evMultiplier = evMultiplier(),
    secIdList = secIdList()?.map { TagValueKT(it.m_tag, it.m_value) },
    cusip = cusip(),
    ratings = ratings(),
    descAppend = descAppend(),
    bondType = bondType(),
    couponType = couponType(),
    callable = callable(),
    putable = putable(),
    coupon = coupon(),
    convertible = convertible(),
    maturity = maturity(),
    issueDate = issueDate(),
    nextOptionDate = nextOptionDate(),
    nextOptionType = nextOptionType(),
    nextOptionPartial = nextOptionPartial(),
    notes = notes()
)

fun ContractDetailsKT.toIbApi(): ContractDetails = ContractDetails().apply {
    contract(contract.toIbApi())
    marketName(marketName ?: "")
    minTick(minTick ?: 0.0)
    orderTypes(orderTypes ?: "")
    validExchanges(validExchanges ?: "")
    priceMagnifier(priceMagnifier ?: 0)
    underConid(underConId ?: 0)
    longName(longName ?: "")
    contractMonth(contractMonth ?: "")
    industry(industry ?: "")
    category(category ?: "")
    subcategory(subcategory ?: "")
    timeZoneId(timeZoneId ?: "")
    tradingHours(tradingHours ?: "")
    liquidHours(liquidHours ?: "")
    evRule(evRule ?: "")
    evMultiplier(evMultiplier ?: 0.0)
    secIdList(secIdList?.map { it.toIbApi() })
    cusip(cusip ?: "")
    ratings(ratings ?: "")
    descAppend(descAppend ?: "")
    bondType(bondType ?: "")
    couponType(couponType ?: "")
    callable(callable ?: false)
    putable(putable ?: false)
    coupon(coupon ?: 0.0)
    convertible(convertible ?: false)
    maturity(maturity ?: "")
    issueDate(issueDate ?: "")
    nextOptionDate(nextOptionDate ?: "")
    nextOptionType(nextOptionType ?: "")
    nextOptionPartial(nextOptionPartial ?: false)
    notes(notes ?: "")
}
