xquery version "1.0";

let $totalMinuts := sum(doc("auxiliar.xml")//pelicula/durada)
let $hores := $totalMinuts div 60
return
  <tempsTotal>{$hores} hores</tempsTotal>
