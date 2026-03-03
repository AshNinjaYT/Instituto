xquery version "1.0";

for $p in doc("auxiliar.xml")//pelicula
where $p/genere != "ROMÀNTIC"
return
  <pelicula>
    <codi>{$p/@codi}</codi>
    <titol>{$p/titol}</titol>
    <director>{$p/director}</director>
  </pelicula>