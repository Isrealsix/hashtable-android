import java.util.*

class Vector2D : ObjectBuilder {
    var valueX: Double
    var valueY: Double

    constructor() {
        valueX = 0.0
        valueY = 0.0
    }

    constructor(valueX: Double, valueY: Double) {
        this.valueX = valueX
        this.valueY = valueY
    }

    constructor(v: Vector2D) {
        valueX = v.valueX
        valueY = v.valueY
    }

    fun print() {
        println("X is: $valueX Y is: $valueY")
    }

    override fun toString(): String {
        return "X: $valueX Y: $valueY"
    }

    override fun typeName(): String? {
        return "Vector2D"
    }

    override fun create(): Any? {
        val min = 0
        val max = 100
        val diff = max - min
        val random = Random()
        val i1 = random.nextInt(diff + 1)
        val i2 = random.nextInt(diff + 1)
        return Vector2D(i1.toDouble(), i2.toDouble())
    }

    override fun toString(value: Any?): String? {
        return value.toString()
    }
}
