PROJECT_ROOT := $(shell cmd /c cd)

download-swagger:
	curl -o swagger.json http://localhost:8080/swagger/doc.json

delete-old-generated:
	@if exist "${PROJECT_ROOT}\\openapi" ( rmdir /s /q "${PROJECT_ROOT}\\openapi" ) else ( echo "Directory does not exist. Skipping deletion." )

api: delete-old-generated download-swagger
	docker run --rm \
      -v ${PROJECT_ROOT}:/local openapitools/openapi-generator-cli generate \
      -i /local/swagger.json \
      -g kotlin \
      -o /local/openapi \
      --additional-properties=library=jvm-ktor,packageName=id.my.smartdoorlock.openapi,serializationLibrary=gson \
      --global-property=apiTests=false,modelTests=false

.PHONY: download-swagger api