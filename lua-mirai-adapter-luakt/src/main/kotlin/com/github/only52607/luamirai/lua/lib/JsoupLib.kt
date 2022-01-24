package com.github.only52607.luamirai.lua.lib

import com.github.only52607.luakt.ValueMapper
import com.github.only52607.luakt.utils.provideScope
import org.jsoup.Jsoup
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.TwoArgFunction

class JsoupLib(
    private val valueMapper: ValueMapper
) : TwoArgFunction() {
    override fun call(modname: LuaValue?, env: LuaValue?): LuaValue {
        val globals = env?.checkglobals()
        valueMapper.provideScope {
            globals?.edit {
                "Jsoup" to {
                    "parse" to luaFunctionOf { content: String ->
                        return@luaFunctionOf Jsoup.parse(content)
                    }
                }
            }
        }
        return LuaValue.NIL
    }
}