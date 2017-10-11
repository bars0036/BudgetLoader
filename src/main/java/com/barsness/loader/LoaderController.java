package com.barsness.loader;

import com.barsness.loader.domain.TransactionLoadResponse;
import com.barsness.loader.service.LoaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by matt.barsness on 10/5/17.
 */

@RestController
@RequestMapping("/load")
public class LoaderController {

    private final LoaderService loaderService;

    public LoaderController(LoaderService loaderService) {
        this.loaderService = loaderService;
    }

    @RequestMapping(value = "/transaction", method= RequestMethod.GET)
    public TransactionLoadResponse loadTransactions(@RequestParam String fileName){
        //TODO: Write Method
        return loaderService.loadTransactions(fileName);
    }

    @RequestMapping(value="/transaction", method=RequestMethod.POST)
    public ResponseEntity<TransactionLoadResponse> loadTransactions(@RequestParam MultipartFile file){
        TransactionLoadResponse response = loaderService.loadTransactions(file);
        if(response.getResponse() == "Success"){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
