package main

import (
	"github.com/stretchr/testify/assert"
	"net/http"
	"net/http/httptest"
	"testing"
)

func TestPingRoute(t *testing.T) {
	route := setUpRoute()
	recorder := httptest.NewRecorder()
	request, _ := http.NewRequest("GET", "/ping", nil)
	route.ServeHTTP(recorder, request)

	assert.Equal(t, 200, recorder.Code)
	assert.Equal(t, "pong", recorder.Body.String())

}
