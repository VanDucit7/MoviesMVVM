/**
 * Copyright (C) 2017 Tony Shen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vn.fsoft.gstlib.core.extensions

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T> SharedPreferences.gSon(defaultValue: T, key: String? = null) =
        object : ReadWriteProperty<Any, T> {
            private val gSon = Gson()

            override fun getValue(thisRef: Any, property: KProperty<*>): T {

                val s = requireNotNull(getString(key ?: property.name, ""))

                return if (s.isBlank()) defaultValue else gSon.fromJson(s, T::class.java)
            }

            override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
                    edit().putString(key ?: property.name, gSon.toJson(value)).apply()
        }

inline fun <reified T> SharedPreferences.gSonList(key: String? = null) =
        object : ReadWriteProperty<Any, List<T>> {

            private val gSon = Gson()

            override fun getValue(thisRef: Any, property: KProperty<*>): List<T> {

                val s = requireNotNull(getString(key ?: property.name, ""))

                return if (s.isBlank()) emptyList() else gSon.fromJson<List<T>>(s, object : TypeToken<List<T>>() {}.type)
            }

            override fun setValue(thisRef: Any, property: KProperty<*>, value: List<T>) =
                    edit().putString(key ?: property.name, gSon.toJson(value)).apply()
        }