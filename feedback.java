/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedback_annotation;

/**
 *
 * @author Sana
 */
import javax.persistence.*;

@Entity
@Table(name = "FEEDBACK")
public class feedback {
   @Id @GeneratedValue
   @Column(name = "id")
   private int id;

   @Column(name = "cust_no")
   private String cust_no;

   @Column(name = "message")
   private String message;

   @Column(name = "status")
   private String status;
   
   @Column(name = "response")
   private String response;

   public feedback() {}
   
   public int getId() {
      return id;
   }
   
   public void setId( int id ) {
      this.id = id;
   }
   
   public String getCustNo() {
      return cust_no;
   }
   
   public void setCustNo( String cust_no ) {
      this.cust_no = cust_no;
   }
   
   public String getMessage() {
      return message;
   }
   
   public void setMessage( String message ) {
      this.message = message;
   }
   
   public String getStatus() {
      return status;
   }
   
   public void setStatus( String status ) {
      this.status = status;
   }
   
    public String getResponse() {
      return response;
   }
   
   public void setResponse( String response ) {
      this.response = response;
   }
}