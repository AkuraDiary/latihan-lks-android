package com.example.zendentaclient.data.source.session

import android.content.Context
import com.example.zendentaclient.model.User

class UserPreferences(mContext: Context) {
    companion object{
        const val pref_name = "userLogin"
        const val usrname_key = "name"
        const val id_key = "id"
        const val email_key = "email"
        const val pass_key = "pass"

    }

    private val preferences = mContext.getSharedPreferences(pref_name,Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun saveSesions(user : User){
        user.id?.let { editor.putInt(id_key, it) }
        editor.putString(usrname_key, user.username)
        editor.putString(email_key, user.email)
        editor.putString(pass_key, user.password)
        editor.apply()
        editor.commit()
    }

    fun getUserSession() : User?{
        var username = preferences.getString(usrname_key, "")
        var id = preferences.getInt(id_key, 0)
        var email = preferences.getString(email_key, "")
        var password = preferences.getString(pass_key, "")

        return User(id= id, email = email, username =username , password=password)

    }

    fun deleteSession(){
        editor.clear()
        editor.apply()
        editor.commit()
    }
}