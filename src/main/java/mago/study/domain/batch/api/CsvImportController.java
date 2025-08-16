package mago.study.domain.batch.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mago.study.domain.batch.application.CsvImportService;
import mago.study.domain.batch.dto.FileResult;
import mago.study.domain.batch.dto.ImportResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
@Slf4j
public class CsvImportController {

    private final CsvImportService csvImportService;

    @PostMapping("/tweet")
    public ResponseEntity<List<FileResult>> importCsv() {
        ImportResult importResult = csvImportService.importByRequest();
        log.info("호출 완료");
        return ResponseEntity.ok(importResult.filesProcessed());
    }
}
