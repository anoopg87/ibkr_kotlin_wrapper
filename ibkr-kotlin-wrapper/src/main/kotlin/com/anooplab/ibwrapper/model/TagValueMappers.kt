package com.anooplab.ibwrapper.model

import com.ib.client.TagValue

fun TagValue.toKT(): TagValueKT = TagValueKT(tag = m_tag, value = m_value)
fun TagValueKT.toIbApi(): TagValue = TagValue(tag, value)
