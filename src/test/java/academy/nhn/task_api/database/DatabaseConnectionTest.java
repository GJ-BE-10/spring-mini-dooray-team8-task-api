package academy.nhn.task_api.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("DB 연결 성공: " + connection.getMetaData().getURL());
        } catch (Exception e) {
            System.err.println("DB 연결 실패");
            e.printStackTrace();
            throw e; // 테스트 실패로 처리
        }
    }
}