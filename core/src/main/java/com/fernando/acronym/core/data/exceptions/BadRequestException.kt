package com.fernando.acronym.core.data.exceptions

class BadRequestException(
    override val message: String
) : ApiException(message)