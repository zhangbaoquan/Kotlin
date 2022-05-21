package kh.h9

interface Computer {
    val cpu: String

    // 方式四：使用伴生对象的方式创建（推荐）
    companion object Factory {
        operator fun invoke(type: ComputerType): Computer {
            return when (type) {
                ComputerType.PC -> PC()
                ComputerType.Server -> Server()
            }
        }
    }
}

class PC(override val cpu: String = "Core") : Computer
class Server(override val cpu: String = "Xeon") : Computer

enum class ComputerType {
    PC, Server
}

// 方式一：老版简单工厂实现
class ComputerFactoryOld {
    fun produce(type: ComputerType): Computer {
        return when (type) {
            ComputerType.PC -> PC()
            ComputerType.Server -> Server()
        }
    }
}

// 方式二：新版简单工厂模式
object ComputerFactoryNew {
    fun produce(type: ComputerType): Computer {
        return when (type) {
            ComputerType.PC -> PC()
            ComputerType.Server -> Server()
        }
    }
}

// 方式三：使用重载运算符的方式实现工厂
object ComputerFactoryNew2 {
    operator fun invoke(type: ComputerType): Computer {
        return when (type) {
            ComputerType.PC -> PC()
            ComputerType.Server -> Server()
        }
    }
}

// 使用内联函数简化抽象工厂
abstract class AbstractFactory {

}

// 装饰者模式(这里使用kotlin的by 关键字实现动态代理)
interface Macbook{
    fun getCost():Int
    fun getDesc():String
}

class MacBookPro : Macbook{
    override fun getCost(): Int = 1000
    override fun getDesc(): String = "啦啦啦"
}

/*******      策略模式实现    ***********/

/*******      模板方法模式实现    ***********/


/*******     装饰者模式的实现  **********/
class ProcessorUpgradeMacBookProProxy(val macBookPro: MacBookPro): Macbook by macBookPro{
    override fun getCost(): Int {
        return macBookPro.getCost() + 2000
    }

    override fun getDesc(): String {
        return macBookPro.getDesc() + "哈哈哈"
    }
}


fun main() {
    // 简单工厂
    val pc1 = ComputerFactoryOld().produce(ComputerType.PC)
    val pc2 = ComputerFactoryNew.produce(ComputerType.PC)
    // 使用重载运算符的方式
    val pc3 = ComputerFactoryNew2(ComputerType.Server)
    // 使用伴生对象
    val pc4 = Computer.Factory(ComputerType.Server)

    // 装饰者模式的使用
    val macBookPro = MacBookPro()
    val pmp = ProcessorUpgradeMacBookProProxy(macBookPro)
    println(pmp.getDesc())
    println(pmp.getCost())

}












