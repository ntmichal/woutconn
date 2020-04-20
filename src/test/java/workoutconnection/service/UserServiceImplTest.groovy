package workoutconnection.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification
import workoutconnection.entities.User

import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

@SpringBootTest
class UserServiceImplTest extends Specification {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EntityManager entityManager;
    User user;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Integer> criteriaQuery;
    Root<User> root;

    void setup(){
        user = User.builder()
                .setUsername("TestUser")
                .setPassword("TestPassword")
                .setEmail("testmail@testmail.com")
                .build();

        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        root = criteriaQuery.from(User.class);

    }

    void 'test saving User, should return User with not null id'(){
        given:
            user = userService.save(user);
        when:
            user.getId() != null;
        then:

            criteriaQuery.select(criteriaBuilder.count(root));
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"),user.getId()));
            assert entityManager.createQuery(criteriaQuery).getSingleResult() == 1






    }

}
