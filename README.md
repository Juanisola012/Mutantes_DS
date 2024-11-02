DATOS:
Juan Ignacio Sola Chambella
3K10
Legajo 46132






INSTRUCCIONES :
La aplicacion se encuentra desplegada en render aunque deje las dependencias en application propperties tanto de MySQL y de H2 por si es necesario probarla local, ya que la render tiene periodo de vencimiento al ser gratuita,
De igual manera los endpoints de Render son los siguientes:

POST: Es necesario enviar un array con el siguiente formato, si es mutante devolvera un 200OK y se cargara en la base, si es un humano devolvera un FORBBIDEN y tambien se cargara en la base:
{
    "mutantDNA": [
        "ATGGGA",
        "CAGGGC",
        "TTATGT",
        "AGGAGG",
        "CCCTTA",
        "TATAAG"
    ]
}

URL:
  - https://mutantes-ds-34bq.onrender.com/mutant

GET: muestra la cantidad de mutantes y humanos que hay en la base y ademas hace un ratio entre ellos.

URL:
  - https://mutantes-ds-34bq.onrender.com/mutant/stats

La respuesta deberia ser similar al siguiente ejemplo
 {
    "count_human_dna": 0,
    "count_mutant_dna": 1,
    "ratio": 0.0
 }

DOCUMENTACION :

El diagrama de arquitectura y el flujo se encuentran en formato PDF en la carpeta principal del repositorio con los siguientes nombres:
DIAGRAMA ARQ MUTANTES.pdf
DIAGRAMA DE SECUENCIA MUTANTES.pdf
