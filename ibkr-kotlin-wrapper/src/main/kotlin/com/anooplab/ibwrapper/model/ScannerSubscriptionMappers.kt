package com.anooplab.ibwrapper.model

import com.ib.client.ScannerSubscription

fun ScannerSubscription.toKT(): ScannerSubscriptionKT = ScannerSubscriptionKT(
    numberOfRows = numberOfRows(),
    instrument = instrument(),
    locationCode = locationCode(),
    scanCode = scanCode(),
    abovePrice = abovePrice(),
    belowPrice = belowPrice(),
    aboveVolume = aboveVolume(),
    marketCapAbove = marketCapAbove(),
    marketCapBelow = marketCapBelow(),
    moodyRatingAbove = moodyRatingAbove(),
    moodyRatingBelow = moodyRatingBelow(),
    spRatingAbove = spRatingAbove(),
    spRatingBelow = spRatingBelow(),
    maturityDateAbove = maturityDateAbove(),
    maturityDateBelow = maturityDateBelow(),
    couponRateAbove = couponRateAbove(),
    couponRateBelow = couponRateBelow(),
    excludeConvertible = excludeConvertible(),
    averageOptionVolumeAbove = averageOptionVolumeAbove(),
    scannerSettingPairs = scannerSettingPairs(),
    stockTypeFilter = stockTypeFilter()
)

fun ScannerSubscriptionKT.toIbApi(): ScannerSubscription = ScannerSubscription().apply {
    numberOfRows(numberOfRows ?: 0)
    instrument(instrument ?: "")
    locationCode(locationCode ?: "")
    scanCode(scanCode ?: "")
    abovePrice(abovePrice ?: 0.0)
    belowPrice(belowPrice ?: 0.0)
    aboveVolume(aboveVolume ?: 0)
    marketCapAbove(marketCapAbove ?: 0.0)
    marketCapBelow(marketCapBelow ?: 0.0)
    moodyRatingAbove(moodyRatingAbove ?: "")
    moodyRatingBelow(moodyRatingBelow ?: "")
    spRatingAbove(spRatingAbove ?: "")
    spRatingBelow(spRatingBelow ?: "")
    maturityDateAbove(maturityDateAbove ?: "")
    maturityDateBelow(maturityDateBelow ?: "")
    couponRateAbove(couponRateAbove ?: 0.0)
    couponRateBelow(couponRateBelow ?: 0.0)
    excludeConvertible(excludeConvertible ?: false)
    averageOptionVolumeAbove(averageOptionVolumeAbove ?: 0)
    scannerSettingPairs(scannerSettingPairs ?: "")
    stockTypeFilter(stockTypeFilter ?: "")
}
