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

## DTO
1. PaymentDto.class: In this class we abstract three variables that would be amount, currency and description, we add ``@Getter`` and ``@Setter`` annotations. In currency we create the object from the created class PaymentCurrency.class.
2. PaymentDto2.class: In this class we abstract the attributes of the Payment.class class, we add the ``@Setter`` and ``@Getter`` to them. We also create the ``fromString`` method with a string parameter, it converts a value to a string and return the value in string.

## MAPPER
1. PaymentMapper.class: In this class we create 2 methods to map to the ``PaymentDto2.class`` dto and to the ``Payment.class`` class.
 - ``public static PaymentDto2 toDto()`` This method creates an object of class ``PaymentDto2.class`` and inside it sets the value that is in ``Payment.class`` example
  ``paymentDto.setIdPayments(payment.getIdPayments());``
 - ``ublic static Payment toModel()`` This method creates an object of class ``Payment.class`` and inside it sets the value that is in ``PaymentDto2.class`` example
 ``payment.setDescription(paymentDto.getDescription());``
 
 ## Repository
 1. PaymentRepository.interface: In this class, the ``@Repository`` annotation is used to detect that it will fulfill that function. It extends from JPA, within the parameters it is connected to the Payment class/entity, and the function of searching through a string for the stripe id was created.
