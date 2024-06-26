/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import static funciones.Level.Verbosity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 *
 * @author yo
 */
public class ClienteBuilder implements Builder<Cliente>{
    //Implanta el patr�n Builder para los objetos Cliente
    public String dni="";
    public String nome="";
    public String apelidos="";
    public String direccion="";
    public String telefono="";
    public LocalDate datanacemento=LocalDate.now();
    public String localidade="";
    public String email="";
    public int codigopostal=1000;
    
    //Inicializamos un objeto cliente y un objeto error de la clase VerboseException
    private VerboseException error;
    private Cliente cliente;
//Constructor(Copia de un objeto Cliente    
    public ClienteBuilder(Cliente c) {
        if (c!=null) {
            this.dni=c.getDni();
            this.nome=c.getNome();
            this.apelidos=c.getApelidos();
            this.direccion=c.getDireccion();
            this.telefono=c.getTelefono();
            this.localidade=c.getLocalidade();
            this.email=c.getEmail();
            this.codigopostal=c.getCodigoPostal();
        }
    }
    
    @Override
    public Cliente build() throws VerboseException {
        reset();
        checkDni();
        checkNome();
        checkApelidos();
        checkDireccion();
        checkTelefono();
        checkDatanacemento();
        checkLocalidade();
        checkEmail();
        checkCodigopostal();
        notifyErrors();
        if (cliente==null) cliente=new Cliente(dni,nome,apelidos);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setNacimiento(datanacemento);
        cliente.setLocalidade(localidade);
        cliente.setEmail(email);
        cliente.setCodigoPostal(codigopostal);
        return cliente;
    }
    public boolean setDni(String dni) {
        this.dni=dni;
        return checkDni();
    }
    
    public boolean setNome(String nome) {
        this.nome=nome;
        return checkNome();
    }
    
    public boolean setApelidos(String apelidos) {
        this.apelidos=apelidos;
        return checkApelidos();
    }
    
    public boolean setDireccion(String direccion) {
        this.direccion=direccion;
        return checkDireccion();
    }
    
    public boolean setTelefono(String telefono) {
        this.telefono=telefono;
        return checkTelefono();
    }
    
    public boolean setDataNacemento(LocalDate date) {
        this.datanacemento=date;
        return checkDatanacemento();
    }
    
    public boolean setDataNacemento(String strdate) {
        strdate=strdate.replace("/","-");
        DateTimeFormatter fmt=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return setDataNacemento(LocalDate.parse(strdate,fmt));
        } catch(DateTimeParseException e) {
            error("Data de nacemento err�nea","Debes indicar dia/mes/ano, co ando en 4 d�xitos");
        }
        return false;
    }
    
    public boolean setLocalidade(String localidade) {
        this.localidade=localidade;
        return checkLocalidade();
    }
    
    public boolean setEmail(String email) {
        this.email=email;
        return checkEmail();
    }
    
    public boolean setCodigopostal(int cp) {
        this.codigopostal=cp;
        return checkCodigopostal();
    }
    
    //M�todos para validaci�n de DNI, fecha de nacimiento, e-mail y c�digo postal y comprobaci�n de que los atributos son correctos
    private boolean checkDni() {
        String check="TRWAGMYFPDXBNJZSQVHLCKE"; 
        int erridx=1;

        if (dni!=null) {
            dni=checkNIE(dni);
            if (Pattern.matches("[0-9]{8}["+check+"]",dni.toUpperCase())) { 
                try {
                    int idx=Integer.parseInt(dni.substring(0,8))%23;
                    if (check.charAt(idx)!=dni.charAt(8)) {
                        error("DNI err�neo","A letra de control non corresponde.");
                        return false;
                    }
                    return true;
                } catch(NumberFormatException e) {
                }
            } 
            error("DNI err�neo","O DNI debe constar de un n�mero de 8 d�xitos e unha letra");
        } else {
            error("DNI err�neo","O DNI non pode ser nulo");
        }
        return false;
    }
    
    private String checkNIE(String dni) {
        String number=dni;
        char first=dni.charAt(0); //�NIE?
        if ((first=='X')||(first=='Y')||(first=='Z')) {
            switch (first) {
                case 'X':
                    number="0";
                    break;
                case 'Y':
                    number="1";
                    break;
                default:
                    number="2";
                    break;
            }
            number+=dni.substring(1);
        }    
        return number;    
    }
    
    
    private boolean checkNome() {
        int erridx=0;
        if ((nome==null)||(nome.length()<1)) {
            erridx=error("Nome err�neo","Debes especificar un nome");
        }
        return erridx==0;    
    }
    
    private boolean checkApelidos() {
        int erridx=0;
        if ((apelidos==null)||(apelidos.length()<1)) {
            erridx=error("Apelidos err�neos","Debes especificar os apelidos");
        }
        return erridx==0;    
    }
    
    private boolean checkDireccion() {
        return true;
    }
    
    private boolean checkTelefono() {
        int erridx=0;
        String number;
        String prefix="+34";

        try {
            if (telefono.charAt(0)=='+') {
                number=telefono.substring(3);
                prefix=telefono.substring(0,3);
            }
            else number=telefono;
            if (number.length()!=9) erridx=error("Tel�fono err�neo","O n�mero de tel�fono debe constar de 9 d�xitos");
            Long.parseLong(number);
            Long.parseLong(prefix.substring(1));
        } catch(NumberFormatException e) {
            if (erridx!=0) error.addMensaxe(ERR,erridx,"O n�mero de tel�fono so pode ter n�meros");
            else erridx=error("Tel�fono err�neo","O n�mero de tel�fono so pode ter n�meros");
        }
        return erridx==0;    
    }
    
    private boolean checkDatanacemento() {
        int erridx=0;
        if (datanacemento==null){
            erridx=error("Data de nacemento err�nea","Debes indicar unha data de nacemento");
        } else {
            if (datanacemento.compareTo(LocalDate.of(2006,1,1))>0) 
                erridx=error("Data de nacemento err�nea","O cliente debe ser maior de 16 anos");
            }
        return erridx==0;    
    }
    
    private boolean checkLocalidade() {
        return true;
    }
    
    private boolean checkEmail() {
        int erridx=0;
        if (!Pattern.matches("^(.+)@(\\S+)$", email)){
            erridx=error("Email err�neo","O email debe ter a forma usuario@dominio");
            }
        return erridx==0;    
    }
    
    private boolean checkCodigopostal() {
        int erridx=0;
        String strcode=Integer.toString(codigopostal);
        if (strcode.length()==4) strcode="0"+strcode;
        if (strcode.length()!=5) {
            erridx=error("Codigo postal err�neo","O codigo postal debe constar de 5 d�xitos");
        } else {
            int pcode=Integer.parseInt(strcode.substring(0,2));
            if (pcode<1 || pcode>44) erridx=error("Codigo postal err�neo","Codigo provincial desco�ecido");
        }
        return erridx==0;    
    }
 
    public void notifyErrors() throws VerboseException {
        if (error!=null){
            throw error;
        }
    }
    
    public void reset() {
        error=null;
    }

    private int error(String msg,String description) {
        if (error==null){
            error=new VerboseException("Erro creando Cliente");
        }
        int erridx=error.addMensaxe(msg);
        error.addMensaxe(ERR,erridx,description);    
        return erridx;
    } 
    
    private int error(String msg) {
        if (error==null){
            error=new VerboseException("Erro creando Cliente");
            }
        int erridx=error.addMensaxe(msg);
        return erridx;
    }
}
