## 📄 DOCUMENTATION

<hr/> 

## MODEL
1. Payment.class: In this class, the entity model is created to connect to the database. 
Jakarta and Lombok annotations are used to make the code easier to write. 
``@Entity`` to mark the class as a JPA entity. ``@Getter`` ``@Setter`` to create them. 
``@AllArgsConstructor`` ``@NoArgsConstructor`` creates constructor and creates empty. 
``@NotNull`` to specify that the attribute can be empty. ``@Table(name = "")`` is used to mark the table that will be used as the entity for this class. 
And in each tribute a column ``@Column(name = "")`` is assigned to it. ``@Enumerated(EnumType.STRING)`` 
was also used to mark a mapping for multiple values ​​and they are saved as a string in the database.
2. PaymentCurrency.class: In this class the class that maps the currency values is created to have a better dependency injection.

##
