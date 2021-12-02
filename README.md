# About facecto-code-base-utils
facecto-code-base-utils is a standard tool library that contains commonly used utils classes, and it will continue to integrate more available tools.

# Quick Start
## Step 1: setting the pom.xml add dependency
```
<dependency>
  <groupId>com.facecto.code</groupId>
  <artifactId>facecto-code-base-utils</artifactId>
  <version>1.1.0</version>
</dependency>
```
## Step 2 : No more step. Enjoy it.

# About IdBuilder
* The ID Builder is based on twitter snowflakes.
* Used to generate sequential codes with dates.
* The first 8 bits are the year, month and date.
* The 9th bit is the microservice designator and takes values in the range 0-15 (displayed as hex 0-9A-F).
* The 10th bit is the machine designator and takes the value range 0-15 (displayed as 0-9A-F in hexadecimal).
* The last 14 bits are the sequence codes.
* Example: 20211202AB10098259438592
* Since from version:1.1.0

## About sequence code
```
* 0 101001001100101101111111111 1111 1111 001111111111
* 0 <-------    A    ---------> < B> < C> <---- D --->
```
* All total 48 bits
* 1st bit 0
* A is the interstamp difference length 27
* B is the application code Length 4, value range 0-15. 16 services can be configured
* C is the machine code Length 4, value range 0-15. 16 machines can be configured
* D is a sequential code Length 12, range 0-4095. generates 4095 sequential codes per second

# About Jon So
I‘m an ordinary developer, likes mountains and rivers, likes code.
The most important thing is make more friends.
thanks.

# About facecto.com
https://facecto.com

