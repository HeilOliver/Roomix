package at.fhv.roomix.controller.reservation;

import at.fhv.roomix.domain.reservation.Arrangement;
import at.fhv.roomix.domain.reservation.Person;
import at.fhv.roomix.domain.reservation.Reservation;
import at.fhv.roomix.domain.reservation.ReservationUnit;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Roomix
 * at.fhv.roomix.implement.reservation
 * ${FILE_NAME}
 * 09/06/2018 OliverHeil
 * <p>
 * Enter Description here
 */
public class ReservationPDFCreator {

    private static String FilePath = "";

    private static String randomFileNameGenerator() {
        String filePath = "";
        do {
            filePath = FilePath + randomString() + ".pdf";
        } while (new File(filePath).isFile());
        return filePath;
    }

    private static String randomString(){
        int leftLimit = 97;     // letter 'a'
        int rightLimit = 122;   // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private ReservationPDFCreator(){ }

    private File file;
    private FileOutputStream outputStream;
    private PdfWriter pdfWriter;
    private Document document;

    private void closeDocument() {
        document.close();
        pdfWriter.close();
        try {
            outputStream.close();
        } catch (IOException e) {
            // If this is happening something is broken anyway.
        }
    }

    private void loadDocument() throws PDFCreateException {
        try {
            file = new File(randomFileNameGenerator());
            outputStream = new FileOutputStream(file);
            document = new Document();
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();
        } catch (FileNotFoundException | DocumentException e) {
            throw new PDFCreateException();
        }
    }

    public static String createPdf(Reservation reservation) throws PDFCreateException {
        ReservationPDFCreator pdfCreator = new ReservationPDFCreator();
        pdfCreator.loadDocument();

        Document document = pdfCreator.document;
        try {
            document.add(new Paragraph("Hotel Address"));
            document.add(new Paragraph("Roomix Hotel"));
            document.add(new Paragraph("Sample Street 22"));
            document.add(new Paragraph("1234 SampleCity"));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Contracting Party Address"));
            document.add(new Paragraph(reservation.getContractingParty().getContact().getFirstName() + " " +
                    reservation.getContractingParty().getContact().getLastName()));
            document.add(new Paragraph(reservation.getContractingParty().getContact().getCompanyName()));
            document.add(new Paragraph(reservation.getContractingParty().getContact().getStreet() + " "
                    + reservation.getContractingParty().getContact().getHouseNumber()));
            document.add(new Paragraph(reservation.getContractingParty().getContact().getPostcode() + " "
                    + reservation.getContractingParty().getContact().getPlace()));

            document.add(new Paragraph(" ------- Persons ----------------------- "));
            int personCount = 1;
            for (Person person : reservation.getGuests()) {
                document.add(new Paragraph(personCount++ + " - " + person.getFirstName() +
                        " " + person.getLastName()));
            }
            document.add(new Paragraph(" ------- Rooms ------------------------- "));
            int i = 1;
            int totalPrice = 0;
            for (ReservationUnit unit : reservation.getUnits()) {
                document.add(new Paragraph(i++ + ". - " + unit.getCategory().getDescription()));
                document.add(new Paragraph(unit.getStartDate() + " - " + unit.getEndDate()));
                StringBuilder b = new StringBuilder("Arrangements: ");
                for (Arrangement arrangement : unit.getArrangements()) {
                    b.append(arrangement.getDescription());
                    b.append(", ");
                }
                document.add(new Paragraph(b.toString()));
                document.add(new Paragraph(unit.getPrice()/100f + "Euro"));
                document.add(new Paragraph(" --- "));
                totalPrice += unit.getPrice();
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("-----------------------------------"));

            document.add(new Paragraph("Total: " + totalPrice/100f + "Euro"));
        } catch (DocumentException e) {
            throw new PDFCreateException();
        }
        pdfCreator.closeDocument();
        return pdfCreator.file.getPath();
    }
}
