import DbJobHunter.AdvertisementsEntity;
import DbJobHunter.CandidateEntity;
import DbJobHunter.EmployerAccountEntity;
import DbJobHunter.ProfessionCategoryEntity;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FacadeClassTest {


    @Test
    void makeAplly() {

        Session session = FacadeClass.getSession();

        EmployerAccountEntity empployer = FacadeClass.getFacade().insertEmployer("Code World",
                "worldcode@bav.bg",9,new HashSet<>());

        ProfessionCategoryEntity category = FacadeClass.getFacade().insertProfCategory(" Devops",new HashSet<>());

        AdvertisementsEntity adv = FacadeClass.getFacade().insertAdvertisement(session,"New JOb",
                true, empployer,category,"uefguegfue",new HashSet<>());

        CandidateEntity candidate = FacadeClass.getFacade().insertCandidate("Gosho",new HashSet<>());


        boolean b=FacadeClass.getFacade().makeAplly(adv,candidate);

        assertTrue(b);

    }
}