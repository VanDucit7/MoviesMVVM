package com.vn.fsoft.gstlib.core.network

import java.io.IOException

class ApiException(code: Int, message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)