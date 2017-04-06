package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    Map<Integer, User> repository = new ConcurrentHashMap<>();
    AtomicInteger id = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        if (repository.remove(id) != null) return true;
        else return false;
    }

    @Override
    public User save(User user) {
        if (user.isNew())
            user.setId(id.incrementAndGet());
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing((User u) -> u.getName().toLowerCase())
                        .thenComparing((User u) -> u.getEmail().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return repository.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
