package com.zsl.test.basezbproject.di.module;

import android.content.Context;

import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.Constants;
import com.zsl.test.basezbproject.http.APIService;
import com.zsl.test.basezbproject.http.converter.CustomGsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Created by stone
 * @since 2018/7/10
 */
@Module
public class AppModule {

    /**
     *  默认超时时间 单位/秒
     */
    private static final int DEFAULT_TIME_OUT = 10;

    private Context context;

    private String baseUrl;
    public AppModule(App context, String baseUrl){
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();
    }
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){

        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new MoreBaseUrlInterceptor())
                .build();
    }

    @Provides
    @Singleton
    public APIService provideAPIService(){
        return provideRetrofit().create(APIService.class);
    }

    /**
     * 开启okhttp打印连接日志
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        boolean isDebug= true;//是否开启
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if(isDebug)
        {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        //开启打印连接日志
        return interceptor;
    }
    public class MoreBaseUrlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取原始的originalRequest
            Request originalRequest = chain.request();
            //获取老的url
            HttpUrl oldUrl = originalRequest.url();
            //获取originalRequest的创建者builder
            Request.Builder builder = originalRequest.newBuilder();
            //获取头信息的集合如：manage,mdffx
            List<String> urlnameList = originalRequest.headers("name");
            if (urlnameList != null && urlnameList.size() > 0) {
                //删除原有配置中的值,就是namesAndValues集合里的值
                builder.removeHeader("name");
                //获取头信息中配置的value,如：manage或者mdffx
                String urlname = urlnameList.get(0);
                HttpUrl baseURL = null;
                //根据头信息中配置的value,来匹配新的base_url地址
                if ("manage".equals(urlname)) {
                    baseURL = HttpUrl.parse(Constants.BASE_URL1);
                } else if ("mdffx".equals(urlname)) {
                    baseURL = HttpUrl.parse(Constants.BASE_URL2);
                }
                //重建新的HttpUrl，需要重新设置的url部分
                HttpUrl newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseURL.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        .build();
                //获取处理后的新newRequest
                Request newRequest = builder.url(newHttpUrl).build();
                return chain.proceed(newRequest);
            } else {
                return chain.proceed(originalRequest);
            }

        }
    }
    }
