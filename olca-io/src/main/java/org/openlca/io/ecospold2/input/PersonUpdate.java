package org.openlca.io.ecospold2.input;

import org.openlca.core.database.ActorDao;
import org.openlca.core.database.IDatabase;
import org.openlca.core.model.Actor;
import org.openlca.ecospold2.EcoSpold2;
import org.openlca.ecospold2.Person;
import org.openlca.ecospold2.PersonList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Updates *existing* contact data sets that are created during a process
 * import with the contact information from a EcoSpold 02 master data file.
 */
public class PersonUpdate implements Runnable {

	private Logger log = LoggerFactory.getLogger(getClass());

	private ActorDao dao;
	private File personFile;

	public PersonUpdate(IDatabase database, File personFile) {
		this.dao = new ActorDao(database);
		this.personFile = personFile;
	}

	@Override
	public void run() {
		log.trace("update actors from {}", personFile);
		try {
			PersonList personList = EcoSpold2.readPersons(personFile);
			if (personList == null)
				return;
			for (Person person : personList.getPersons()) {
				Actor actor = dao.getForRefId(person.getId());
				if (actor == null)
					continue;
				updateActor(actor, person);
			}
		} catch (Exception e) {
			log.error("failed to import persons from " + personFile, e);
		}
	}

	private void updateActor(Actor actor, Person person) {
		actor.setName(person.getName());
		actor.setAddress(person.getAddress());
		actor.setEmail(person.getEmail());
		actor.setTelefax(person.getTelefax());
		actor.setTelephone(person.getTelephone());
		if(person.getCompanyName() != null)
			actor.setDescription("company: " + person.getCompanyName());
		dao.update(actor);
	}
}
