import DbJobHunter.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class FacadeClass {

    private static FacadeClass mua;

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

   public FacadeClass() {

    }

    public static FacadeClass getFacade() {
        if (mua == null) {
            mua = new FacadeClass();
        }
        return mua;
    }


    public EmployerAccountEntity insertEmployer(String name, String email, int advertisementCounter,
                                                Set<AdvertisementsEntity> advertisements) {

        EmployerAccountEntity employer = new EmployerAccountEntity();

        employer.setName(name);
        employer.setEmail(email);
        employer.setAdvertisements(advertisements);
        employer.setAdvertisementCounter(advertisementCounter);

        return employer;
    }


    public ProfessionCategoryEntity insertProfCategory(String name, Set<AdvertisementsEntity> advertisements) {

        ProfessionCategoryEntity category = new ProfessionCategoryEntity();

        category.setName(name);
        category.setAdvertisements(advertisements);

        return category;
    }


    public CandidateEntity insertCandidate(String name, Set<AdvertisementsEntity> advertisements) {

        CandidateEntity candidate = new CandidateEntity();

        candidate.setName(name);
        candidate.setAdvertisements(advertisements);

        return candidate;
    }


    public AdvertisementsEntity insertAdvertisement(Session session,String name, boolean active,
                                                     EmployerAccountEntity employer,
                                                     ProfessionCategoryEntity professionCategory,
                                                     String description,
                                                     Set<CandidateEntity> candidate) {

       if(employer.getAdvertisementCounter()>= 10 && active==true){
           active=false;
       }else if (active==true){
           employer.setAdvertisementCounter(employer.getAdvertisementCounter()+1);

           session.update(employer);

       }
        AdvertisementsEntity advertisement = new AdvertisementsEntity();

        advertisement.setName(name);
        advertisement.setActive(active);
        advertisement.setEmployer(employer);
        advertisement.setProfessionCategory(professionCategory);
        advertisement.setDescription(description);
        advertisement.setCandidate(candidate);


        return advertisement;
    }


    public boolean makeAplly (AdvertisementsEntity advertisement,CandidateEntity candidate){

        Session session1 = getSession();
        session1.beginTransaction();

            CriteriaBuilder cb = session1.getCriteriaBuilder();
            CriteriaQuery<CandidateEntity> cr1 = cb.createQuery(CandidateEntity.class);
            Root<CandidateEntity> root1 = cr1.from(CandidateEntity.class);
            cr1.select(root1);
            cr1.where(cb.isMember(advertisement, root1.get("advertisements")));


            boolean isAplly = true;

            try {
                List<CandidateEntity> candidateEntity = session1.createQuery(cr1).getResultList();
                if(candidateEntity.size()==0){
                    throw new Exception("asd");
                }
            } catch (Exception e) {

                isAplly = false;

                System.out.println("You already aplly this advertisment ");

            }if(isAplly==false) {

                advertisement.getCandidate().add(candidate);
                session1.update(advertisement);


            }
            session1.getTransaction().commit();
            session1.close();

            return !isAplly;

        }
    }

