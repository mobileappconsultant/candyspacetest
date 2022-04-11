package com.example.candyspacestack.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.candyspacestack.ui.model.User
import com.google.gson.Gson

class AssetParamType : NavType<User>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): User? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): User {
        return Gson().fromJson(value, User::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: User) {
        bundle.putParcelable(key, value)
    }
}
