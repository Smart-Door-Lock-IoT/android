# ConfigurationApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**changePin**](ConfigurationApi.md#changePin) | **PUT** /api/v1/configuration/pin |  |
| [**validatePin**](ConfigurationApi.md#validatePin) | **POST** /api/v1/configuration/pin |  |


<a id="changePin"></a>
# **changePin**
> ChangePinResponse changePin(request)



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ConfigurationApi()
val request : ChangePinRequest =  // ChangePinRequest | body
try {
    val result : ChangePinResponse = apiInstance.changePin(request)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ConfigurationApi#changePin")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ConfigurationApi#changePin")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **request** | [**ChangePinRequest**](ChangePinRequest.md)| body | |

### Return type

[**ChangePinResponse**](ChangePinResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="validatePin"></a>
# **validatePin**
> ValidatePinResponse validatePin(request)



### Example
```kotlin
// Import classes:
//import id.my.smartdoorlock.openapi.infrastructure.*
//import id.my.smartdoorlock.openapi.models.*

val apiInstance = ConfigurationApi()
val request : ValidatePinRequest =  // ValidatePinRequest | body
try {
    val result : ValidatePinResponse = apiInstance.validatePin(request)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ConfigurationApi#validatePin")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ConfigurationApi#validatePin")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **request** | [**ValidatePinRequest**](ValidatePinRequest.md)| body | |

### Return type

[**ValidatePinResponse**](ValidatePinResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

