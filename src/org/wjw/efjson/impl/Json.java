/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wjw.efjson.impl;


import java.text.SimpleDateFormat;

import org.wjw.efjson.DecodeException;
import org.wjw.efjson.EncodeException;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Json {

  private final static ObjectMapper mapper = new ObjectMapper();
  private final static ObjectMapper prettyMapper = new ObjectMapper();

  static {
    // Non-standard JSON but we allow C style comments in our JSON
    mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);

    mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    //Bean->JSON
    mapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, true);
    mapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
    mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
    mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
    mapper.configure(MapperFeature.USE_STATIC_TYPING, false);
    
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z"));  //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
    mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false);
    //mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    //JSON->Bean
    mapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, true);
    mapper.configure(MapperFeature.AUTO_DETECT_SETTERS, false);
    mapper.configure(MapperFeature.AUTO_DETECT_CREATORS, true);
    mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
    mapper.configure(MapperFeature.USE_GETTERS_AS_SETTERS, false);
    mapper.configure(MapperFeature.USE_ANNOTATIONS, false);

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // If you want to access private fields, you need to play with the Visibility by adding the following line:
    mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(Visibility.ANY));
  }

  public static String encode(Object obj) throws EncodeException {
    try {
      return mapper.writeValueAsString(obj);
    }
    catch (Exception e) {
      throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
    }
  }

  public static String encodePrettily(Object obj) throws EncodeException {
    try {
      return prettyMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new EncodeException("Failed to encode as JSON: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T decodeValue(String str, Class<?> clazz) throws DecodeException {
    try {
      return (T)mapper.readValue(str, clazz);
    }
    catch (Exception e) {
      throw new DecodeException("Failed to decode:" + e.getMessage());
    }
  }

  static {
    prettyMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);

    prettyMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    prettyMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    prettyMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    //Bean->JSON
    prettyMapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, true);
    prettyMapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
    prettyMapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
    prettyMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
    prettyMapper.configure(MapperFeature.USE_STATIC_TYPING, false);
    
    prettyMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z"));  //prettyMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    prettyMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    prettyMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    prettyMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false);
    //prettyMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    //JSON->Bean
    prettyMapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, true);
    prettyMapper.configure(MapperFeature.AUTO_DETECT_SETTERS, false);
    prettyMapper.configure(MapperFeature.AUTO_DETECT_CREATORS, true);
    prettyMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
    prettyMapper.configure(MapperFeature.USE_GETTERS_AS_SETTERS, false);
    prettyMapper.configure(MapperFeature.USE_ANNOTATIONS, false);

    prettyMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // If you want to access private fields, you need to play with the Visibility by adding the following line:
    prettyMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(Visibility.ANY));
  }

}
