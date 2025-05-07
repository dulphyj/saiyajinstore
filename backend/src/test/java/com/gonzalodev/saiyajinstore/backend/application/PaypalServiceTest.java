/*package com.gonzalodev.saiyajinstore.backend.application;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaypalServiceTest {
    @Mock
    private APIContext apiContext;

    @InjectMocks
    private PaypalService paypalService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePayment_success() throws Exception {
        // Arrange
        Payment mockPayment = Mockito.mock(Payment.class);
        when(mockPayment.create(apiContext)).thenReturn(mockPayment);

        // Simular construcción
        Payment paymentSpy = Mockito.spy(new Payment());
        doReturn(mockPayment).when(paymentSpy).create(apiContext);

        // Usamos Mockito.spy para interceptar el new Payment()
        try (MockedConstruction<Payment> mockedPayment = mockConstruction(Payment.class,
                (mock, context) -> {
                    when(mock.create(apiContext)).thenReturn(mockPayment);
                })) {

            // Act
            Payment result = paypalService.createPayment(
                    100.0, "USD", "paypal", "sale",
                    "Test payment", "http://cancel", "http://success"
            );

            // Assert
            assertNotNull(result);
            verify(mockedPayment.constructed().get(0)).create(apiContext);
        }
    }

    @Test
    void testCreatePayment_failure_throwsRuntimeException() throws Exception {
        // Arrange
        try (MockedConstruction<Payment> mockedPayment = mockConstruction(Payment.class,
                (mock, context) -> {
                    when(mock.create(apiContext)).thenThrow(new PayPalRESTException("PayPal failed"));
                })) {

            // Act & Assert
            RuntimeException ex = assertThrows(RuntimeException.class, () ->
                    paypalService.createPayment(
                            100.0, "USD", "paypal", "sale",
                            "Test payment", "http://cancel", "http://success"
                    )
            );

            assertTrue(ex.getMessage().contains("Failed to create PayPal payment"));
        }
    }

    @Test
    void testExecutePayment_success() throws Exception {
        // Arrange
        String paymentId = "PAY-123";
        String payerId = "PAYER-456";

        Payment mockPayment = Mockito.mock(Payment.class);
        when(mockPayment.execute(eq(apiContext), any(PaymentExecution.class)))
                .thenReturn(mockPayment);

        try (MockedConstruction<Payment> mockedPayment = mockConstruction(Payment.class,
                (mock, context) -> {
                    when(mock.execute((String) any(), any())).thenReturn(mockPayment);
                })) {

            // Act
            Payment result = paypalService.executePayment(paymentId, payerId);

            // Assert
            assertNotNull(result);
            verify(mockedPayment.constructed().get(0)).execute(eq(apiContext), any(PaymentExecution.class));
        }
    }

    @Test
    void testExecutePayment_failure_throwsRuntimeException() throws Exception {
        // Arrange
        String paymentId = "PAY-123";
        String payerId = "PAYER-456";

        try (MockedConstruction<Payment> mockedPayment = mockConstruction(Payment.class,
                (mock, context) -> {
                    when(mock.execute((String) any(), any())).thenThrow(new PayPalRESTException("Execute error"));
                })) {

            // Act & Assert
            RuntimeException ex = assertThrows(RuntimeException.class, () ->
                    paypalService.executePayment(paymentId, payerId)
            );

            assertTrue(ex.getMessage().contains("Failed to execute PayPal payment"));
        }
    }

}
*/