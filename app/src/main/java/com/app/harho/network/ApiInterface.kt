package com.app.harho.network

import com.app.harho.adapter.propogatorDate.PropogatorDateResponse
import com.app.harho.model.auth.login.LoginPramModel
import com.app.harho.model.auth.login.LoginResponse
import com.app.harho.model.auth.signup.SignUpPramModel
import com.app.harho.model.auth.signup.SignUpResponse
import com.app.harho.model.auth.update.boxarrived.BoxArrivedPramModel
import com.app.harho.model.auth.update.isupdate.IsUpdatePramModel
import com.app.harho.model.auth.update.readytogrow.ReadyToGrowPramModel
import com.app.harho.model.auth.update.recieved.RecievedPramModel
import com.app.harho.model.auth.update.updateResponse
import com.app.harho.model.auth.userme.UserMeResponse
import com.app.harho.model.leaderBoard.LeaderBoardResponse
import com.app.harho.model.propogator.productDetail.PropogatorProductDetailResponse
import com.app.harho.model.propogator.propogatorNumber.PropogatorNumberResponse
import com.app.harho.model.propogator.propogatorProduct.PropogatorProductResponse
import com.app.harho.model.propogator.propogatorRocket.PropogatorRocketResponse
import com.app.harho.model.propogator.propogatorTittle.PropogatorTittleResponse
import com.app.harho.model.question.QuestionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("auth/log-in")
    suspend fun onLogin(@Body body: LoginPramModel): Response<LoginResponse>

    @POST("auth/set-profile")
    suspend fun onSignUp(@Body body: SignUpPramModel): Response<SignUpResponse>

    @POST("auth/update-intro")
    suspend fun onBoxArrived(@Body body: BoxArrivedPramModel): Response<updateResponse>

    @POST("auth/update-intro")
    suspend fun onRecieved(@Body body: RecievedPramModel): Response<updateResponse>

    @POST("auth/update-intro")
    suspend fun onReadyToGrow(@Body body: ReadyToGrowPramModel): Response<updateResponse>

    @POST("auth/update-intro")
    suspend fun onIsUpdate(@Body body: IsUpdatePramModel): Response<updateResponse>

    @GET("auth/me")
    suspend fun getUserMe(): Response<UserMeResponse>

    @GET("propacate/all-propagators")
    suspend fun getPropogatorTittle(): Response<PropogatorTittleResponse>

    @GET("propacate/steps")
    suspend fun getPropogatorNumber(@Query("propagator_id") propagator_id: String?): Response<PropogatorNumberResponse>

    @GET("propacate/propacate-products")
    suspend fun getPropogatorProduct(@Query("propagator_id") propagator_id: String?): Response<PropogatorProductResponse>

    @GET("propacate/propagator-step-3")
    suspend fun getPropogatorDate(
        @Query("propagator_id") propagator_id: String?,
        @Query("month") month: String
    ): Response<PropogatorDateResponse>

    @GET("propacate/propacate-categories")
    suspend fun getPropogatorProductDetail(): Response<PropogatorProductDetailResponse>

    @GET("propacate/get-propagator-month")
    suspend fun getPropogatorRocket(@Query("propagator_id") propagator_id :String): Response<PropogatorRocketResponse>

    @GET("quiz/leader-board")
    suspend fun getLeaderBoard( @Query("offset") offset: String,
                           @Query("limit") limit: String): Response<LeaderBoardResponse>

    @GET("quiz/quiz-question")
    suspend fun getQuestion(): Response<QuestionResponse>

}