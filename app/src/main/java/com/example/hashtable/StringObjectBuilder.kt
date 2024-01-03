import java.util.*

class StringObjectBuilder internal constructor() : ObjectBuilder {
    init {
        create()
    }

    override fun typeName(): String? {
        return "String"
    }

    override fun create(): String? {
        val leftBorder = 97
        val rightBorder = 122
        val random = Random()
        val stringBuffer = StringBuffer()
        for (i in 0..5) {
            val randomBorder = leftBorder + (random.nextFloat() * (rightBorder - leftBorder + 1)).toInt()
            stringBuffer.append(randomBorder.toChar())
        }
        return stringBuffer.toString()
    }

    override fun toString(value: Any?): String? {
        return value.toString()
    }
}
