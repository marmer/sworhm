package io.github.marmer.sworhm.testutils.springhelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbCleanupService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Transactional
    public void clearAll() throws Exception {
        final Connection connection = dataSource.getConnection();
        final String catalog = connection.getCatalog();
        final DatabaseMetaData metaData = connection.getMetaData();
        final ResultSet resultSet = metaData.getTables(catalog, null, null, new String[]{"TABLE"});
        final List<String> tables = new ArrayList<>();
        while (resultSet.next()) {
            tables.add(resultSet.getString("TABLE_NAME"));
        }

        truncate(tables);

    }

    private void truncate(final List<String> tables) {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        tables.forEach(table -> entityManager.createNativeQuery("TRUNCATE TABLE " + table + "CASCADE "));
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();

    }

    private <T> void removeAllFrom(final Class<T> entityType) {
        entityManager.createQuery("DELETE FROM " + entityType.getName()).executeUpdate();
    }
}