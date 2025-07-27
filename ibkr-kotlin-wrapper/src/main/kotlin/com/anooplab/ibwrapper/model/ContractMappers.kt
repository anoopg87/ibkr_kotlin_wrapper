import com.anooplab.ibwrapper.model.ContractKT
import com.ib.client.Contract

fun Contract.toKT(): ContractKT = ContractKT(
    conId = conid(),
    symbol = symbol(),
    secType = secType()?.toString(),
    lastTradeDateOrContractMonth = lastTradeDateOrContractMonth(),
    strike = strike(),
    right = right()?.toString(),
    multiplier = multiplier(),
    exchange = exchange(),
    primaryExch = primaryExch(),
    currency = currency(),
    localSymbol = localSymbol(),
    tradingClass = tradingClass(),
    includeExpired = includeExpired(),
    secIdType = secIdType()?.toString(),
    secId = secId()
)


fun ContractKT.toIbApi(): Contract = Contract().apply {
    conid(conId ?: 0)
    symbol(symbol ?: "")
    secType(secType ?: "")
    lastTradeDateOrContractMonth(lastTradeDateOrContractMonth ?: "")
    strike(strike ?: 0.0)
    right(right ?: "")
    multiplier(multiplier ?: "")
    exchange(exchange ?: "")
    primaryExch(primaryExch ?: "")
    currency(currency ?: "")
    localSymbol(localSymbol ?: "")
    tradingClass(tradingClass ?: "")
    includeExpired(includeExpired ?: false)
    secIdType(secIdType ?: "")
    secId(secId ?: "")
}
