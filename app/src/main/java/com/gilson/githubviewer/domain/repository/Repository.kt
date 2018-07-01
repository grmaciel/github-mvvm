package com.gilson.githubviewer.domain.repository

data class Repository(val id: Int,
                      val name: String,
                      val description: String?,
                      val forkCount: Int,
                      val avatarUrl: String)