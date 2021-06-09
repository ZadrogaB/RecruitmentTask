package com.newspoint.service;

import com.newspoint.dbService.UserService;
import com.newspoint.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RequiredArgsConstructor
@Service
public class CsvService {

    private final UserService service;

    public void addUsersFromFile(MultipartFile csvFile) throws IOException {
        Iterable<CSVRecord> records = prepareRecords(csvFile);

        for (CSVRecord record : records) {
            User user = new User(record.get("first_name").strip(),
                                 record.get("last_name").strip(),
                                 record.get("birth_date").strip());
            if (record.get("phone_no").length() == 9 ) {
                user.setPhoneNumber(record.get("phone_no").strip());
            }
            service.saveUser(user);
        }
    }

    public Iterable<CSVRecord> prepareRecords (MultipartFile csvFile) throws IOException {
        String[] HEADERS = { "first_name", "last_name", "birth_date", "phone_no"};
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), "UTF-8"));

        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .withDelimiter(';'));
        Iterable<CSVRecord> records = csvParser.getRecords();

        return records;
    }
}
