This example does not work well with HSQLDB - we recommend that you 
carry this out with the PostgreSQL database instead if possible.

Note also that this code is slightly different from that printed in 
the book - the Session API changed between Beta 2 (against which that
chapter was written) and the released version. The create method used
to associate an object with the session was removed, and the most
appropriate replacement is saveOrUpdate. The code here has been amended.
