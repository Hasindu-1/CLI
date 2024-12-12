# Ticketing System Application

## Overview

The Ticketing System Application is a Java-based system that simulates ticket sales using multithreading. 
It implements the Producer-Consumer pattern to manage ticket production by venders and ticket purchase by customers.
This application used (OOP) principles and serilization and Deserilization for Configure input managemnt.


## Features 
### 1.Admin panel
Admin panel has the Configure system that takes Admin input as total tickets, customerrate ,vender rate and maxiumum capacity of the ticket pool.
THis project is run based on Admin Enterd detils,Those Details assiged after passed the validate functions.

### 2.Serialization
Using configure object configure details will be wriiten in a separate user defined Jason file.

### 3.Multithreading
Venders will add ticket and customers will buy ticket untill the total number of tickers are released and purchased by venders and customers.

### 4.Logging
Logs system events  such as ticket addition ,purchased and thread interruptions will aved in a separte file.

### 5.Synchronization
Ensures thread-safe operations for adding and removing tickets and used thread safe data stuctures.
