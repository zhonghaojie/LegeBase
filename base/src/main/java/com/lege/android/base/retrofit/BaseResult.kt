package com.lege.android.base.retrofit

/**
 * Description:
 * Created by zhonghaojie on 2019-08-22.
 */
class BaseResult<T> {
    var error_code: Int? = null
    var reason: String? = null
    var result: T? = null
    var resultcode: Int? = null

    fun isSuccess(): Boolean = ((resultcode == 200) && (error_code == 0))
}
interface OnResultResponse<T> {
    fun onError(msg: String)
    fun onSuccess(t: T)
}