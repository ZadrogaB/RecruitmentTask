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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class CsvService {

    private final UserService service;

    public void addUsersFromFile(MultipartFile csvFile) throws IOException {
        Iterable<CSVRecord> records = prepareRecords(csvFile);

        for (CSVRecord record : records) {
            String firstName = record.get("first_name").strip();
            String lastName = record.get("last_name").strip();
            String birthDate = record.get("birth_date").strip();
            String phoneNumber = record.get("phone_no").strip();

            if (firstName.length() != 0 && lastName.length() != 0 && birthDate.length() !=0) {
                User user = new User(firstName, lastName, getLocalDate(birthDate));

                if (phoneNumber.length() == 9) {
                    user.setPhoneNumber(phoneNumber);
                }
                try {
                    service.saveUser(user);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    private Iterable<CSVRecord> prepareRecords (MultipartFile csvFile) throws IOException {
        String[] HEADERS = { "first_name", "last_name", "birth_date", "phone_no"};
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), "UTF-8"));

        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .withDelimiter(';'));
        Iterable<CSVRecord> records = csvParser.getRecords();

        return records;
    }

    private LocalDate getLocalDate (String date) {
        int year, month, day, nextDot;
        nextDot = date.indexOf(".");
        year = Integer.valueOf(date.substring(0, nextDot));
        date = date.substring(nextDot+1);
        nextDot = date.indexOf(".");
        month = Integer.valueOf(date.substring(0, nextDot));
        date = date.substring(nextDot+1);
        day = Integer.valueOf(date);
        LocalDate birthDate = LocalDate.of(year, month, day);
        return birthDate;
    }
}
