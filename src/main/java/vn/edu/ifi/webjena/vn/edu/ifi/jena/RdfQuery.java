package vn.edu.ifi.webjena.vn.edu.ifi.jena;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class RdfQuery {
    public String Query(String ville){
        System.out.println("avant "+ville);
        String[] villeTab = ville.split(" ");
        System.out.println(" taille "+villeTab.length);
        if(villeTab.length > 1) {
            for(int i = 0 ; i< villeTab.length; i++) {
                villeTab[i] = Character.toUpperCase(villeTab[i].charAt(0))+villeTab[i].substring(1);
            }
            ville = Arrays.stream(villeTab).collect(Collectors.joining("_"));
        }else {
            ville = Character.toUpperCase(ville.charAt(0))+ville.substring(1);
        }
        ville = ville.trim();
        System.out.println(ville);
        // TODO Auto-generated method stub
        String service = "http://dbpedia.org/sparql";
        String queryString = "";
        queryString = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?label " +
                "WHERE {" +
                "<http://dbpedia.org/resource/"+ville+"> <http://dbpedia.org/ontology/country> ?y ."+
                "?y rdfs:label ?label ."+
                "FILTER (LANG(?label) = 'en')"+
                "}";
        //System.out.println(queryString);
        Query query = QueryFactory.create(queryString);
        QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
        ResultSet results = qexec.execSelect();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        Gson gson = new Gson();

        System.out.println("Country: " + gson.toJson(json));


        return  json ;
        //return  gson.toJson(json) ;
        // and turn that into a String
        /*String json = new String(outputStream.toByteArray());
        return  json ;*/
	   /*  System.out.println(json);
	   for ( ; results.hasNext() ; ) {
	        QuerySolution soln = results.nextSolution() ;
	        System.out.println(soln);
	        System.out.println(soln.getLiteral("label"));
	    }*/
    }
}
