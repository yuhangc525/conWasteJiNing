package cn.edu.bjtu.jzlj.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-10-10 14:25
 **/
public class JsonUtil {
    /***/
    private static ObjectMapper mapper = null ;

    private JsonUtil()
    {
    }

    /**
     * Object转Json
     *
     * @param obj
     *            Object
     * @return String
     */
    public static String objToJson( Object obj )
    {
        String json = null ;

        if ( null != obj )
        {
            ObjectMapper mapper = JsonUtil.getObjectMapper() ;

            try
            {
                json = mapper.writeValueAsString( obj ) ;
            }
            catch ( JsonGenerationException e )
            {
                e.printStackTrace() ;
            }
            catch ( JsonMappingException e )
            {
                e.printStackTrace() ;
            }
            catch ( IOException e )
            {
                e.printStackTrace() ;
            }
        }
        return json ;
    }

    /**
     * Json转Object
     *
     * @param json
     *            String
     * @param clazz
     *            Class
     * @return Object
     */
    public static Object jsonToObj( String json, Class< ? > clazz )
    {
        Object obj = null ;

        if ( null != json && !"".equals( json.trim() ) )
        {
            ObjectMapper mapper = JsonUtil.getObjectMapper() ;

            try
            {
                obj = mapper.readValue( json, clazz ) ;
            }
            catch ( JsonParseException e )
            {
                e.printStackTrace() ;
            }
            catch ( JsonMappingException e )
            {
                e.printStackTrace() ;
            }
            catch ( IOException e )
            {
                e.printStackTrace() ;
            }
        }
        return obj ;
    }

    /**
     * 获取ObjectMapper
     *
     * @return ObjectMapper
     */
    public static synchronized ObjectMapper getObjectMapper()
    {
        if ( null == mapper )
        {
            mapper = new ObjectMapper() ;

           // mapper.setSerializationInclusion() ;
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return mapper ;
    }
}