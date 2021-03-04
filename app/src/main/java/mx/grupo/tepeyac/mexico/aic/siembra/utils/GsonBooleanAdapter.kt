package mx.grupo.tepeyac.mexico.aic.siembra.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.util.*

class GsonBooleanAdapter : JsonDeserializer<Boolean?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type,
        context: JsonDeserializationContext?
    ): Boolean? {
        return try {
            when (json.asInt) {
                0 -> false
                1 -> true
                else -> null
            }
        } catch (e: Exception) {
            val code1 = json.asString
            when {
                code1.toLowerCase(Locale.ROOT) == "true" -> true
                code1.toLowerCase(Locale.ROOT) == "false" -> false
                else -> null
            }
        }
    }
}