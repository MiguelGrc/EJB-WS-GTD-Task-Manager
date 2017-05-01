package com.sdi.client.service;

import com.sdi.client.dto.Category;
import com.sdi.client.dto.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GTDRestService {

    @GET("TaskServiceRs")
    Call<List<Category>> findCategoriesByUserId();
    //TODO Param (id) removed for the moment

    @GET("TaskServiceRs/{id}")
    Call<List<Task>> findTasksByCategoryId(@Path("id") Long catId);
    //TODO Suponiendo que se pidan este tipo de tareas ^

    @POST("TaskServiceRs/{id}")
    Call<Void>markTaskAsFinished(@Path("id") Long id);

    @PUT("TaskServiceRs")
    Call<Void> createTask(@Body Task task); //Necesitamos el @Body para retrofit!
}
