package com.ooooonly.luaMirai.base

interface BotScriptManager<S : BotScript, T: BotScriptSource> {
    /**
     * 列出所有脚本
     */
    fun list(): List<S>

    /**
     * 获取指定位置脚本
     */
    fun getScript(index: Int): S

    /**
     * 读入脚本
     * 返回脚本索引
     */
    fun add(source: T): Int

    /**
     * 读入并加载脚本
     * 返回脚本索引
     */
    suspend fun load(source: T): Int

    /**
     * 加载指定位置脚本
     */
    suspend fun execute(scriptId: Int)

    /**
     * 重载指定位置脚本
     */
    suspend fun reload(scriptId: Int)

    /**
     * 停止指定位置脚本
     */
    suspend fun stop(scriptId: Int)

    /**
     * 删除指定位置脚本
     */
    suspend fun delete(scriptId: Int)

    /**
     * 停止所有脚本
     */
    suspend fun stopAll()

    /**
     * 重载所有脚本
     */
    suspend fun reloadAll()

    /**
     * 删除所有脚本
     */
    suspend fun deleteAll()
}