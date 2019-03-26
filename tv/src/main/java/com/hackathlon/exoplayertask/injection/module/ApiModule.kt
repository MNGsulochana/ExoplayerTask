package com.hackathlon.exoplayertask.injection.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.hackathlon.exoplayertask.api.request.ApiIntern
import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.api.response.ApiResponse
import com.hackathlon.exoplayertask.api.response.CustomJsonDeserializer
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.injection.annotate.Appcontext
import com.hackathlon.exoplayertask.ui.leanback.TestDataModel
import com.hackathlon.exoplayertask.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


@Module
class ApiModule {

  //  private val dataType: Type = object : TypeToken<ApiResponse<DataModel>>() {}.type
  private val dataType: Type = object : TypeToken<ApiResponse<TestDataModel>>() {}.type

    @Provides
    fun providesApiHandler(
            @Appcontext context: Context, api: ApiIntern, retrofit: Retrofit): ApiHandler {
        return ApiHandler(context, api, retrofit)
    }

    @Provides
    fun providesApi(retrofit: Retrofit): ApiIntern {
        return retrofit.create(ApiIntern::class.java)
    }

    @Provides
    fun providesRetrofit(httpClient: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(factory)
                .build()
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val intrceptor = HttpLoggingInterceptor()
        intrceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(intrceptor)
                .build()
    }


    @Provides
    fun providesGsonFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }
    //

    /*@Provides
    fun providesGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(dataType, CustomJsonDeserializer<DataModel>())
                .create()
    }*/

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(dataType, CustomJsonDeserializer<TestDataModel>())
                .create()
    }
    /*@Provides
    fun converter()
    {
        val collectionType = object : TypeToken<Collection<TestDataModel>>() {

        }.type
        val enums = providesGson().fromJson(yourJson, collectionType)
    }
*/
}
