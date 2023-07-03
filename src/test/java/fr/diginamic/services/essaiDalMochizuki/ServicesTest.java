package fr.diginamic.services.essaiDalMochizuki;

import fr.diginamic.entities.Nutriscore;
import fr.diginamic.services.Services;
import junit.framework.TestCase;

public class ServicesTest extends TestCase {

    public void test_mochizukiService(){
        Services service = Services.getSingleton();
        Nutriscore nutriscore1 = new Nutriscore("Y");
        service.createNutriscore(nutriscore1);


        nutriscore1.setValeurScore("Z");
        service.updateNutriscore(nutriscore1);
    }


}