package fr.diginamic.service.essaiDaoMochizuki;

import fr.diginamic.entities.Nutriscore;
import junit.framework.TestCase;

public class MochizukiServicesTest extends TestCase {

    public void test_mochizukiService(){
        MochizukiServices service = MochizukiServices.getSingleton();
        Nutriscore nutriscore1 = new Nutriscore("Y");
        service.createNutriscore(nutriscore1);
    }


}