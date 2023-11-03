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

class Repository {
    private val getApi = GetApi.api
    suspend fun onLogin(body: LoginPramModel): Response<LoginResponse> {
        return getApi.onLogin(body)
    }

    suspend fun onSignUp(body: SignUpPramModel): Response<SignUpResponse> {
        return getApi.onSignUp(body)
    }

    suspend fun onBoxArrived(body: BoxArrivedPramModel): Response<updateResponse> {
        return getApi.onBoxArrived(body)
    }

    suspend fun onRecieved(body: RecievedPramModel): Response<updateResponse> {
        return getApi.onRecieved(body)
    }

    suspend fun onIsUpdate(body: IsUpdatePramModel): Response<updateResponse> {
        return getApi.onIsUpdate(body)
    }

    suspend fun onReadyToGrow(body: ReadyToGrowPramModel): Response<updateResponse> {
        return getApi.onReadyToGrow(body)
    }

    suspend fun getUserMe(): Response<UserMeResponse> {
        return getApi.getUserMe()
    }

    suspend fun getPropogatorTittle(): Response<PropogatorTittleResponse> {
        return getApi.getPropogatorTittle()
    }

    suspend fun getPropogatorNumber(id: String): Response<PropogatorNumberResponse> {
        return getApi.getPropogatorNumber(id)
    }

    suspend fun getPropogatorProduct(id: String): Response<PropogatorProductResponse> {
        return getApi.getPropogatorProduct(id)
    }

    suspend fun getPropogatorDate(id: String, month: String): Response<PropogatorDateResponse> {
        return getApi.getPropogatorDate(id, month)
    }

    suspend fun getPropogatorProductDetail(): Response<PropogatorProductDetailResponse> {
        return getApi.getPropogatorProductDetail()
    }

    suspend fun getPropogatorRocket(id: String): Response<PropogatorRocketResponse> {
        return getApi.getPropogatorRocket(id)
    }

    suspend fun getLeaderBoard(
        offset: String,
        limit: String,
    ): Response<LeaderBoardResponse> {
        return RetrofitClient().getApi().getLeaderBoard(offset, limit)

    }
    suspend fun getQuestion(): Response<QuestionResponse> {
        return getApi.getQuestion()
    }
}