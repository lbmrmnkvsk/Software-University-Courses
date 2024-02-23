package orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);

        if (value == null || (long) value <= 0) {
            return doInsert(entity, primaryKey);
        }

        return doUpdate(entity, primaryKey);
    }

    private boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = entity.getClass().getAnnotation(Entity.class).name();
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        List<Object> values = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                String columnName = field.getAnnotation(Column.class).name();
                columns.append(columnName).append(", ");
                placeholders.append("?, ");
                values.add(field.get(entity));
            }
        }

        String columnString = columns.substring(0, columns.length() - 2);
        String placeholderString = placeholders.substring(0, placeholders.length() - 2);
        String sql = "INSERT INTO " + tableName + " (" + columnString + ") VALUES (" + placeholderString + ");";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < values.size(); i++) {
            statement.setObject(i + 1, values.get(i));
        }
        int affectedRows = statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();

        if (generatedKeys.next()) {
            long id = generatedKeys.getLong(1);
            primary.set(entity, id);
            return true;
        } else {
            return false;
        }
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = entity.getClass().getAnnotation(Entity.class).name();
        StringBuilder setClause = new StringBuilder();
        Object idValue = null;
        List<Object> values = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                idValue = field.get(entity);
            } else if (field.isAnnotationPresent(Column.class)) {
                String columnName = field.getAnnotation(Column.class).name();
                setClause.append(columnName).append(" = ?, ");
                values.add(field.get(entity));
            }
        }

        String setString = setClause.substring(0, setClause.length() - 2);
        String sql = "UPDATE " + tableName + " SET " + setString + " WHERE " + primary.getName() + " = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < values.size(); i++) {
            statement.setObject(i + 1, values.get(i));
        }
        statement.setObject(values.size() + 1, idValue);
        int affectedRows = statement.executeUpdate();

        return affectedRows > 0;
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<E> entities = new ArrayList<>();
        String tableName = table.getAnnotation(Entity.class).name();
        String sql = "SELECT * FROM " + tableName;
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        while (rs.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            for (Field field : table.getDeclaredFields()) {
                field.setAccessible(true);
                String columnName = field.getAnnotation(Column.class).name();
                if (columnName != null) {
                    Object value = rs.getObject(columnName);
                    field.set(entity, value);
                }
            }
            entities.add(entity);
        }

        return entities;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> entities = new ArrayList<>();
        String tableName = table.getAnnotation(Entity.class).name();
        String sql = "SELECT * FROM " + tableName + " WHERE " + where;
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        while (rs.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            for (Field field : table.getDeclaredFields()) {
                field.setAccessible(true);
                String columnName = field.getAnnotation(Column.class).name();
                if (columnName != null) {
                    Object value = rs.getObject(columnName);
                    field.set(entity, value);
                }
            }
            entities.add(entity);
        }

        return entities;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> entities = new ArrayList<>();
        String tableName = table.getAnnotation(Entity.class).name();
        String sql = "SELECT * FROM " + tableName + " LIMIT 1";
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        while (rs.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            for (Field field : table.getDeclaredFields()) {
                field.setAccessible(true);
                String columnName = field.getAnnotation(Column.class).name();
                if (columnName != null) {
                    Object value = rs.getObject(columnName);
                    field.set(entity, value);
                }
            }
            entities.add(entity);
        }

        return entities.get(0);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> entities = new ArrayList<>();
        String tableName = table.getAnnotation(Entity.class).name();
        String sql = "SELECT * FROM " + tableName + " WHERE " + where + " LIMIT 1";
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        while (rs.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            for (Field field : table.getDeclaredFields()) {
                field.setAccessible(true);
                String columnName = field.getAnnotation(Column.class).name();
                if (columnName != null) {
                    Object value = rs.getObject(columnName);
                    field.set(entity, value);
                }
            }
            entities.add(entity);
        }

        return entities.get(0);
    }

    private Field getId(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have a primary key"));
    }
}
