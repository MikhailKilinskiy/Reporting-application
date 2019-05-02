package org.dao;

import org.model.*;
import com.github.javafaker.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.HashSet;
import java.util.Random;
import java.math.BigDecimal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateData {
    private static Faker faker = new Faker();
    private static final Logger creatingLogger = LogManager.getLogger(CreateData.class);

    public CreateData() {
    }

    private static void createData() {


        Customer customer = new Customer();
        customer.setContractDate(
                faker.date().between(
                        Date.valueOf(LocalDate.now().minusDays(300)),
                        Date.valueOf(LocalDate.now()))
        );
        customer.setContractName(faker.bothify("????-##"));
        customer.setFullName(faker.company().name());
        customer.setRegion(faker.address().country());
        customer.setShortName(faker.company().buzzword());
        customer.setType(OrganisationType.values()[new Random().nextInt(OrganisationType.values().length)]);

        Supplier supplier = new Supplier();
        supplier.setPerson(faker.name().fullName());
        supplier.setPhone(faker.phoneNumber().phoneNumber());
        supplier.setFullName(faker.company().name());
        supplier.setRegion(faker.address().country());
        supplier.setShortName(faker.company().buzzword());
        supplier.setType(OrganisationType.values()[new Random().nextInt(OrganisationType.values().length)]);

        Product product1 = new Product();
        product1.setName(faker.letterify("???????????"));
        product1.setPrice(new BigDecimal(faker.numerify("####.##")));
        product1.setQuantity(faker.number().numberBetween(1, 100));

        Product product2 = new Product();
        product2.setName(faker.letterify("???????????"));
        product2.setPrice(new BigDecimal(faker.numerify("####.##")));
        product2.setQuantity(faker.number().numberBetween(1, 100));

        Order order = new Order();
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setCustomer(customer);
        order.setSupplier(supplier);

        product1.setOrder(order);
        product2.setOrder(order);
        HashSet<Product> products = new HashSet<>();
        products.add(product1);
        products.add(product2);
        order.setProducts(products);

        GenericDao<Order, Long> orderDao = new GenericDao<>();

        try {
            orderDao.persist(order);
            creatingLogger.info("Order was persistence!");
        } catch (Exception ex) {
            creatingLogger.error("ERROR: " + ex.getMessage());
            creatingLogger.fatal("FATAL ERROR: " + ex.getMessage());
        }
    }


    public static void insertData(int rowCount) {
        int i = 0;
        while (i < rowCount){
            CreateData.createData();
            i++;
        }
    }
    /*
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = new GenericDao<>().getJdbcConnection();
            System.out.println(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    */
}
