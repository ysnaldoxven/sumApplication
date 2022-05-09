package com.example.sumapplication.interfaces.controller;


import com.example.sumapplication.model.SumRequestBody;
import com.example.sumapplication.model.SumResponseBody;
import com.example.sumapplication.model.SumResult;
import com.example.sumapplication.service.RequestService;
import com.example.sumapplication.service.ResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/sums", produces = "application/json")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class SumController {

    @Value("${endpointName.requestParam}")
    private String endpointNameRequestParam;

    @Value("${endpointName.pathVariable}")
    private String endpointNamePathVariable;

    @Value("${endpointName.requestBody}")
    private String endpointNameRequestBody;

    public SumController(RequestService requestService, ResponseService responseService) {
        this.requestService = requestService;
        this.responseService = responseService;
    }

    private final RequestService requestService;

    private final ResponseService responseService;

    @PostMapping(value = "/requestParam.postSum", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithRequestParam(@RequestParam int numberOne, @RequestParam int numberTwo) throws JsonProcessingException {
        String endpoint = endpointNameRequestParam;
        SumResponseBody result = requestService.saveRequest(endpoint, numberOne, numberTwo);
        return new ResponseEntity<>(new SumResult(result.getResultSum()), HttpStatus.OK);
    }

    @PostMapping(value = "/pathVariable.postSum/{numberOne}/{numberTwo}", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithPathVariable(@PathVariable("numberOne") int numberOne, @PathVariable("numberTwo") int numberTwo) throws JsonProcessingException {
        String endpoint = endpointNamePathVariable;
        SumResponseBody result = requestService.saveRequest(endpoint, numberOne, numberTwo);
        return new ResponseEntity<>(new SumResult(result.getResultSum()), HttpStatus.OK);
    }

    @PostMapping(value = "/requestBody.postSum", produces = "application/json")
    public ResponseEntity<SumResult> postSumWithRequestBody(@RequestBody SumRequestBody sumRequestBody) throws JsonProcessingException {
        String endpoint = endpointNameRequestBody;
        SumResponseBody result = requestService.saveRequestWithBodyRequest(endpoint, sumRequestBody);
        return new ResponseEntity<>(new SumResult(result.getResultSum()), HttpStatus.OK);
    }

    @GetMapping(value = "/requestParam.getSumResponseBody", produces = "application/json")
    public ResponseEntity<SumResponseBody> findSumResponseBody(@RequestParam int idResponse) throws JsonProcessingException {
        //Poner restriccion de retorno cuando no se encuentra ningun objecto!!
        return new ResponseEntity<>(responseService.findResponse(idResponse), HttpStatus.OK);
    }

}