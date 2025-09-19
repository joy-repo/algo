

refer: https://youtu.be/S7E-GhQLNnM?si=un3r2_kx2J-JfKMP



We can use SQL to store the Latitude and longitude in separate columns and index those

Any Search in the 2 columns will have o(n^2)

We have few ways to do this.
All the below ways involve breaking downd the 2D space recurrsively and then mapping it.(QuadTree)

google s2 -> maps the globe to a cube and then recurrsively get to a small cell and the location is assigned cellID 
Uber h3 is similar to Quad but the divisions are hexagonal.

R-tree/GIST -> used by postgres. very granular and accurate. no issues with neighbouring cells issue. Is complex and is generally good for ststic locations.


In this Article, we will discuss about GeoHash.
very scalable and not so complex way to store and search by converting a 2D space to  String.

there are 2 main issues which will be discussed.

![alt text](image.png)




We dicvide a 2D space into quadrants. like :

![alt text](image-1.png)

**Step-by-Step Example** </br>
Let’s encode the coordinates:
    Latitude: **37.7749**
    Longitude: **-122.4194**
    ( San Francisco, CA)

1. **Initial Ranges** </br>
    Latitude: [-90, +90] </br>
    Longitude: [-180, +180] </br>
2. **Binary Subdivision** </br>
Alternate between longitude and latitude. </br>
For each bit, split the range in half: </br>
If the coordinate is above the midpoint, set bit to 1 and use the upper half. </br>
If below, set bit to 0 and use the lower half. </br>
**Example (First few steps):**


|Step|	Range |Split |	Value |	Bit |
|---|---|---|---|---|
|1|	Longitude |	-180 to 0	 |-122.4194 < 0 | upper half |
|2|	Latitude |	-90 to 0 |	37.7749 > 0 | upper half |
|3|	Longitude |	-180 to -90 |	-122.4194 > -135 | upper half |
|4|	Latitude |	0 to 90	 | 37.7749 < 45 | lower half  |
...	...	...	...



3. **Interleave Bits** </br>
    Combine longitude and latitude bits alternately.
4. **Base32 Encoding** </br>
    Group bits into 5-bit chunks.
    Convert each chunk to a Base32 character. </br>
**Example Output:** </br>
For San Francisco, the GeoHash is: </br>
**"9q8yy" (short precision)** ---- 
**"9q8yyz0r" (higher precision)**


**Flowchart TD**
    * A[Start with Lat/Lon] --> B[Divide Longitude Range]
    * B --> C[Divide Latitude Range]
    * C --> D[Assign Bit (0 or 1)]
    * D --> E[Interleave Bits]
    * E --> F[Group into 5-bit Chunks]
    * F --> G[Encode to Base32]
    * G --> H[GeoHash String]

## How does it help

We can use it as partitioning key to cluster nearby places in distributed DBs like cassandra and dynamoDB. This will fasten nearby places search. </br> 
Use is as sorted indexed  key in SQL dbs to enable faster d-search for nearby places since  the string is percision based.





