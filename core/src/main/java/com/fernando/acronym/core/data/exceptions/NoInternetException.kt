package com.fernando.acronym.core.data.exceptions

class NotInternetException(
    override val message: String = "the internet connection is not available",
) : ApiException(message)
