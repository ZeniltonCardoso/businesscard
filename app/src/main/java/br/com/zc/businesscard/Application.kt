package br.com.zc.businesscard

import android.app.Application
import br.com.zc.businesscard.data.AppDataBase
import br.com.zc.businesscard.data.BusinessCardRepository

class Application : Application() {
    private val database by lazy { AppDataBase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}