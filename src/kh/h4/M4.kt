package kh.h4


sealed class Shape {
    class Circle(val radius: Double) : Shape()

    class Rect(val width: Double, val height: Double) : Shape()
}

fun getArea(shape: Shape):Double = when(shape){
    is Shape.Circle -> Math.PI * shape.radius
    is Shape.Rect -> shape.width * shape.height
}

fun main() {

}
