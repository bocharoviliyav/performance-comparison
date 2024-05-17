FROM golang:1.22.3-alpine3.19 as build
WORKDIR /app
ADD . .
RUN CGO_ENABLED=0 GOOS=linux GIN_MODE=release go build -a -installsuffix cgo -o main .

FROM scratch
COPY --from=build /app/main /app/main
COPY --from=build /app/personApp/resources/local.yaml /app/local.yaml
ENV CONFIG_PATH=/app/local.yaml
CMD ["app/main"]
EXPOSE 8080