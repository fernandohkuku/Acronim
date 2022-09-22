package com.fernando.acronym.core.infrastructure.handlers.error

import com.fernando.acronym.core.data.exceptions.BadRequestException
import com.fernando.acronym.core.data.exceptions.NotFoundException
import com.fernando.acronym.core.data.exceptions.ServerException
import com.squareup.moshi.Moshi
import java.io.Reader
import java.net.HttpURLConnection

class ApiErrorHandler : ErrorHandler{
    override fun throwFromCode(errorCode: Int, reader: Reader?) {
        when(errorCode){
            HttpURLConnection.HTTP_BAD_REQUEST -> {
                throw BadRequestException("we have a problem processing your entry")
            }

            HttpURLConnection.HTTP_NOT_FOUND -> {
                throw  NotFoundException("this acronym doesn't exist")
            }
            HttpURLConnection.HTTP_INTERNAL_ERROR ->{
                throw ServerException("Sorry, there was a problem in the server. Try again later.")
            }
        }
    }
}