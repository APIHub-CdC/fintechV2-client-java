package io.RCCPM.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;


public class Data {
  @SerializedName("score")
  private Score score = null;
  public Data scoreNoHit(Score score) {
    this.score = score;
    return this;
  }
   
  @ApiModelProperty(value = "")
  public Score getScore() {
    return score;
  }
  public void setScore(Score score) {
    this.score = score;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Data data = (Data) o;
    return Objects.equals(this.score, data.score);
  }
  @Override
  public int hashCode() {
    return Objects.hash(score);
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Data {\n");
    
    sb.append("    scoreNoHit: ").append(toIndentedString(score)).append("\n");
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
