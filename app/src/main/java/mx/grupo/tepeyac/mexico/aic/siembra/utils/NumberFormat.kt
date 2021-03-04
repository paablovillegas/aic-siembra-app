package mx.grupo.tepeyac.mexico.aic.siembra.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class NumberFormat {
    companion object {
        @JvmStatic
        fun currencyFormat(): DecimalFormat {
            val symbols = DecimalFormatSymbols(Locale.forLanguageTag("es-MX"))
            symbols.decimalSeparator = '.'
            symbols.groupingSeparator = ','
            val format = DecimalFormat("$ #,###,##0.00")
            format.decimalFormatSymbols = symbols
            return format
        }
    }
}