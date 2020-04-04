package vn.mrlongg71.vnfood.src.utils;


import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import vn.mrlongg71.vnfood.src.model.ErrorResponse;
import vn.mrlongg71.vnfood.src.network.APIVnFood;

public class ErrorUtils {
    public static ErrorResponse parseError(Response<?> response){
        Converter<ResponseBody, ErrorResponse> converter = APIVnFood.getAPIVnFood()
                .responseBodyConverter(ErrorResponse.class,new Annotation[0]);
        ErrorResponse errorResponse;
        try {
            assert response.errorBody() != null;
            errorResponse = converter.convert(response.errorBody());
        }catch (IOException e){
            return new ErrorResponse();
        }
        return errorResponse;
    }
}
