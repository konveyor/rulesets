package com.example;

import org.springframework.dao.CannotSerializeTransactionException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Test class demonstrating usage of deprecated Spring 6 exceptions.
 * These exceptions are deprecated in favor of PessimisticLockingFailureException
 * and CannotAcquireLockException.
 */
@Service
public class DeprecatedExceptionHandler {

    /**
     * Method that catches the deprecated CannotSerializeTransactionException.
     * Should be migrated to catch CannotAcquireLockException or PessimisticLockingFailureException.
     */
    public void handleSerializationFailure() {
        try {
            // Simulate database operation
            performDatabaseOperation();
        } catch (CannotSerializeTransactionException e) {
            // This catch block uses a deprecated exception type
            System.err.println("Transaction serialization failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Method that catches the deprecated DeadlockLoserDataAccessException.
     * Should be migrated to catch CannotAcquireLockException or PessimisticLockingFailureException.
     */
    public void handleDeadlock() {
        try {
            // Simulate database operation
            performDatabaseOperation();
        } catch (DeadlockLoserDataAccessException e) {
            // This catch block uses a deprecated exception type
            System.err.println("Deadlock detected, this transaction lost: " + e.getMessage());
            // Retry logic could go here
        }
    }

    /**
     * Method that throws the deprecated CannotSerializeTransactionException.
     */
    public void throwSerializationException() throws CannotSerializeTransactionException {
        throw new CannotSerializeTransactionException("Unable to serialize transaction");
    }

    /**
     * Method that throws the deprecated DeadlockLoserDataAccessException.
     */
    public void throwDeadlockException() throws DeadlockLoserDataAccessException {
        throw new DeadlockLoserDataAccessException("Deadlock detected", null);
    }

    private void performDatabaseOperation() throws DataAccessException {
        // Simulated database operation
    }
}
