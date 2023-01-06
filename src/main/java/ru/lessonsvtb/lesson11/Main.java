package ru.lessonsvtb.lesson11;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    static Session session = null;
    static SessionFactory factory = null;

    public static void main(String[] args) {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = true;
            while (exit) {
                System.out.println("what can I do for you?");
                String[] command = reader.readLine().split(" ");
                switch (command[0]) {
                    case "showProductsByCustomer":
                        showProductsByCustomer(Integer.valueOf(command[1]));
                        break;
                    case "findCustomersByProductId":
                        findCustomersByProductId(Integer.valueOf(command[1]));
                        break;
                    case "removeCustomer":
                        removeCustomer(Integer.valueOf(command[1]));
                        break;
                    case "removeProduct":
                        removeProduct(Integer.valueOf(command[1]));
                        break;
                    case "buy":
                        buy(Integer.valueOf(command[1]), Integer.valueOf(command[2]));
                        break;
                    case "exit":
                        exit = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            session.close();
        }

    }

    public static void showProductsByCustomer(int customerId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        customer.getProductList().forEach(System.out::println);
        session.getTransaction().commit();
    }

    public static void findCustomersByProductId(int productId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        product.getCustomerList().forEach(System.out::println);
        session.getTransaction().commit();
    }

    public static void removeCustomer(int customerId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        session.delete(customer);
        session.getTransaction().commit();
    }

    public static void removeProduct(int productId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        session.delete(product);
        session.getTransaction().commit();
    }

    public static void buy(int customerId, int productId) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        Product product = session.get(Product.class, productId);
        customer.getProductList().add(product);
        session.getTransaction().commit();
    }
}
