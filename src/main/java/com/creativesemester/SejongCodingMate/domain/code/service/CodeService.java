package com.creativesemester.SejongCodingMate.domain.code.service;


import com.creativesemester.SejongCodingMate.domain.code.dto.CodeRequestDto;
import com.creativesemester.SejongCodingMate.domain.code.dto.CodeResponseDto;
import com.creativesemester.SejongCodingMate.domain.code.entity.Code;
import com.creativesemester.SejongCodingMate.domain.code.repository.CodeRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.problem.entity.Problem;
import com.creativesemester.SejongCodingMate.domain.problem.repository.ProblemRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;
    private final ProblemRepository problemRepository;

    @Transactional
    public ResponseEntity<GlobalResponseDto> executeCCode(Member member, CodeRequestDto codeRequestDto) {

        // 1. Get Problem by ProblemId
        Optional<Problem> problem = problemRepository.findById(codeRequestDto.getProblemId());

        if (problem.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.PROBLEM_NOT_FOUND));
        }

        // 2. Run Code
        String code = codeRequestDto.getCode();
        Map<String, String> testCases = problem.get().getTestCases();

        Object[] result = runCCode(code, testCases);

        // 3. Return Result of Run Code
        ResponseCode responseCode = (ResponseCode) result[0];
        String message = responseCode.name();

        Double score = ((Number) result[1]).doubleValue() / testCases.size() * 100;
        String input = (String) result[2];
        String answer = (String) result[3];

        if (responseCode != ResponseCode.CODE_EXCEPTION) { // 서버 오류는 DB에 Code 저장하지 않음
            codeRepository.save(Code.of(member, problem.get(), code, "C", message, score));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(responseCode, CodeResponseDto.of(score, input, answer)));
    }

    private Object[] runCCode(String code, Map<String, String> testCases) {

        int accept = 0;
        boolean error = false;
        String resultInput = null;
        String resultAnswer = null;

        for (String s : testCases.keySet()) {
            String input = s;
            String answer = testCases.get(s);

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
}


