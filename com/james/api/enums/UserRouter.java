package com.james.api.enums;
import com.james.api.user.UserController;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum UserRouter {
    EXIT("x", (a,b) ->{
        System.out.println("종료");
        return false;
    }),
    JOIN("j", (a,b) -> {
        try {
            System.out.println(a.save(b));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }),
    LOGIN("l", (a,b) ->{
        System.out.println(a.login(b));
        return true;
    }),
    ID("id", (a,b) ->{
        System.out.println(a.findById(b));
        return true;
    }),
    PASSWORD("cp", (a,b) ->{
        System.out.println(a.updatePassword(b));
        return true;
    }),
    DELETE("d", (a,b) ->{
        System.out.println(a.deleteUser(b));
        return true;
    }),
    LIST("ls", (a,b) ->{
        try {
            a.findUsers().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }),
    NAME("name", (a,b) ->{
        System.out.println(a.findUsersByName(b));
        return true;
    }),
    JOB("job", (a,b) ->{
        System.out.println(a.findUsersByJob(b));
        return true;
    }),
    COUNT("count", (a,b)->{
        System.out.println(a.count());
        return true;
    }),
    TOUCH("touch", (a,b) -> {
        try {
            System.out.println(a.createTable());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }),
    REMOVE("rm", (a,b) -> {
        try {
            System.out.println(a.deleteTable());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }),
    INSERT ("in", (a,b) -> {
        try {
            System.out.println((a.insertData(b)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }),

    ERROR("error", (a,b) -> {
        System.out.println("ERROR 유효하지 않는 문자입니다.");
        return true;
    });

    private final String name;
    private final BiPredicate<UserController, Scanner> biPredicate;

    UserRouter(String name, BiPredicate<UserController, Scanner> biPredicate) {
        this.name = name;
        this.biPredicate = biPredicate;
    }
    public static boolean router(UserController ctrl, Scanner sc)  {
        System.out.println("[메뉴] x-Exit\n" +
                " j-회원가입\n" +
                " l-로그인\n" +
                " id-ID검색\n" +
                " cp-비번변경\n" +
                " d-탈퇴\n" +
                " ls-회원목록\n" +
                " name-이름검색\n" +
                " job-직업검색\n" +
                " count-회원수\n" +
                " touch - 테이블생성\n" +
                " rm - 테이블삭제\n"+
                " in - 데이터추가");
        String str = sc.next();
        return Stream.of(values())
                .filter(i -> i.name.equals(str))
                .findAny().orElse(ERROR).biPredicate.test(ctrl,sc);
    }
}