package tests;

import models.StudentData;
import pages.RegistrationPage;
import utils.StudentDataFactory;
import org.junit.jupiter.api.Test;

public class PracticeFormTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillFormTest() {
        StudentData testData = StudentDataFactory.generateFullStudentData();

        registrationPage.openPage()
                .setFirstName(testData.getFirstName())
                .setLastName(testData.getLastName())
                .setEmail(testData.getEmail())
                .chooseGender(testData.getGender())
                .setPhoneNumber(testData.getPhoneNumber())
                .setDateOfBirth(testData.getDay(), testData.getMonth(), testData.getYear())
                .setSubjects(testData.getSubjects())
                .chooseHobbies(testData.getHobbies())
                .uploadFile(testData.getPicture())
                .setAddress(testData.getAddress())
                .setState(testData.getState())
                .setCity(testData.getCity())
                .submitRegistration()
                .modalWindowShouldExist()

                .checkResult("Student Name", testData.getFirstName() + " " + testData.getLastName())
                .checkResult("Student Email", testData.getEmail())
                .checkResult("Gender", testData.getGender())
                .checkResult("Mobile", testData.getPhoneNumber())
                .checkResult("Date of Birth", testData.getDay() + " " + testData.getMonth() + "," + testData.getYear())
                .checkResult("Subjects", String.join(", ", testData.getSubjects()))
                .checkResult("Hobbies", String.join(", ", testData.getHobbies()))
                .checkResult("Picture", testData.getPicture())
                .checkResult("Address", testData.getAddress())
                .checkResult("State and City", testData.getState() + " " + testData.getCity())
                .closeModalWindow()
                .modalWindowShouldNotExist();
    }

    @Test
    void fillOnlyNecessaryFormTest() {
        StudentData testData = StudentDataFactory.generateMinimalStudentData();

        registrationPage.openPage()
                .setFirstName(testData.getFirstName())
                .setLastName(testData.getLastName())
                .chooseGender(testData.getGender())
                .setPhoneNumber(testData.getPhoneNumber())
                .setDateOfBirth(testData.getDay(), testData.getMonth(), testData.getYear())
                .submitRegistration()
                .modalWindowShouldExist()
                .checkResult("Student Name", testData.getFirstName() + " " + testData.getLastName())
                .checkResult("Gender", testData.getGender())
                .checkResult("Date of Birth", testData.getDay() + " " + testData.getMonth() + "," + testData.getYear())
                .closeModalWindow()
                .modalWindowShouldNotExist();
    }

    @Test
    void fillOnlyFirstNameFormTest() {
        StudentData testData = StudentDataFactory.generateFirstNameOnlyData();

        registrationPage.openPage()
                .setFirstName(testData.getFirstName())
                .submitRegistration()
                .modalWindowShouldNotExist();
    }
}