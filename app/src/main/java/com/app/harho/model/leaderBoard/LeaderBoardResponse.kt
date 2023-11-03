package com.app.harho.model.leaderBoard

data class LeaderBoardResponse(
    val body: Body,
    val code: Int,
    val message: String,
    val success: Boolean
) {
    data class Body(
        val allScoresInfo: List<AllScoresInfo>,
        val userinfo: Userinfo
    ) {
//        data class AllScoresInfo(
//            val id: Int,
//            val name: String?,
//            val userscores: String?
//        )

        data class Userinfo(
            val email: String,
            val id: Int,
            val name: String,
            val display_name: String,
            val userscores: String
        )
    }
}
class AllScoresInfo{
            var id: Int?=0
            var name: String?=null
            var display_name: String?=null
            var userscores: String?=null

    constructor(
        id: Int?,
        name: String?,
        userscores: String?){
        this.id = id
        this.name = name
        this.userscores = userscores
    }
}