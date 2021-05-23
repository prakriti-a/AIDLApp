// AIDLMultiplyInterface.aidl
package com.prakriti.aidlapp;

// Declare any non-default types here with import statements

interface AIDLMultiplyInterface {
// AIDL interface is used to establish a relationship bw Client & Remote Server
// MainActivity -> client

    long multipleTwoNumbers(long first, long second);
}

// create a Service that uses this interface
// for .aidl files, always build project after creating for it to be detected in other classes