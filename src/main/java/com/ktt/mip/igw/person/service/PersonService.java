package com.ktt.mip.igw.person.service;

import com.ktt.mip.igw.person.domain.ServerPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<Long, ServerPerson> persons = new HashMap<>();

    public PersonService() {
        persons.put(1l, new ServerPerson(1, "John", 25));
        persons.put(2l, new ServerPerson(2, "Steve", 19));
        persons.put(3l, new ServerPerson(3, "Mike", 38));
        persons.put(4l, new ServerPerson(4, "Julia", 41));
    }


    public ServerPerson getPerson(long id) {
        logger.info("retrieving person with id {}", id);
        if(!persons.containsKey(id)) return null;
        return persons.get(id);
    }


    public void updatePerson(ServerPerson person) {
        if (persons.get(person.getId()) != null) {
            logger.info("updating person with id {}", person.getId());
            persons.put(person.getId(), person);
        }
    }


    public void insertPerson(ServerPerson person) {
        if (persons.get(person.getId()) == null) {
            logger.info("inserting new person with id {}", person.getId());
            persons.put(person.getId(), person);
        }
    }


    public void deletePerson(long id) {
        logger.info("deleting person with id {}", id);
        persons.remove(id);
    }
}
