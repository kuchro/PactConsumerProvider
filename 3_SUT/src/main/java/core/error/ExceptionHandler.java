package core.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ExceptionHandler {
    //private static final String DEFAULT_KEY_TIMESTAMP = "timestamp";
    private static final String DEFAULT_KEY_STATUS = "status";
    private static final String DEFAULT_KEY_ERROR = "error";
    private static final String DEFAULT_KEY_ERRORS = "errors";
    private static final String DEFAULT_KEY_MESSAGE = "message";


    public static final String KEY_STATUS = "status";
    public static final String KEY_ERROR = "error";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_DATE = "date";
    public static final String KEY_ERRORS = "errors";



    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String ,Object> getErrorAttributes(
                    WebRequest webRequest
                    ,boolean includeStackTrace
            ) {
                Map<String ,Object> defaultMap
                        = super.getErrorAttributes( webRequest ,
                        includeStackTrace );

                Map<String ,Object> errorAttributes = new LinkedHashMap<>();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                errorAttributes.put( KEY_STATUS, defaultMap.get( DEFAULT_KEY_STATUS ) );
                errorAttributes.put( KEY_MESSAGE ,"Not Found" );
                errorAttributes.put( KEY_ERROR ,"There is no such a element" );

                errorAttributes.put( KEY_DATE , sdf.format(date) );

                return errorAttributes;
            }
        };
    }
}
