#!/bin/bash

door_array=$(curl -s -X POST http://localhost:8081/doors/1 | jq -r '.doors')

expected_response='[
  {
    "number": 1,
    "state": "OPEN"
  }
]'

if test "$door_array" = "$expected_response"; then
  echo "Test succeeded: door array matches expected response."
else
  echo "Test failed: door array does not match expected response."
fi