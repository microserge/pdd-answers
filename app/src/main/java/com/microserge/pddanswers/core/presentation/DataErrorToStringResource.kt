package com.microserge.pddanswers.core.presentation

import com.microserge.pddanswers.core.domain.DataError
import com.microserge.pddanswers.R

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.DISK_FULL -> R.string.error_unknown
        DataError.Local.UNKNOWN -> R.string.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> R.string.error_unknown
        DataError.Remote.TOO_MANY_REQUESTS -> R.string.error_unknown
        DataError.Remote.NO_INTERNET -> R.string.error_unknown
        DataError.Remote.SERVER -> R.string.error_unknown
        DataError.Remote.SERIALIZATION -> R.string.error_unknown
        DataError.Remote.UNKNOWN -> R.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}