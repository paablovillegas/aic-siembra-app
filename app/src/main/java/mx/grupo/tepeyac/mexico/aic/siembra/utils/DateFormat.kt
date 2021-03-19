package mx.grupo.tepeyac.mexico.aic.siembra.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormat {
    companion object {
        val DATE_FORMAT = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.forLanguageTag("es-MX")
        )
        val DATETIME_FORMAT = SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            Locale.forLanguageTag("es-MX")
        )
    }
}