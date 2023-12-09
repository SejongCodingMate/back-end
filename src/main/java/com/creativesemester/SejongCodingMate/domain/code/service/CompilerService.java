package com.creativesemester.SejongCodingMate.domain.code.service;

import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CompilerService {
    public Object[] runCode(String executeCode, String input, String answer) {
        try {
            // 1. Create main.py File
            File tempFile = new File("./main.py");
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(executeCode);
            fileWriter.close();

            // 2. Create process to execute python code
            Process process = new ProcessBuilder("python3", tempFile.getAbsolutePath()) // 서버에서는 python3로 해야함
                    .redirectInput(ProcessBuilder.Redirect.PIPE)
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .start();

            // 3. Enter input
            if (input != null) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                writer.write(input);
                writer.close();
            }

            // 4. Execute process then Check Timeout and Error
            long timeoutInSeconds = 10; // 타임아웃 설정 (초)
            TimeUnit unit = TimeUnit.SECONDS;
            if (!process.waitFor(timeoutInSeconds, unit)) {
                int exitCode = process.exitValue();
                if (exitCode != 0) {
                    return new Object[]{SuccessType.CODE_EXECUTE_ERROR, null};
                } else {
                    process.destroy();
                    return new Object[]{SuccessType.CODE_TIMEOUT, null};
                }
            }

            // 5. Handle Result
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line).append("\n");
            }
            br.close();

            String codeExecuteAnswer = output.toString();

            if (codeExecuteAnswer.equals(answer)) {
                return new Object[]{SuccessType.CODE_ACCEPT, output.toString()};
            } else {
                return new Object[]{SuccessType.CODE_WRONG_ANSWER, output.toString()};
            }

        } catch (Exception e) {
            return new Object[]{ErrorType.CODE_EXCEPTION, null};
        }
    }
}
