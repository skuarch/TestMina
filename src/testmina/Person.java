package testmina;

import java.io.Serializable;

/**
 *
 * @author skuarch
 */
public class Person implements Serializable {
    
    private String mocos = "mocos";
    
    public Person(){
    
    }

    public String getMocos() {
        return mocos;
    }

    public void setMocos(String mocos) {
        this.mocos = mocos;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
}
