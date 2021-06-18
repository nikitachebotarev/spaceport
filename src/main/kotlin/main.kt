import repository.entity.app.App
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import repository.repository.impl.MessagesRepositoryImpl
import repository.repository.impl.ReportsRepositoryImpl
import repository.repository.impl.SpacePortRepositoryImpl
import java.util.Stack
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberProperties

fun main(args: Array<String>) {
    println("STARTED!")
    val app = App(SpacePortRepositoryImpl(), MessagesRepositoryImpl(), ReportsRepositoryImpl())
    embeddedServer(Netty, 8080) {
        routing {
            get {
                val query = context.parameters["query"]
                val answer = startInteractor(app, query!!.split(" ").toTypedArray())
                call.respondText(answer.toString(), ContentType.Text.Html, HttpStatusCode.OK)
            }
        }
    }.start()
}

fun startInteractor(app: App, args: Array<String>): Any {
    val name = args[0]
    val stack = Stack<String>().apply { addAll(args) }
    val clazz = Class.forName(name)
    val interactor = clazz.newInstance()
    val method = interactor::class.declaredFunctions.first { it.name == "execute" }
    val params = method.parameters.drop(1).map { parameter ->
        return@map when {
            parameter.name!!.toLowerCase().contains("repository") -> {
               app::class.declaredMemberProperties.first { it.returnType == parameter.type }.getter.call(app)
            }
            parameter.name!!.toLowerCase().contains("function") -> {
                (parameter.type.classifier as KClass<*>).createInstance()
            }
            else -> {
                stack.pop()
            }
        }
    }
    println("invoking $method, with params $params")
    return method.call(*params.toMutableList().apply { add(0, interactor) }.toTypedArray())!!
}