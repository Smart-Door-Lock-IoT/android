# ControlApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**triggerFingerprintMode**](ControlApi.md#triggerFingerprintMode) | **POST** /api/v1/control/fingerprint-mode |  |
| [**triggerOpenDoor**](ControlApi.md#triggerOpenDoor) | **POST** /api/v1/control/open-door |  |
| [**triggerRFIDMode**](ControlApi.md#triggerRFIDMode) | **POST** /api/v1/control/rfid-mode |  |


<a id="triggerFingerprintMode"></a>
# **triggerFingerprintMode**
> TriggerFingerprintModeResponse triggerFingerprintMode()



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
try {
    val result : TriggerFingerprintModeResponse = apiInstance.triggerFingerprintMode()
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
This endpoint does not need any parameter.

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
> TriggerRFIDModeResponse triggerRFIDMode()



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ControlApi()
try {
    val result : TriggerRFIDModeResponse = apiInstance.triggerRFIDMode()
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
This endpoint does not need any parameter.

### Return type

[**TriggerRFIDModeResponse**](TriggerRFIDModeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

