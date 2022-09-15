package com.sampong.graphql.util

import mu.KotlinLogging

private val loggers = KotlinLogging.logger {  }
class Logger {
	fun logDebug(mes: String){
		loggers.debug { mes }
	}
	fun logInfo(mes: String){
		loggers.debug { mes }
	}
	fun logWarm(mes: String){
		loggers.debug { mes }
	}
	fun logError(mes: String){
		loggers.debug { mes }
	}
	
}