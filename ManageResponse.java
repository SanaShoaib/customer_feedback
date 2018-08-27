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
import java.util.Scanner;
 
import org.hibernate.HibernateException; 
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class ManageResponse {
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
      
      ManageResponse MR = new ManageResponse();
     
        
       MR.listCustomers();
       
       
       
                
     
    
      
     
      
      
      
     // MR.listCustomers();
   }
   
 
   
   public void listCustomers( ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         ArrayList<feedback> Customers = (ArrayList<feedback>)session.createQuery("FROM feedback WHERE status='Sent' AND response='NULL'").list();
         for (feedback customers:Customers){
            
            System.out.print("ID: " + customers.getId()); 
            System.out.print("  Customer Number: " + customers.getCustNo());
            System.out.print("  Message: " + customers.getMessage()); 
            System.out.print("  Status: " + customers.getStatus()); 
            System.out.println("  Response: " + customers.getResponse()); 
         }
         
                Scanner input = new Scanner (System.in);
                
                for(feedback customers1:Customers)
                {
                       
                        
                        System.out.println();
                        System.out.println("enter contact number: ");
                        String cust_no = input.nextLine();
                        System.out.println("enter response (s/ns): ");
                        String response = input.nextLine();
                        response=response.toLowerCase();

                        updateCustomers(customers1.getId() , response);
                        
                    
                }
                
                
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   
   public void updateCustomers( int id, String response){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         Query query = session.createQuery("UPDATE feedback set response = '"+response+"'  WHERE id=" + id);
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
