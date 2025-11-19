# ControlApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**deleteAllLogs**](ControlApi.md#deleteAllLogs) | **DELETE** /api/v1/control/logs |  |
| [**getAllLatestLogs**](ControlApi.md#getAllLatestLogs) | **GET** /api/v1/control/logs/latest |  |
| [**getAllLogs**](ControlApi.md#getAllLogs) | **GET** /api/v1/control/logs |  |
| [**triggerBuzzerAlarm**](ControlApi.md#triggerBuzzerAlarm) | **POST** /api/v1/control/buzzer-alarm |  |
| [**triggerFingerprintMode**](ControlApi.md#triggerFingerprintMode) | **POST** /api/v1/control/fingerprint-mode |  |
| [**triggerOpenDoor**](ControlApi.md#triggerOpenDoor) | **POST** /api/v1/control/open-door |  |
| [**triggerRFIDMode**](ControlApi.md#triggerRFIDMode) | **POST** /api/v1/control/rfid-mode |  |


<a id="deleteAllLogs"></a>
# **deleteAllLogs**
> DeleteAllLogsResponse deleteAllLogs()



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
try {
    val result : DeleteAllLogsResponse = apiInstance.deleteAllLogs()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ControlApi#deleteAllLogs")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ControlApi#deleteAllLogs")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**DeleteAllLogsResponse**](DeleteAllLogsResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllLatestLogs"></a>
# **getAllLatestLogs**
> GetAllLatestLogsResponse getAllLatestLogs()



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
try {
    val result : GetAllLatestLogsResponse = apiInstance.getAllLatestLogs()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ControlApi#getAllLatestLogs")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ControlApi#getAllLatestLogs")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetAllLatestLogsResponse**](GetAllLatestLogsResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllLogs"></a>
# **getAllLogs**
> GetAllLogsResponse getAllLogs()



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
try {
    val result : GetAllLogsResponse = apiInstance.getAllLogs()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ControlApi#getAllLogs")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ControlApi#getAllLogs")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetAllLogsResponse**](GetAllLogsResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="triggerBuzzerAlarm"></a>
# **triggerBuzzerAlarm**
> TriggerBuzzerAlarmResponse triggerBuzzerAlarm()



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
try {
    val result : TriggerBuzzerAlarmResponse = apiInstance.triggerBuzzerAlarm()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ControlApi#triggerBuzzerAlarm")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ControlApi#triggerBuzzerAlarm")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**TriggerBuzzerAlarmResponse**](TriggerBuzzerAlarmResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="triggerFingerprintMode"></a>
# **triggerFingerprintMode**
> TriggerFingerprintModeResponse triggerFingerprintMode(body)



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
val body : TriggerFingerprintModeRequest =  // TriggerFingerprintModeRequest | body
try {
    val result : TriggerFingerprintModeResponse = apiInstance.triggerFingerprintMode(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ControlApi#triggerFingerprintMode")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ControlApi#triggerFingerprintMode")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**TriggerFingerprintModeRequest**](TriggerFingerprintModeRequest.md)| body | |

### Return type

[**TriggerFingerprintModeResponse**](TriggerFingerprintModeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="triggerOpenDoor"></a>
# **triggerOpenDoor**
> TriggerOpenDoorResponse triggerOpenDoor()



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
try {
    val result : TriggerOpenDoorResponse = apiInstance.triggerOpenDoor()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ControlApi#triggerOpenDoor")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ControlApi#triggerOpenDoor")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**TriggerOpenDoorResponse**](TriggerOpenDoorResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="triggerRFIDMode"></a>
# **triggerRFIDMode**
> TriggerRFIDModeResponse triggerRFIDMode(body)



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
val body : TriggerRFIDModeRequest =  // TriggerRFIDModeRequest | body
try {
    val result : TriggerRFIDModeResponse = apiInstance.triggerRFIDMode(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ControlApi#triggerRFIDMode")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ControlApi#triggerRFIDMode")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**TriggerRFIDModeRequest**](TriggerRFIDModeRequest.md)| body | |

### Return type

[**TriggerRFIDModeResponse**](TriggerRFIDModeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

