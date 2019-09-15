package by.stqa.lesson2.addressbook.generators;

import by.stqa.lesson2.addressbook.modal.ContactData;
import by.stqa.lesson2.addressbook.modal.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String [] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }
        catch (ParameterException e){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generatContact(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        }
        else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        }
        else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        }
        else{
            System.out.println("Unrecognized" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json =gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s; %s; %s; %s; %s; %s; %s; %s; %s;\n",contact.getLastname(),
                    contact.getFirstname(), contact.getMobile(), contact.getEmail(),
                    contact.getAddress(), contact.getBday(), contact.getBmonth(), contact.getByear(), contact.getGroup()));
        }
        writer.close();
    }

    private List<ContactData> generatContact(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withLastname(String.format("Ivanov_%s", i))
                    .withFirstname(String.format("Ivan_%s", i))
                    .withMobile(String.format("+375 (29) 111 11 1%s", i))
                    .withHomephone(String.format("11 11 1%s", i))
                    .withAddress(String.format("Address_%s", i))
                    .withEmail(String.format("email_%s@mail.ru", i))
                    .withEmail2(String.format("email2_%s@mail.ru", i))
                    .withBday(String.format("%s", i + 1))
                    .withBmonth("September")
                    .withByear(String.format("198%s", i))
                    .withGroup(String.format("test%s", i)));
        }
        return contacts;
    }
}
