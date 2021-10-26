package com.example.architecturedemo.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.util.*
import java.util.regex.Pattern

inline fun <T : Fragment, reified VM : ViewModel> T.getViewModel(viewModelFactory: ViewModelProvider.Factory): VM {
    return ViewModelProviders.of(this, viewModelFactory)
        .get(VM::class.java)
}

fun Activity.showLightStatusBar() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    ViewCompat.setOnApplyWindowInsetsListener(
        window.decorView
    ) { _, insets ->
        ViewCompat.onApplyWindowInsets(
            window.decorView,
            insets.replaceSystemWindowInsets(
                insets.systemWindowInsetLeft, 0,
                insets.systemWindowInsetRight, insets.systemWindowInsetBottom
            )
        )
    }
}

fun toDp(context: Context, value: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}

fun isRTL(): Boolean {
    return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL
}

fun String.isValidMobileIndia(): Boolean {
    return this.matches("^(\\+91|91)?[6-9]\\d{9}\$".toRegex())
}

fun String.isValidUsername(): Boolean =
    Pattern.matches("^[\\p{L} .'-]{2,}+$", this)

fun String.isValidationForMobNo(): Boolean =
    Pattern.matches("[0-9]+", this)

fun String.isValidCharacters(): Boolean =
    Pattern.matches("^[a-zA-Z]+\$", this)

fun String.isValidOtp(): Boolean {
    return this.trim().length == 6
}

fun String.isValidSerialNo(): Boolean {
    return this.trim().isNotEmpty()
}

fun String.isValidVerificationCode(): Boolean {
    return this.trim().isNotEmpty()
}

fun String.isValidWifiName(): Boolean {
    return this.trim().isNotEmpty()
}

fun String.isValidDeviceName(): Boolean {
    return this.trim().isNotEmpty()
}

fun String.isValidWifiPwd(): Boolean {
    return this.trim().isNotEmpty()
}

fun String.isAlphaNumeric(): Boolean {
    val regex = "\\d+".toRegex()
    return regex.containsMatchIn(this)
}

fun String.isSpecialChar(): Boolean {
    val regex = "[!@#$%&*?_\\-]+".toRegex()
    return regex.containsMatchIn(this)
}

fun String.isValidPassword(): Boolean {
    val regex = "[a-zA-Z]".toRegex()
    return regex.containsMatchIn(this) && this.trim().length >= 8
}

fun View.hideKeyBoard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}
