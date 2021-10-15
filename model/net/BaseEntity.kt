package com.lft.hileia.model.net

import com.lft.hileia.constants.BaseCodeConstants

data class BaseEntity<T>(
    var code: Int, var desc: String, var data: T?
) {
    fun isSuccess(): Boolean {
        return code == BaseCodeConstants.CODE_SUCCESS
    }
}
