package com.github.only52607.luamirai.lua

import com.github.only52607.luakt.lib.KotlinCoroutineLib
import com.github.only52607.luakt.lib.LuaKotlinLib
import com.github.only52607.luamirai.core.script.AbstractBotScript
import com.github.only52607.luamirai.core.script.BotScriptHeader
import com.github.only52607.luamirai.core.script.BotScriptResourceFinder
import com.github.only52607.luamirai.core.script.BotScriptSource
import com.github.only52607.luamirai.lua.lib.*
import com.github.only52607.luamirai.lua.mapper.LuaMiraiLuaKotlinClassRegistry
import com.github.only52607.luamirai.lua.mapper.LuaMiraiValueMapper
import kotlinx.coroutines.*
import org.luaj.vm2.Globals
import org.luaj.vm2.LoadState
import org.luaj.vm2.LuaValue
import org.luaj.vm2.compiler.LuaC
import org.luaj.vm2.lib.*
import org.luaj.vm2.lib.jse.*
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class LuaMiraiScript(
    override val source: BotScriptSource,
    override val header: BotScriptHeader,
    mainInputStream: InputStream
) : AbstractBotScript(), CoroutineScope {

    override fun toString(): String {
        return "LuaMiraiScript: $source"
    }



    private var taskLib = TaskLib(LuaMiraiValueMapper)

    override val lang: String = "lua"

    private val globals: Globals = Globals()

    private val mainFunc: LuaValue

    override var stdout: OutputStream? = System.out
        set(value) {
            globals.STDOUT = value?.let(::PrintStream)
            field = value
        }

    override var stderr: OutputStream? = System.err
        set(value) {
            globals.STDERR = value?.let(::PrintStream)
            field = value
            stdErrPrintStream = PrintStream(value ?: System.err)
        }

    override var stdin: InputStream? = System.`in`
        set(value) {
            globals.STDIN = value
            field = value
        }

    private var stdErrPrintStream: PrintStream = PrintStream(stderr ?: System.err)

    override var coroutineContext: CoroutineContext = SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
        stdErrPrintStream.println(throwable)
    }

    init {
        installLibs()
        LoadState.install(globals)
        LuaC.install(globals)
        globals.finder = ResourceFinderAdapter(globals.finder, source.resourceFinder)
        globals.apply {
            STDOUT = PrintStream(System.out, false, "UTF-8")
            STDERR = PrintStream(System.err, false, "UTF-8")
            STDIN = stdin
        }
        mainFunc = globals.load(mainInputStream, source.name, "bt", globals)
    }

    override suspend fun onStart() {
        if (coroutineContext[ContinuationInterceptor] == null) {
            coroutineContext += taskLib.asCoroutineDispatcher()
        }
        try {
            mainFunc.invoke()
        } catch (e: Exception) {
            e.printStackTrace(PrintStream(stderr ?: System.err))
        }
    }

    override suspend fun onStop() {
        taskLib.shutdown()
        coroutineContext.cancel()
    }

    private fun installLibs() {
        loadBaseLibs()
        loadMiraiLibs()
        loadExtendLibs()
    }

    private fun loadBaseLibs() = globals.apply {
        load(JseBaseLib())
        load(PackageLib())
        load(Bit32Lib())
        load(TableLib())
        load(StringLib())
        load(CoroutineLib())
        load(JseMathLib())
        load(JseIoLib())
        load(JseOsLib())
        load(LuajavaLib())
        load(DebugLib()) // add debug lib
        load(taskLib)
    }

    private fun loadMiraiLibs() = globals.apply {
        load(StringExLib())
        load(MiraiLib(this@LuaMiraiScript, LuaMiraiValueMapper))
        load(LuaKotlinLib(this@LuaMiraiScript, LuaMiraiValueMapper, LuaMiraiLuaKotlinClassRegistry))
        load(KotlinCoroutineLib(this@LuaMiraiScript, LuaMiraiValueMapper))
    }

    private fun loadExtendLibs() = globals.apply {
        load(HttpLib(LuaMiraiValueMapper))
        load(KtxJsonLib())
        load(JDBCLib(LuaMiraiValueMapper))
        load(JsoupLib(LuaMiraiValueMapper))
        load(SocketLib(LuaMiraiValueMapper))
    }

    class ResourceFinderAdapter(
        private val parentFinder: ResourceFinder,
        private val botScriptResourceFinder: BotScriptResourceFinder?
    ) : ResourceFinder {
        override fun findResource(filename: String): InputStream? {
            val resource = botScriptResourceFinder?.findResource(filename)
            if (resource != null) return resource
            return parentFinder.findResource(filename)
        }
    }
}
