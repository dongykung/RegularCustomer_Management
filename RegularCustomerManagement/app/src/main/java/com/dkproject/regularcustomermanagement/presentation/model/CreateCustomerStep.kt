package com.dkproject.regularcustomermanagement.presentation.model

import androidx.annotation.StringRes
import com.dkproject.regularcustomermanagement.R


enum class CreateCustomerStep(@StringRes val label: Int, val progress: Float) {
    BASIC(label = R.string.basicinfo, progress = 0.1f),
    AFFILIATION(label = R.string.affiliationtag, progress = 0.5f),
    MEMO(label = R.string.memo, progress = 1.0f)
}
