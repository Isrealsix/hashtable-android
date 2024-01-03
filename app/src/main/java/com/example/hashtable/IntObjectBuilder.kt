import java.util.*

class IntObjectBuilder : ObjectBuilder {
    override fun typeName(): String? {
        return "IntObjectBuilder"
    }

    override fun create(): Any? {
        val min = 0
        val max = 32000
        val diff = max - min
        val random = Random()
        return random.nextInt(diff + 1)
    }

    override fun toString(value: Any?): String? {
        return value.toString()
    }
}