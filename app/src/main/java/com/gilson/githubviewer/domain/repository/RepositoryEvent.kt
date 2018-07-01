package com.gilson.githubviewer.domain.repository

data class RepositoryEvent(val state: RepositoryState, val data: List<Repository>? = null, val error: Throwable? = null)