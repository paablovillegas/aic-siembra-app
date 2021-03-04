package mx.grupo.tepeyac.mexico.aic.siembra.utils

import androidx.databinding.InverseMethod
import kotlin.math.roundToInt

class BindingConverter {
    companion object {
        @JvmStatic
        @InverseMethod("stringToFloat")
        fun floatToString(value: Float): String? {
            return try {
                if (value == 0f) {
                    return ""
                } else if (value % 1 == 0f) {
                    return value.roundToInt().toString()
                }
                value.toString()
            } catch (e: Exception) {
                ""
            }
        }

        @JvmStatic
        fun stringToFloat(value: String): Float {
            return try {
                value.toFloat()
            } catch (e: Exception) {
                0.0f
            }
        }

        @JvmStatic
        @InverseMethod("stringToLong")
        fun longToString(value: Long): String? {
            return try {
                if (value == 0.toLong()) {
                    return ""
                }
                return value.toString()
            } catch (e: Exception) {
                ""
            }
        }

        @JvmStatic
        fun stringToLong(value: String): Long {
            return try {
                value.toLong()
            } catch (e: Exception) {
                return 0.toLong()
            }
        }

        @JvmStatic
        @InverseMethod("stringToInt")
        fun intToString(value: Int): String? {
            return try {
                if (value == 0) {
                    return ""
                }
                return value.toString()
            } catch (e: Exception) {
                ""
            }
        }

        @JvmStatic
        fun stringToInt(value: String): Int {
            return try {
                value.toInt()
            } catch (e: Exception) {
                return 0
            }
        }

        @JvmStatic
        @InverseMethod("stringToDouble")
        fun doubleToString(value: Double): String? {
            return try {
                if (value == 0.0) {
                    return ""
                }
                return value.toString()
            } catch (e: Exception) {
                ""
            }
        }

        @JvmStatic
        fun stringToDouble(value: String): Double {
            return try {
                value.toDouble()
            } catch (e: Exception) {
                return 0.0
            }
        }
    }
}