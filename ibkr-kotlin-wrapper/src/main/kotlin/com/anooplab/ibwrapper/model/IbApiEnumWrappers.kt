package com.anooplab.ibwrapper.model

import com.ib.client.OrderStatus
import com.ib.client.Types


// --- IB API Enum Kotlin Wrappers (KT) ---

// Action
enum class ActionKT(val ibValue: String) {
    BUY("BUY"),
    SELL("SELL"),
    SSHORT("SSHORT"),
    SSHORTX("SSHORTX"),
    UNKNOWN("");
    companion object {
        fun fromIb(value: String?): ActionKT = values().find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.Action?.toKT(): ActionKT = ActionKT.fromIb(this?.name)
fun ActionKT.toIbApi(): Types.Action? = Types.Action.values().find { it.name == this.ibValue }

// OrderType
enum class OrderTypeKT(val ibValue: String) {
    MKT("MKT"),
    LMT("LMT"),
    STP("STP"),
    STP_LMT("STP LMT"),
    UNKNOWN("");
    companion object {
        fun fromIb(value: String?): OrderTypeKT = values().find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.SecType?.toKT(): OrderTypeKT = OrderTypeKT.fromIb(this?.name)
fun OrderTypeKT.toIbApi(): Types.SecType? = Types.SecType.values().find { it.name == this.ibValue }

// TimeInForce
enum class TimeInForceKT(val ibValue: String) {
    DAY("DAY"),
    GTC("GTC"),
    IOC("IOC"),
    GTD("GTD"),
    OPG("OPG"),
    FOK("FOK"),
    DTC("DTC"),
    UNKNOWN("");
    companion object {
        fun fromIb(value: String?): TimeInForceKT = values().find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.TimeInForce?.toKT(): TimeInForceKT = TimeInForceKT.fromIb(this?.name)
fun TimeInForceKT.toIbApi(): Types.TimeInForce? = Types.TimeInForce.values().find { it.name == this.ibValue }

// Right
enum class RightKT(val ibValue: String) {
    CALL("C"),
    PUT("P"),
    UNKNOWN("");
    companion object {
        fun fromIb(value: String?): RightKT = values().find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.Right?.toKT(): RightKT = RightKT.fromIb(this?.name)
fun RightKT.toIbApi(): Types.Right? = Types.Right.values().find { it.name == this.ibValue }

// SecIdType
enum class SecIdTypeKT(val ibValue: String) {
    CUSIP("CUSIP"),
    SEDOL("SEDOL"),
    ISIN("ISIN"),
    RIC("RIC"),
    UNKNOWN("");
    companion object {
        fun fromIb(value: String?): SecIdTypeKT = values().find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.SecIdType?.toKT(): SecIdTypeKT = SecIdTypeKT.fromIb(this?.name)
fun SecIdTypeKT.toIbApi(): Types.SecIdType? = Types.SecIdType.values().find { it.name == this.ibValue }

// FADataType
enum class FADataTypeKT(val ibValue: Int) {
    GROUPS(1),
    PROFILE(2),
    ACCOUNT(3),
    UNKNOWN(-1);
    companion object {
        fun fromIb(value: Int?): FADataTypeKT = entries.find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.FADataType?.toKT(): FADataTypeKT = this?.ordinal?.let { FADataTypeKT.entries.getOrNull(it) } ?: FADataTypeKT.UNKNOWN
fun FADataTypeKT.toIbApi(): Types.FADataType? = Types.FADataType.entries.getOrNull(this.ordinal)

// Method
enum class MethodKT(val ibValue: String) {
    EqualQuantity("EqualQuantity"),
    AvailableEquity("AvailableEquity"),
    PctChange("PctChange"),
    UNKNOWN("");
    companion object {
        fun fromIb(value: String?): MethodKT = entries.find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.Method?.toKT(): MethodKT = MethodKT.fromIb(this?.name)
fun MethodKT.toIbApi(): Types.Method? = Types.Method.entries.find { it.name == this.ibValue }

// TriggerMethod
enum class TriggerMethodKT(val ibValue: Int) {
    DEFAULT(0),
    DOUBLE_BID_ASK(1),
    LAST(2),
    DOUBLE_LAST(3),
    BID_ASK(4),
    LAST_OF_BID_ASK(7),
    MIDPOINT(8),
    UNKNOWN(-1);
    companion object {
        fun fromIb(value: Int?): TriggerMethodKT = entries.find { it.ibValue == value } ?: UNKNOWN
    }
}
fun Types.TriggerMethod?.toKT(): TriggerMethodKT = this?.ordinal?.let { TriggerMethodKT.entries.getOrNull(it) } ?: TriggerMethodKT.UNKNOWN
fun TriggerMethodKT.toIbApi(): Types.TriggerMethod? = Types.TriggerMethod.entries.getOrNull(this.ordinal)

// OrderStatus
enum class OrderStatusKT(val ibValue: String) {
    PENDING_SUBMIT("PendingSubmit"),
    PENDING_CANCEL("PendingCancel"),
    PRE_SUBMITTED("PreSubmitted"),
    SUBMITTED("Submitted"),
    CANCELLED("Cancelled"),
    FILLED("Filled"),
    INACTIVE("Inactive"),
    UNKNOWN("");
    companion object {
        fun fromIb(value: String?): OrderStatusKT = entries.find { it.ibValue == value } ?: UNKNOWN
    }
}
fun OrderStatus?.toKT(): OrderStatusKT = OrderStatusKT.fromIb(this?.name)
fun OrderStatusKT.toIbApi(): OrderStatus? = OrderStatus.entries.find { it.name == this.ibValue }
