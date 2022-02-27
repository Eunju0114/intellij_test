package com.app;

import com.conpig.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLOutput;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class main {

    public static void main(String[] args) {

        SqlSession session = SessionFactory.getSession();
        ArticleDao articleMysql = session.getMapper(ArticleDao.class);

        Scanner sc =new Scanner(System.in);

        boolean switchstatus = true;

        while (switchstatus){

            try{
                System.out.println("명령어를 입력해주세요 : ");
                String command = sc.nextLine();

                switch (command){

                    case "stop" :
                        System.out.println("프로그램을 종료합니다.");
                        switchstatus = false;
                        break;

                    case "write":
                        Article article = new Article();

                        System.out.print("제목을 입력해주세요 : ");
                        String title = sc.nextLine();
                        article.setTitle(title);

                        System.out.print("내용을 입력해주세요 : ");
                        String body = sc.nextLine();
                        article.setBody(body);

                        articleMysql.write(article);
                        System.out.println("성공적으로 글이 작성되었습니다!");
                        session.commit();
                        break;

                    case "modify" :
                        System.out.println("수정하고자 하는 글 번호를 입력해주세요 : ");
                        Article modifyArticle = articleMysql.findById(Integer.parseInt(sc.nextLine()));

                        if( isNull(modifyArticle) ){
                            break;
                        }

                        System.out.println(":: 선택하신 글 :: ");
                        System.out.println(modifyArticle);

                        System.out.println("새로운 제목 입력 : ");
                        String updateTitle = sc.nextLine();
                        modifyArticle.setTitle(updateTitle);

                        System.out.println("새로운 내용 입력 : ");
                        String updateBody = sc.nextLine();
                        modifyArticle.setBody(updateBody);

                        articleMysql.modify(modifyArticle);
                        session.commit();

                        System.out.println("변경이 완료 되었습니다.!");
                        break;




                    default:
                        break;
                }

            } catch (Exception e){

                System.out.println("잘못된 명령어 입니다.");
            }
        }
    }
}
