package io.RCCPM.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.RCCPM.client.model.Data;
import java.io.IOException;


public class ResponseScore {
  @SerializedName("httpStatus")
  private Double httpStatus = null;
  @SerializedName("success")
  private String success = null;
  @SerializedName("message")
  private String message = null;
  @SerializedName("data")
  private Data data = null;
  public ResponseScore httpStatus(Double httpStatus) {
    this.httpStatus = httpStatus;
    return this;
  }
   
  @ApiModelProperty(value = "estado")
  public Double getHttpStatus() {
    return httpStatus;
  }
  public void setHttpStatus(Double httpStatus) {
    this.httpStatus = httpStatus;
  }
  public ResponseScore success(String success) {
    this.success = success;
    return this;
  }
   
  @ApiModelProperty(value = "success")
  public String getSuccess() {
    return success;
  }
  public void setSuccess(String success) {
    this.success = success;
  }
  public ResponseScore message(String message) {
    this.message = message;
    return this;
  }
   
  @ApiModelProperty(value = "message")
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public ResponseScore data(Data data) {
    this.data = data;
    return this;
  }
   
  @ApiModelProperty(value = "")
  public Data getData() {
    return data;
  }
  public void setData(Data data) {
    this.data = data;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseScore responseScore = (ResponseScore) o;
    return Objects.equals(this.httpStatus, responseScore.httpStatus) &&
        Objects.equals(this.success, responseScore.success) &&
        Objects.equals(this.message, responseScore.message) &&
        Objects.equals(this.data, responseScore.data);
  }
  @Override
  public int hashCode() {
    return Objects.hash(httpStatus, success, message, data);
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseScore {\n");
    
    sb.append("    httpStatus: ").append(toIndentedString(httpStatus)).append("\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
