package com.app;

import com.conpig.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;
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
        System.out.print("명령어를 입력해주세요 : ");
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
                System.out.print("수정하고자 하는 글 번호를 입력해주세요 : ");
                String modifyArticleId = sc.nextLine();

                Article modifyArticle = articleMysql.findById(Integer.parseInt(modifyArticleId));

                if( isNull(modifyArticle) ){
                    break;
                }

                System.out.println(":: 선택하신 글 :: ");
                System.out.println(modifyArticle);

                System.out.print("새로운 제목 입력 : ");
                String updateTitle = sc.nextLine();
                modifyArticle.setTitle(updateTitle);

                System.out.print("새로운 내용 입력 : ");
                String updateBody = sc.nextLine();
                modifyArticle.setBody(updateBody);

                articleMysql.modify(modifyArticle);

                System.out.print("변경이 완료 되었습니다!");
                session.commit();
                break;

            case "delete" :
                System.out.print("삭제하고자 하는 글 번호를 입력해주세요 : ");
                String deleteArticleId = sc.nextLine();

                if ( isNull(articleMysql.findById(Integer.parseInt(deleteArticleId)))) {
                    break;
                }

                articleMysql.delete(Integer.parseInt(deleteArticleId));

                System.out.println(deleteArticleId + "번 글이 삭제되었습니다!");
                session.commit();
                break;


            case "list" :

                List<Article> articleList = articleMysql.findAll();

                System.out.println(" :: 현재 작성된 게시물 목록 :: ");

                System.out.println("|    번호    |    제목    |    내용    |");

                for (Article a : articleList) {
                    System.out.println("|    " + a.getId() + "    |    " + a.getTitle() + "    |    " + a.getBody() + "    |");
                }

                System.out.println(" :: 게시글 목록 끝 :: ");
                session.commit();
                break;

            case "detail" :
                System.out.print("열람하고자 하는 게시물 번호를 입력해주세요 : ");
                String detailArticleId = sc.nextLine();

                Article detailArticle = articleMysql.findById(Integer.parseInt(detailArticleId));


                if ( isNull(detailArticle)) {
                    break;
                }

                System.out.println(" :: 선택하신 글 :: ");
                System.out.println(detailArticle);

                session.commit();
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
