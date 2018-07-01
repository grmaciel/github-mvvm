package com.gilson.githubviewer.data.network.repository

import com.google.gson.annotations.SerializedName

data class GithubResponse(val id: Int,
                          val name: String,
                          val fullName: String,
                          val description: String?,
                          val owner: GithubOwnerResponse,
                          @SerializedName("forks_count") val forkCount: Int?,
                          @SerializedName("stargazers_count") val starsCount: Int?)

data class GithubOwnerResponse(@SerializedName("avatar_url") val avatarUrl: String)