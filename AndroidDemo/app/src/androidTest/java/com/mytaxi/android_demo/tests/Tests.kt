package com.mytaxi.android_demo.tests

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.mytaxi.android_demo.utils.LogT
import org.junit.After
import org.junit.Before
import java.io.File

open class Tests {
    val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
    val appContext: Context = instrumentation.targetContext.applicationContext

    @Before
    open fun beforeEach() {
        // common setup before each test case
    }

    @After
    open fun afterEach() {
        // common teardown after each test case
    }

    fun clearAppData() {
        LogT.i("clear application data")
        clearPreferences()
        clearDatabase()
    }

    fun clearPreferences() {
        LogT.d("clear preferences")
        val root = appContext.filesDir.parentFile
        val sharedPreferencesFileNames: Array<String>? = File(root, "shared_prefs").list()
        if (sharedPreferencesFileNames != null) {
            for (fileName in sharedPreferencesFileNames) {
                val sharedPreferencesName = fileName.replace(".xml", "")
                //delete doesn't seem to work
                appContext.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE).edit().clear().commit()
            }
        }
    }

    fun clearDatabase() {
        LogT.d("clear database")
        val databaseList: Array<String>? = appContext.databaseList()
        if (databaseList != null) {
            for (database in databaseList) {
                // when transaction rollback files exists they are always locked so we can't delete them
                if (database.contains(".db-journal")) {
                    appContext.deleteDatabase(database)
                    continue
                }

                // when using transaction write ahead logging then this db files are listed but often they don't exist
                if (database.contains(".db-wal") || database.contains(".db-shm")) {
                    appContext.deleteDatabase(database)
                    continue
                }

                LogT.d("deleting $database")

                val databasePath = appContext.getDatabasePath(database)
                if (databasePath.exists()) {
                    appContext.deleteDatabase(database)
                }
            }
        }
    }
}