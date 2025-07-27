package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's ScannerSubscription model.
 */
data class ScannerSubscriptionKT(
    val numberOfRows: Int? = null,
    val instrument: String? = null,
    val locationCode: String? = null,
    val scanCode: String? = null,
    val abovePrice: Double? = null,
    val belowPrice: Double? = null,
    val aboveVolume: Int? = null,
    val marketCapAbove: Double? = null,
    val marketCapBelow: Double? = null,
    val moodyRatingAbove: String? = null,
    val moodyRatingBelow: String? = null,
    val spRatingAbove: String? = null,
    val spRatingBelow: String? = null,
    val maturityDateAbove: String? = null,
    val maturityDateBelow: String? = null,
    val couponRateAbove: Double? = null,
    val couponRateBelow: Double? = null,
    val excludeConvertible: Boolean? = null,
    val averageOptionVolumeAbove: Int? = null,
    val scannerSettingPairs: String? = null,
    val stockTypeFilter: String? = null
)
