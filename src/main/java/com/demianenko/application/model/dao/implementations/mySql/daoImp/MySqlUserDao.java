package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.connection.MySQLConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.UserMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.IUserDao;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;

import java.util.List;

public class MySqlUserDao implements IUserDao {

    private IConnectionFactory cf;
    private MySqlGenericDao gDao;

    public MySqlUserDao() {
        this.cf = MySQLConnectionFactory.getInstance();
        gDao = new MySqlGenericDao(cf);
    }

    @Override
    public Role readRole(Long userId) {
        return null;
    }

    @Override
    public Integer add(User user) {
        return gDao.insert("INSERT INTO user " +
                        "(first_name," +
                        "second_name," +
                        "email," +
                        "password," +
                        "role)" +
                        "VALUES(?,?,?,?,?)",
                user.getFirstName(),
                user.getSecondName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().toString());
    }

    @Override
    public User find(Integer id) {
        User user;
        user = gDao.findObject("SELECT * FROM user " +
                "WHERE id=?",UserMapper::map, id);
        return user;
    }

    @Override
    public void update(User user) {
        gDao.update("UPDATE user " +
                        "SET " +
                        "first_name = ?," +
                        "last_name = ?," +
                        "email = ?," +
                        "password = ?," +
                        "role = ? " +
                        "WHERE id=?",
                user.getFirstName(),
                user.getSecondName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().toString(),
                user.getId());
    }

    @Override
    public void delete(Integer id) {
        gDao.remove("DELETE FROM user " +
                "WHERE id=?", id);
    }

    @Override
    public List<User> findAll() {
        List<User> list = gDao.findObjects("SELECT * FROM user "
                , UserMapper::map);
        return list;
    }
}
