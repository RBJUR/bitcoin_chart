package br.com.roquebuarque.bitcoinchart.util


import java.text.NumberFormat
import java.util.Locale

class MaskUtil {
    companion object {
        fun formatMoney(value: Double, prefix: Boolean, fraction: Int = 0): String {
            val numberFormat = NumberFormat.getNumberInstance(Locale.US)
            numberFormat.minimumFractionDigits = fraction
            numberFormat.maximumFractionDigits = fraction

            val valueFormatted: String
            valueFormatted = if (prefix) {
                "U$ " + numberFormat.format(value)
            } else {
                numberFormat.format(value)
            }

            return valueFormatted
        }
    }
}
