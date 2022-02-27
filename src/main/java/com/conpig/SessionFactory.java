package com.conpig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SessionFactory {
    static SqlSessionFactory sqlSessionFactory = null;

    static {

        String path = "configuration.xml";
        InputStream inputStream = null;

        try {

            inputStream = Resources.getResourceAsStream(path);
            System.out.println("성공");

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    public static SqlSession getSession() {
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

}
