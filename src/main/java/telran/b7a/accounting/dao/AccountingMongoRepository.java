package telran.b7a.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.accounting.model.User;

public interface AccountingMongoRepository extends MongoRepository<User, String> {

}
