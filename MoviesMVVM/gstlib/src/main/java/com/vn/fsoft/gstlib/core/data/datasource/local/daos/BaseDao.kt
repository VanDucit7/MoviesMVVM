package com.vn.fsoft.gstlib.core.data.datasource.local.daos

import androidx.room.Delete
import androidx.room.Insert
import com.vn.fsoft.gstlib.core.data.datasource.local.entity.BaseEntity

interface BaseDao<T : BaseEntity> {

    @Insert
    fun insert(vararg item: BaseEntity)

    @Delete
    fun deleteEmployee(employee: BaseEntity)
}