package com.FlexPay.FlexPay.exception;

import com.FlexPay.FlexPay.dto.response.ErrorResponse;
import com.FlexPay.FlexPay.dto.response.TransactionDetailResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildErrorResponse(HttpStatus status, String message , HttpServletRequest request){

        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(LocalDateTime.now());

        error.setStatus(status.value());

        error.setError(status.getReasonPhrase());

        error.setMessage(message);

        error.setPath(request.getRequestURI());

        return  error;

    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists
            (EmailAlreadyExistsException ex , HttpServletRequest request) {


        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                buildErrorResponse(HttpStatus.CONFLICT,
                         ex.getMessage(),
                        request)
        );
    }

    @ExceptionHandler(PhoneNumberAlreadyExists.class)
            public ResponseEntity<ErrorResponse> handlePhoneNumberAlreadyExists(
                    PhoneNumberAlreadyExists ex , HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        buildErrorResponse(
                                HttpStatus.CONFLICT,
                                ex.getMessage(),
                                request
                        )
                );
        }


    @ExceptionHandler(InvalidLoginCredentialException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoginCredential(
            InvalidLoginCredentialException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                buildErrorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),request)
        );
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalance(
            InsufficientBalanceException ex , HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWalletNotFound(WalletNotFoundException ex , HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(HttpStatus.NOT_FOUND,
                ex.getMessage(),request));
    }

    @ExceptionHandler(SelfTransferException.class)
    public ResponseEntity<ErrorResponse> handleSelfTransfer(SelfTransferException ex , HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildErrorResponse(HttpStatus.CONFLICT,
                ex.getMessage(),request));
    }
    @ExceptionHandler(IllegalAmountException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAmount(
            IllegalAmountException ex , HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTransactionNotFound(
            TransactionNotFoundException ex , HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(
                buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        message,
                        request
                )
        );
    }



   // for all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception ex,
            HttpServletRequest request){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        buildErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong ",
                                request
                        )
                );
    }





}
