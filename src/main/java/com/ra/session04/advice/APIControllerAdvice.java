package com.ra.session04.advice;

import com.ra.session04.exception.DataExistException;
import com.ra.session04.model.dto.response.DataError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class APIControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // trang thai cua response tra ve. Thay vi tra ve responseEntity nhu ben Controller thi dung cach nay cung tuong tu
    public DataError<Map<String,String>> handleError(MethodArgumentNotValidException e) { // MethodArgumentNotValidException: Đối số đầu vào của phương thức. Đây là ngoại lệ (exception) được ném ra khi có lỗi xảy ra trong quá trình xác thực tham số đầu vào của một phương thức
        Map<String,String> map = new HashMap<>();
        for(FieldError error : e.getFieldErrors()){ // e.getFieldErrors: Phương thức này trả về danh sách các lỗi thuộc loại FieldError, mỗi lỗi đại diện cho một trường không hợp lệ trong dữ liệu đầu vào.
            map.put(error.getField(),error.getDefaultMessage());
            //error.getFiled(): Lấy tên của trường dữ liệu mà lỗi xảy ra.
            //error.getDefaultMessage(): Lấy thông điệp lỗi mô tả lý do trường đó không hợp lệ.
        }
        return new DataError<>(map, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handleErrNotFound(NoSuchElementException e) {
        return new DataError<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handleErrorExist(DataExistException e) {
        Map<String,String> map = new HashMap<>();
        map.put(e.getField(),e.getMessage());
        return new DataError<>(map, HttpStatus.BAD_REQUEST);
    }
}
