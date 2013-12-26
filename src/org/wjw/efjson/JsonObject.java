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

package org.wjw.efjson;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.wjw.efjson.impl.Base64;
import org.wjw.efjson.impl.Json;

/**
 * 
 * Represents a JSON object.<p>
 * Instances of this class are not thread-safe.<p>
 * 
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class JsonObject extends JsonElement {

  final Map<String, Object> map;

  /**
   * Create a JSON object based on the specified Map
   * 
   * @param map
   */
  public JsonObject(Map<String, Object> map) {
    this.map = map;
  }

  /**
   * Create an empty JSON object
   */
  public JsonObject() {
    this.map = new LinkedHashMap<String, Object>();
  }

  /**
   * Create a JSON object from a string form of a JSON object
   * 
   * @param jsonString
   *          The string form of a JSON object
   */
  public JsonObject(String jsonString) {
    map = Json.decodeValue(jsonString, Map.class);
  }

  public JsonObject putString(String fieldName, String value) {
    map.put(fieldName, value);
    return this;
  }

  public JsonObject putObject(String fieldName, JsonObject value) {
    map.put(fieldName, value == null ? null : value.map);
    return this;
  }

  public JsonObject putArray(String fieldName, JsonArray value) {
    map.put(fieldName, value.list);
    return this;
  }

  public JsonObject putElement(String fieldName, JsonElement value) {
    if(value.isArray()){
      return this.putArray(fieldName, value.asArray());
    }
    
    return this.putObject(fieldName, value.asObject());
  }

  public JsonObject putNumber(String fieldName, Number value) {
    map.put(fieldName, value);
    return this;
  }

  public JsonObject putBoolean(String fieldName, Boolean value) {
    map.put(fieldName, value);
    return this;
  }

  public JsonObject putBinary(String fieldName, byte[] binary) {
    map.put(fieldName, Base64.encodeBytes(binary));
    return this;
  }

  public JsonObject putValue(String fieldName, Object value) {
    if (value instanceof JsonObject) {
      putObject(fieldName, (JsonObject)value);
    } else if (value instanceof JsonArray) {
      putArray(fieldName, (JsonArray)value);
    } else {
      map.put(fieldName, value);
    }
    return this;
  }

  public String getString(String fieldName) {
    return (String) map.get(fieldName);
  }

  @SuppressWarnings("unchecked")
  public JsonObject getObject(String fieldName) {
    Map<String, Object> m = (Map<String, Object>) map.get(fieldName);
    return m == null ? null : new JsonObject(m);
  }

  @SuppressWarnings("unchecked")
  public JsonArray getArray(String fieldName) {
    List<Object> l = (List<Object>) map.get(fieldName);
    return l == null ? null : new JsonArray(l);
  }

  public JsonElement getElement(String fieldName) {
    Object element = map.get(fieldName);
    if (element instanceof Map<?,?>){
      return getObject(fieldName);
    }
    if (element instanceof List<?>){
      return getArray(fieldName);
    }
    throw new ClassCastException();
  }

  public Number getNumber(String fieldName) {
    return (Number) map.get(fieldName);
  }

  public Long getLong(String fieldName) {
    Number num = (Number) map.get(fieldName);
    return num == null ? null : num.longValue();
  }

  public Integer getInteger(String fieldName) {
    Number num = (Number) map.get(fieldName);
    return num == null ? null : num.intValue();
  }

  public Boolean getBoolean(String fieldName) {
    return (Boolean) map.get(fieldName);
  }

  public byte[] getBinary(String fieldName) {
    String encoded = (String) map.get(fieldName);
    return Base64.decode(encoded);
  }

  public String getString(String fieldName, String def) {
    String str = getString(fieldName);
    return str == null ? def : str;
  }

  public JsonObject getObject(String fieldName, JsonObject def) {
    JsonObject obj = getObject(fieldName);
    return obj == null ? def : obj;
  }

  public JsonArray getArray(String fieldName, JsonArray def) {
    JsonArray arr = getArray(fieldName);
    return arr == null ? def : arr;
  }

  public JsonElement getElement(String fieldName, JsonElement def) {
    JsonElement elem = getElement(fieldName);
    return elem == null ? def : elem;
  }

  public boolean getBoolean(String fieldName, boolean def) {
    Boolean b = getBoolean(fieldName);
    return b == null ? def : b;
  }

  public Number getNumber(String fieldName, int def) {
    Number n = getNumber(fieldName);
    return n == null ? def : n;
  }

  public Long getLong(String fieldName, long def) {
    Number num = (Number) map.get(fieldName);
    return num == null ? def : num.longValue();
  }

  public Integer getInteger(String fieldName, int def) {
    Number num = (Number) map.get(fieldName);
    return num == null ? def : num.intValue();
  }

  public byte[] getBinary(String fieldName, byte[] def) {
    byte[] b = getBinary(fieldName);
    return b == null ? def : b;
  }

  public Set<String> getFieldNames() {
    return map.keySet();
  }

  @SuppressWarnings("unchecked")
  public <T> T getValue(String fieldName) {
    Object obj = map.get(fieldName);
    if (obj != null) {
      if (obj instanceof Map) {
        obj = new JsonObject((Map)obj);
      } else if (obj instanceof List) {
        obj = new JsonArray((List)obj);
      }
    }
    return (T)obj;
  }

  @SuppressWarnings("unchecked")
  public <T> T getField(String fieldName) {
    Object obj = map.get(fieldName);
    if (obj instanceof Map) {
      obj = new JsonObject((Map)obj);
    } else if (obj instanceof List) {
      obj = new JsonArray((List)obj);
    }
    return (T)obj;
  }

  public Object removeField(String fieldName) {
    return map.remove(fieldName) != null;
  }

  public int size() {
    return map.size();
  }

  public JsonObject mergeIn(JsonObject other) {
    map.putAll(other.map);
    return this;
  }

  /** 
   * 输出成JSON格式的字符串
   * @return JSON字符串
   */
  public String encode() {
    return Json.encode(this.map);
  }

  /**
   * 输出成易读格式的JSON格式的字符串
   */
  public String encodePrettily() {  //@wjw_add
    return Json.encodePrettily(this.map);
  }
  
  public JsonObject copy() {
    return new JsonObject(encode());
  }

  @Override
  public String toString() {
    return encode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    JsonObject that = (JsonObject) o;

    if (this.map.size() != that.map.size())
      return false;

    for (Map.Entry<String, Object> entry : this.map.entrySet()) {
      Object val = entry.getValue();
      if (val == null) {
        if (that.map.get(entry.getKey()) != null) {
          return false;
        }
      } else {
        if (!entry.getValue().equals(that.map.get(entry.getKey()))) {
          return false;
        }
      }
    }
    return true;
  }

  public Map<String, Object> toMap() {
    return convertMap(map);
  }

  @SuppressWarnings("unchecked")
  static Map<String, Object> convertMap(Map<String, Object> map) {
    Map<String, Object> converted = new LinkedHashMap<String, Object>(map.size());
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      Object obj = entry.getValue();
      if (obj instanceof Map) {
        Map<String, Object> jm = (Map<String, Object>) obj;
        converted.put(entry.getKey(), convertMap(jm));
      } else if (obj instanceof List) {
        List<Object> list = (List<Object>) obj;
        converted.put(entry.getKey(), JsonArray.convertList(list));
      } else {
        converted.put(entry.getKey(), obj);
      }
    }
    return converted;
  }

  /**
   * 把JSON格式的字符串,转换成Java对象
   * @param <T>
   *          泛型声明
   * @param json
   *          JSON字符串
   * @param clzz
   *          要转换对象的类型
   * @return
   * @throws DecodeException
   */
  public static <T> T fromJson(String json, Class<T> clzz) throws DecodeException {
    return Json.decodeValue(json, clzz);
  }

  /**
   * 把JSON格式的字符串,转换成Java的集合对象(List,Map)对象
   * @param <T>
   *          泛型声明
   * @param json
   *          JSON字符串
   * @param collectionClass
   *          要转换集合的类型
   * @param elementClasses
   *          要转换元素的类型
   * @return
   * @throws DecodeException
   */
  public static <T> T fromJson(String json, Class<T> collectionClass,Class<?>... elementClasses) throws DecodeException {
    return Json.decodeCollectionValue(json, collectionClass,elementClasses);
  }
  
  /**
   * 把JSON格式的字符串,转换成Java对象
   * @param <T>
   *          泛型声明
   * @param json
   *          JSON字符串
   * @param valueTypeRef
   *          针对集合泛型的类型引用类
   * @return
   * @throws DecodeException
   */
  public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) throws DecodeException {
    return Json.decodeValue(json, valueTypeRef);
  }
  
  /** 
   * 把Java对象输出成JSON格式的字符串
   * @param <T>
   *          泛型声明
   * @param bean
   *          类的实例
   * @return JSON字符串
   */
  public static <T> String toJson(T bean) throws EncodeException {
    return Json.encode(bean);
  }

  /**
   * 把Java对象输出成易读格式的JSON格式的字符串
   * @param <T>
   *          泛型声明
   * @param bean
   *          类的实例
   * @return JSON字符串
   */
  public static <T> String toJsonPrettily(T bean) throws EncodeException {
    return Json.encodePrettily(bean);
  }
}
