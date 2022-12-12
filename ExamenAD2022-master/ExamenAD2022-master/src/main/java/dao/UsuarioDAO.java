package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO {
    
    private Connection connection;
    
    /* Completar consultas */
    static final String INSERT_QUERY ="INSERT INTO usuario(nombre,apellidos,dni) VALUES(?,?,?)";
    static final String LIST_QUERY="SELECT * FROM usuario";
    static final String GET_BY_DNI="SELECT * FROM usuario WHERE dni=?";
    
    
    
    public void connect(){
        
        String url = "jdbc:mysql://localhost:3306/examenaccesoadatos?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "jumaca";
        try {
            
            /* completar código de conexión */
            connection = DriverManager.getConnection(url, user, password);       
            System.out.println("Conexión establecida correctamente.");
        }catch(Exception ex){
            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }     
    }
    
    public void close(){
        try {
            connection.close();
            System.out.println("Se cerró la conexión correctamente");
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");     
        }
    }
    
    public void save(Usuario user){
        
        /**
         * Completar código para guardar un usuario 
         * Este método no debe generar el id del usuario, ya que la base
         * de datos es la que lo genera.
         * Una vez obtenido el id del usuario tras la consulta sql,
         * hay que modificarlo en el objeto que se pasa como parámetro 
         * de forma que pase a tener el id correcto.
         */
        
           try {
           PreparedStatement ps = connection.prepareStatement(INSERT_QUERY,RETURN_GENERATED_KEYS);
           
           //ps con los campos de la tabla
           ps.setString(1, user.getNombre());
           ps.setString(2, user.getApellidos());
           ps.setString(3, user.getDni());
           ps.executeUpdate();
           
           ResultSet rs = ps.getGeneratedKeys();
           rs.next();
           
           user.setId((long)(rs.getInt(1)) );
           System.out.println("El usuario "+user.getNombre()+" "+user.getApellidos());
           System.out.println("con DNI: "+user.getDni());
           System.out.println("ha sido añadido satisfactoriamente");
           
       } catch (Exception ex) {
           Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Error al añadir");
           System.out.println(ex);
       }

        System.out.println("--Método save completado--");

    }

    public ArrayList<Usuario> list() throws SQLException{

        var salida = new ArrayList<Usuario>(0);
        
        /* Completar código para devolver un arraylist con todos los usuarios */
        
        try( Statement st = connection.createStatement() ){
            
            ResultSet rs = st.executeQuery(LIST_QUERY);
            
            while(rs.next()){
                Usuario user = new Usuario();
                
                
                user.setNombre( rs.getString("nombre") );
                user.setApellidos( rs.getString("apellidos") );
                user.setDni( rs.getString("dni") );
                
                salida.add(user);
            }
            
        
        System.out.println("--Método list completado--");
        
        
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }
    
    public Usuario getByDNI(String dni){
        
        Usuario salida = new Usuario();
        
        /**
         * Completar código para devolver el usuario que tenga ese dni.
         * Si no existe, se debe devolver null
         */
        
        try( var pst =connection.prepareStatement(GET_BY_DNI)){
            
            pst.setString(1, dni);
            
            ResultSet rs = pst.executeQuery();
            
            

               if(rs.next()){
                
                salida.setDni(rs.getString("dni") );
                salida.setApellidos(rs.getString("apellidos"));
                salida.setNombre(rs.getString("nombre"));
                return salida;
                
            }else{
                System.out.println("No hay un registro para ese dni"); 
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("--Método getByDNI completado--");
        return salida;
    } 
}   

