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
import java.io.IOException;


public class RequestFolios {
  @SerializedName("folioConsulta")
  private String folioConsulta = null;
  @SerializedName("producto")
  private String producto = null;
  public RequestFolios folioConsulta(String folioConsulta) {
    this.folioConsulta = folioConsulta;
    return this;
  }
   
  @ApiModelProperty(example = "12345678", required = true, value = "folioO")
  public String getFolioConsulta() {
    return folioConsulta;
  }
  public void setFolioConsulta(String folioConsulta) {
    this.folioConsulta = folioConsulta;
  }
  public RequestFolios producto(String producto) {
    this.producto = producto;
    return this;
  }
   
  @ApiModelProperty(example = "prestamos", required = true, value = "producto")
  public String getProducto() {
    return producto;
  }
  public void setProducto(String producto) {
    this.producto = producto;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestFolios requestFolios = (RequestFolios) o;
    return Objects.equals(this.folioConsulta, requestFolios.folioConsulta) &&
        Objects.equals(this.producto, requestFolios.producto);
  }
  @Override
  public int hashCode() {
    return Objects.hash(folioConsulta, producto);
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestFolios {\n");
    
    sb.append("    folioConsulta: ").append(toIndentedString(folioConsulta)).append("\n");
    sb.append("    producto: ").append(toIndentedString(producto)).append("\n");
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
