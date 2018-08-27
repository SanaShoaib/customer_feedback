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
import java.util.ArrayList;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 

 
import org.hibernate.HibernateException; 
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
public class ManageFeedback {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      
      try {
         factory = new AnnotationConfiguration().
                   configure().
                   //addPackage("com.xyz") //add package if used.
                   addAnnotatedClass(feedback.class).
                   buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      ManageFeedback MF = new ManageFeedback();

     
      MF.listCustomers();

      //call webservice
      
      
      //MF.updateCustomers();

      
      
     // MF.listCustomers();
   }
   
 
   
   public void listCustomers( ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         ArrayList<feedback> Customers = (ArrayList<feedback>)session.createQuery("FROM feedback WHERE status='NULL'").list(); 
         for (feedback customers:Customers){
             
            System.out.print("ID: " + customers.getId());
            System.out.print("  Customer Number: " + customers.getCustNo());
            System.out.print("  Message: " + customers.getMessage()); 
            System.out.print("  Status: " + customers.getStatus()); 
            System.out.println("  Response: " + customers.getResponse()); 
            
            
            
               
                
            
            
         }
         
                for(feedback customers1:Customers)
                {
                       
                        //call webservice
                        updateCustomers(customers1.getId());
                        
                    
                }
         
         
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   
   public void updateCustomers(int Id ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("UPDATE feedback set status = 'Sent' WHERE id= "+Id);
        int result= query.executeUpdate();
         System.out.println("Rows affected: " + result);
		 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
  
}
