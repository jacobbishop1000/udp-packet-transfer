# Moving UDP Packets

## Background

User Datagram Protocol (UDP) is a core communication protocol in computer networking that operates at the transport layer of the Internet Protocol (IP) suite. Unlike Transmission Control Protocol (TCP), UDP provides connectionless communication, meaning it does not establish a session before transmitting data and does not guarantee delivery or order of packets. Instead, UDP offers a lightweight, low-overhead method for sending datagrams between devices, making it faster and more efficient for applications where real-time data transmission is crucial, such as streaming media, online gaming, and VoIP. While UDP lacks the reliability mechanisms of TCP, such as acknowledgments and retransmission of lost packets, its simplicity and speed make it well-suited for scenarios where occasional packet loss or out-of-order delivery can be tolerated.

## This Code

This code was written to ping UDP packets from a Client to a Server to better understand computer networks and how UDP works. One Server can ping to many Clients on separate machines through the Internet.

## TODO

This repository could use cleanup or a description for how to run it without simply compiling the Java files ad-hoc