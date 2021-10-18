# Meitrack tracker command format:

```
$$Q25,                  $$<Data identifier><Data length>           -   1
353358017784062,        <IMEI>                                     +   2
AAA,                    <Command type>                             -   3
35,                     <Event code>                               -   4
22.756325,              <(-)Latitude>                              +   5
114.752146,             <(-)Longitude>                             +   6
091221102631,           <Date and time>                            +   7
A,                      <Positioning status>                       -   8
5,                      <Number of satellites>                     -   9
12,                     <GSM signal strength>                      -   10
5,                      <Speed>                                    -   11
72,                     <Direction>                                +   12
5,                      <Horizontal dilution of precision(HDOP)>   -   13
118,                    <Altitude>                                 +   14
564870,                 <Mileage>                                  -   15
2546321,                <Runtime>                                  -   16
460|0|E166|A08B,        <Base station info>                        -   17
0421,                   <I/O port status>                          -   18
123|456|235|1456|222,   <Analog input value>                       -   19
02 00 00 00             <Geo-fence number>                         -   20
*BE                     <*Checksum>
```

## For example:

```
$$Q25,353358017784062,AAA,35,22.756325,114.752146,091221102631,A,5,12,5,72,5,118,564870,2546321,460|0|E166|A08B,0421,123|456|235|1456|222,02 00 00 00*BE
```

# Queclink tracker command format:

```
+RESP:GTFRI,      Message type         -   1
020102,           Protocol version     -   2
135790246811220,  Unique ID            +   3
,                 Device name          -   4
0,                Report ID            -   5
0,                Report type          -   6
1,                Number               -   7
1,                GPS accuracy         -   8
4.3,              Speed                -   9
92,               Azimuth              +   10
70.0,             Altitude             +   11
121.354335,       Longitude            +   12
31.222073,        Latitude             +   13
20090214013254,   GPS UTC time         +   14
0460,             MCC                  -   15
0000,             MNC                  -   16
18d8,             LAC                  -   17
6141,             Cell ID              -   18
00,               Reserved1            -   19
,                 battery percentage   -   20
20090214093254,   Send time            -   21
11F0              Count number         -   22
$                 Tail character
```

## For example:

```
+RESP:GTFRI,020102,135790246811220,,0,0,1,1,4.3,92,70.0,121.354335,31.222073,20090214013254,0460,0000,18d8,6141,00,,20090214093254,11F0$
```
