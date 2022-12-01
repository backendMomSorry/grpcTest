package utils

import java.time.format.DateTimeFormatter

var threadLocalFormatter: ThreadLocal<DateTimeFormatter> =
    ThreadLocal.withInitial { DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'") }