import DbJobHunter.AdvertisementsEntity;
import DbJobHunter.CandidateEntity;
import DbJobHunter.EmployerAccountEntity;
import DbJobHunter.ProfessionCategoryEntity;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import java.util.HashSet;
import java.util.Map;

public class Main {


    public static void main(final String[] args) throws Exception {
        final Session session = FacadeClass.getSession();

        session.beginTransaction();
        FacadeClass facade = FacadeClass.getFacade();

       /* EmployerAccountEntity employer4 = facade.insertEmployer("Soft Machine",
                                                                "www.Softmachine@yahoo.com",
                                                                0,new HashSet<>());

        session.save(employer4); //inserting employers*/


       /* ProfessionCategoryEntity category1= facade.insertProfCategory("QA",new HashSet<>());
        ProfessionCategoryEntity category2= facade.insertProfCategory("Developer",new HashSet<>());
        ProfessionCategoryEntity category3= facade.insertProfCategory("Manager",new HashSet<>());
        ProfessionCategoryEntity category4 = facade.insertProfCategory("DevOps",new HashSet<>());
        ProfessionCategoryEntity category5 = facade.insertProfCategory("PM",new HashSet<>());

        session.save(category1);
        session.save(category2);
        session.save(category3);
        session.save(category4);
        session.save(category5);//inserting categories


        CandidateEntity candidate1 = facade.insertCandidate("Mihail Plamenov",new HashSet<>());
        CandidateEntity candidate2 = facade.insertCandidate("Stamen Zashev",new HashSet<>());
        CandidateEntity candidate3 = facade.insertCandidate("Genka Mishkova",new HashSet<>());
        CandidateEntity candidate4 = facade.insertCandidate("Miroslav Minkov",new HashSet<>());
        CandidateEntity candidate5 = facade.insertCandidate("Naiden Georgiev",new HashSet<>());*/
        //CandidateEntity candidate6 = facade.insertCandidate("Stanimir Milanov",new HashSet<>());

       /* session.save(candidate1);
        session.save(candidate2);
        session.save(candidate3);
        session.save(candidate4);
        session.save(candidate5);*/
       // session.save(candidate6);


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EmployerAccountEntity> cr = cb.createQuery(EmployerAccountEntity.class);
        Root<EmployerAccountEntity> root = cr.from(EmployerAccountEntity.class);
        cr.select(root);
        cr.where(cb.equal(root.get("id"),2));

        EmployerAccountEntity employer = session.createQuery(cr).getSingleResult();


        CriteriaQuery<ProfessionCategoryEntity> cr1 = cb.createQuery(ProfessionCategoryEntity.class);
        Root<ProfessionCategoryEntity> root1 = cr1.from(ProfessionCategoryEntity.class);
        cr1.select(root1);
        cr1.where(cb.equal(root1.get("id"),5));

        ProfessionCategoryEntity category = session.createQuery(cr1).getSingleResult();

        AdvertisementsEntity advertisement = facade.insertAdvertisement(session,"Welcome to our team ",
                true,employer,category,"We need new employee",new HashSet<>());// inserting advertisement



        CriteriaQuery<CandidateEntity> cr2 = cb.createQuery(CandidateEntity.class);
        Root<CandidateEntity> root2 = cr2.from(CandidateEntity.class);
        cr2.select(root2);
        cr2.where(cb.equal(root1.get("id"),4));//specify exactly who candidate with his ID

        CandidateEntity candidate = session.createQuery(cr2).getSingleResult();


        session.save(advertisement);
        session.getTransaction().commit();
        session.close();


        facade.makeAplly(advertisement,candidate);

    }
}