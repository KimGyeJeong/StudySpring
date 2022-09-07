package com.main.day0906;

import com.assembler.day0906.Assembler;
import com.spring.day0906.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }//if문 종료
            if (command.startsWith("new")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change")) {
                processChangeCommand(command.split(" "));
                continue;
            }//if,else if 종료
        }
        printHelp();
    }

    private static Assembler assembler = new Assembler();

    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService registerService = assembler.getRegSvc();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인암호가 일치하지 않습니다.\n");
            return;
        }
        try {
            registerService.regist(req);
            System.out.println("등록완료!");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다...");
        }//try~catch 종료
    }

    private static void processChangeCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService changePasswordService = assembler.getPwdSvc();

        try {
            changePasswordService.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호 변경에 성공했습니다!");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일 입니다!");
        } catch (WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다...");
        }
    }

    private static void printHelp(){
        System.out.println("----------------------");
        System.out.println("설명....");
        System.out.println("----------------------");
    }
}
