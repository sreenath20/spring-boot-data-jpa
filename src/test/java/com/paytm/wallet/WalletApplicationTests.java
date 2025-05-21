package com.paytm.wallet;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WalletApplicationTests {


    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletRepository walletRepository;

    private Wallet testWallet;



    @Test
    public void registerNewWalletTest() throws WalletException { // +ve test case
        // Test data
        Wallet resultWallet = this.walletService.registerNewWallet(testWallet);
        Assertions.assertEquals(testWallet.getEmail(), resultWallet.getEmail());
        Assertions.assertNotNull(resultWallet.getId());
//        Assertions.assertEquals();
//        Assertions.assertNotEquals();
//        Assertions.assertNotNull();
//        Assertions.assertNull();
//        Assertions.assertTrue();
//        Assertions.assertFalse();
//        Assertions.assertSame();
//        Assertions.assertNotSame();
    }

    @Test
    public void checkIfRegisterWalletThrowsException() throws WalletException { // -Ve test case
        testWallet = new Wallet("Test User 1", 500.0, "u1@g.com", "123@");
        this.walletService.registerNewWallet(testWallet);
        Assertions.assertThrows(WalletException.class, ()->this.walletService.registerNewWallet(testWallet));
    }


    @BeforeAll
    public static void beforeAllTests() { // Test set up for all test cases
        System.out.println("Before all");
    }

    @BeforeEach
    public void beforeEachTest() { // Allocate resources for each test
        System.out.println("Before Each");
        testWallet = new Wallet("Test User 1", 500.0, "u1@g.com", "123@");
    }

    @AfterEach
    public void afterEachTest() throws WalletException { // tear down resources
        System.out.println("After each test");
        this.walletService.deleteWalletByEmailId(testWallet.getEmail());
    }

    @AfterAll
    public static void afterAllTests() { // Test set up for all test cases
        System.out.println("After  all");
    }

}
