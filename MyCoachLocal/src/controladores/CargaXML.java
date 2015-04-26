package controladores;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class CargaXML{

public CargaXML(){
	
cargaXml();	
}

public void cargaXml(){
	   //Se crea un SAXBuilder para poder parsear el archivo
    SAXBuilder builder = new SAXBuilder();
    File xmlFile = new File( "CargaPosicionesFinal.xml" );
    try
    {
        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );
 
        //Se obtiene la raiz 'tacticas'
        Element rootNode = document.getRootElement();
 
        //Se obtiene la lista de hijos de la raiz 'tacticas'
        List list = rootNode.getChildren( "tactica" );
 
        //Se recorre la lista de hijos de 'tacticas'
        for ( int i = 0; i < list.size(); i++ )
        {
            //Se obtiene el elemento 'tactica'
            Element tactica = (Element) list.get(i);
 
            //Se obtiene el atributo 'nombre' que esta en el tag 'tacticas'
            String nombreTactica = tactica.getAttributeValue("nombre");
 
            System.out.println( "Tactica: " + nombreTactica );
 
            //Se obtiene la lista de hijos del tag 'tactica'
            List lista_posiciones = tactica.getChildren();
 
            System.out.println( "\tDemarcacion\t\t\t\tTipo\t\t\tValor" );
 
            //Se recorre la lista de posiciones
            for ( int j = 0; j < lista_posiciones.size(); j++ )
            {
                //Se obtiene el elemento 'posiciones'
                Element posiciones = (Element)lista_posiciones.get( j );
                
            List lista_pos = posiciones.getChildren();
          //Se recorre la lista de pos
            for ( int k = 0; k < lista_pos.size(); k++ )
            {
                //Se obtiene el elemento 'pos'
                Element pos = (Element)lista_pos.get( k );
             
 
                //Se obtienen los valores que estan entre los tags '<demarcacion></demarcacion>'
              
                String demarcacion = pos.getChildTextTrim("demarcacion");
 
                //Se obtiene el valor que esta entre los tags '<x></x>'
                String posX = pos.getChildTextTrim("x");
                
                //Se obtiene el valor que esta entre los tags '<y></y>'
                String posY = pos.getChildTextTrim("y");
 
               
 
                System.out.println( "\t"+demarcacion+"\t\t\t\t\t"+posX+"\t\t\t"+posY);
            }
        }
     }
    }catch ( IOException io ) {
        System.out.println( io.getMessage() );
    }catch ( JDOMException jdomex ) {
        System.out.println( jdomex.getMessage() );
    }
}
}
