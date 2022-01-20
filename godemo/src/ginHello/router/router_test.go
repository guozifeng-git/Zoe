package router

import (
	"github.com/stretchr/testify/assert"
	"net/http"
	"net/http/httptest"
	"testing"
)

func TestRouter(t *testing.T) {
	router := SetUpRoute()
	recorder := httptest.NewRecorder()
	request, _ := http.NewRequest(http.MethodGet, "/", nil)
	router.ServeHTTP(recorder, request)
	assert.Equal(t, http.StatusOK, recorder.Code)
	assert.Equal(t, "hello gin", recorder.Body.String())
}
