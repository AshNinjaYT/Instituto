xquery version "1.0";

for $p in doc("auxiliar.xml")//pelicula
where xs:integer($p/titol/@any) lt 2000
order by xs:integer($p/titol/@any)
return
  <pelicula>
    <titol>{$p/titol}</titol>
    <genere>{$p/genere}</genere>
    <pais>{$p/director/@pais}</pais>
  </pelicula>
