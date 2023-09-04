package com.creativesemester.SejongCodingMate.domain.code.service;

import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CompilerService {

    public Object[] runCCode(String code, Map<String, String> testCases) {

        int accept = 0;
        boolean error = false;
        String resultInput = null;
        String resultAnswer = null;

        for (String input : testCases.keySet()) {
            String answer = testCases.get(input);

            try {
                // 1. Create main.C File
                File tempFile = new File("./main.c");
                FileWriter fileWriter = new FileWriter(tempFile);
                fileWriter.write(code);
                fileWriter.close();

                // 2. Create output.exe using the GCC compiler
                Process compileProcess = new ProcessBuilder("gcc", tempFile.getAbsolutePath(), "-o", "output")
                        .redirectErrorStream(true)
                        .start();

                // 3. Error Check output.exe
                int compileExitCode = compileProcess.waitFor();
                if (compileExitCode != 0) {
                    return new Object[]{ResponseCode.CODE_COMPILE_ERROR, accept, input, answer};
                }

                // 4. Create process that output.exe execute
                Process execProcess = new ProcessBuilder("./output")
                        .redirectInput(ProcessBuilder.Redirect.PIPE)
                        .redirectOutput(ProcessBuilder.Redirect.PIPE)
                        .start();

                // 5. Enter input
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(execProcess.getOutputStream()));
                writer.write(input);
                writer.close();

                // 6. Execute process then Check Timeout and Error
                long timeoutInSeconds = 10; // 타임아웃 설정 (초)
                TimeUnit unit = TimeUnit.SECONDS;
                if (execProcess.waitFor(timeoutInSeconds, unit)) {
                    int exitCode = execProcess.exitValue();
                    if (exitCode != 0) {
                        error = true;
                        resultInput = input;
                        resultAnswer = answer;
                        continue;
                    }
                } else {
                    execProcess.destroy();
                    return new Object[]{ResponseCode.CODE_TIMEOUT, accept, input, answer};
                }

                // 7. Handle Result
                BufferedReader br = new BufferedReader(new InputStreamReader(execProcess.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line).append("\n");
                }
                br.close();

                if (!output.toString().trim().equals(answer)) {
                    resultInput = input;
                    resultAnswer = answer;
                } else {
                    accept++;
                }

            } catch (Exception e) {
                return new Object[]{ResponseCode.CODE_EXCEPTION, accept, resultInput, resultAnswer};
            }
        }
        if (error) {
            return new Object[]{ResponseCode.CODE_EXECUTE_ERROR, accept, resultInput, resultAnswer};
        } else if (accept != testCases.size()) {
            return new Object[]{ResponseCode.CODE_WRONG_ANSWER, accept, resultInput, resultAnswer};
        } else {
            return new Object[]{ResponseCode.CODE_ACCEPT, accept, resultInput, resultAnswer};
        }
    }

    public Object[] runPythonCode(String code, Map<String, String> testCases) {

        int accept = 0;
        boolean error = false;
        String resultInput = null;
        String resultAnswer = null;

        for (String input : testCases.keySet()) {
            String answer = testCases.get(input);

            try {
                // 1. Create main.py File
                File tempFile = new File("./main.py");
                FileWriter fileWriter = new FileWriter(tempFile);
                fileWriter.write(code);
                fileWriter.close();

                // 2. Create process to execute python code
                Process process = new ProcessBuilder("python3", tempFile.getAbsolutePath()) // 서버에서는 python3로 해야함
                        .redirectInput(ProcessBuilder.Redirect.PIPE)
                        .redirectOutput(ProcessBuilder.Redirect.PIPE)
                        .start();

                // 3. Enter input
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                writer.write(input);
                writer.close();

                // 34 Execute process then Check Timeout and Error
                long timeoutInSeconds = 10; // 타임아웃 설정 (초)
                TimeUnit unit = TimeUnit.SECONDS;
                if (process.waitFor(timeoutInSeconds, unit)) {
                    int exitCode = process.exitValue();
                    if (exitCode != 0) {
                        error = true;
                        resultInput = input;
                        resultAnswer = answer;
                        continue;
                    }
                } else {
                    process.destroy();
                    return new Object[]{ResponseCode.CODE_TIMEOUT, accept, input, answer};
                }

                // 5. Handle Result
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line).append("\n");
                }
                br.close();

                if (!output.toString().trim().equals(answer)) {
                    resultInput = input;
                    resultAnswer = answer;
                } else {
                    accept++;
                }

            } catch (Exception e) {
                return new Object[]{ResponseCode.CODE_EXCEPTION, accept, resultInput, resultAnswer};
            }
        }
        if (error) {
            return new Object[]{ResponseCode.CODE_EXECUTE_ERROR, accept, resultInput, resultAnswer};
        } else if (accept != testCases.size()) {
            return new Object[]{ResponseCode.CODE_WRONG_ANSWER, accept, resultInput, resultAnswer};
        } else {
            return new Object[]{ResponseCode.CODE_ACCEPT, accept, resultInput, resultAnswer};
        }
    }
}
