package workoutconnection.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import workoutconnection.entities.User

import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaDelete
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root
import javax.transaction.Transactional


@Transactional
@SpringBootTest
class UserServiceImplTest extends Specification {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EntityManager entityManager;
    User testUser1;
    User testUser2;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Integer> criteriaQuery;
    Root<User> root;

    void setup(){
        testUser1 = User.builder()
                .setUsername("TestUser")
                .setPassword("TestPassword")
                .setEmail("testmail@testmail.com")
                .build();
        testUser2 = User.builder().setUsername("TestUser2")
                .setPassword("TestPassword")
                .setEmail("testmail@testmail.com")
                .build();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        root = criteriaQuery.from(User.class);

    }

    void cleanup(){
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> query = criteriaBuilder.createCriteriaDelete(User.class);
        query.from(User.class);
        entityManager.createQuery(query).executeUpdate();
    }

    void 'test saving User, should return User with not null id'(){
        given:
            testUser1 = userService.save(testUser1);
        when:
            testUser1.getId() != null;
        then:
            criteriaQuery.select(criteriaBuilder.count(root));
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"),testUser1.getId()));
            assert entityManager.createQuery(criteriaQuery).getSingleResult() == 1

    }

    void 'return list of users'(){
        given:
            testUser1 = userService.save(testUser1)
            testUser2 = userService.save(testUser2)
            List<User> list = userService.findAll()
        expect:
            criteriaQuery.select(criteriaBuilder.count(root));
            assert entityManager.createQuery(criteriaQuery).getSingleResult() == list.size()

    }



}
