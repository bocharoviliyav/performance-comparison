FROM golang:latest
WORKDIR /app
ADD . .
RUN go build -o main .
CMD ["/app/main"]
EXPOSE 8080