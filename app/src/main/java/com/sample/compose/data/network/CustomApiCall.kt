package com.sample.compose.data.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCall<T>(private val delegate: Call<T>) : Call<ApiResponse<T>> {
    override fun enqueue(callback: Callback<ApiResponse<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    callback.onResponse(
                        this@ApiResponseCall,
                        Response.success(ApiResponse.Success(body))
                    )
                } else {
                    callback.onResponse(
                        this@ApiResponseCall,
                        Response.success(ApiResponse.Error(response.message()))
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.Error(t.message ?: "Unknown Error"))
                )
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted
    override fun clone() = ApiResponseCall(delegate.clone())
    override fun cancel() = delegate.cancel()
    override fun isCanceled() = delegate.isCanceled
    override fun execute(): Response<ApiResponse<T>> = throw UnsupportedOperationException()
    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
}


class ApiResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation?>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) { "Return type must be parameterized" }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != ApiResponse::class.java) return null
        check(responseType is ParameterizedType) { "Response type must be parameterized" }

        val successType = getParameterUpperBound(0, responseType)
        return object : CallAdapter<Any, Call<ApiResponse<Any>>> {
            override fun responseType(): Type = successType
            override fun adapt(call: Call<Any>): Call<ApiResponse<Any>> = ApiResponseCall(call)
        }
    }

}