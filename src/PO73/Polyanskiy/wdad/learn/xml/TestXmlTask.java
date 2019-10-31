package PO73.Polyanskiy.wdad.learn.xml;

public class TestXmlTask {
    public static void main(String[] args) {
        XmlTask xmlTask = new XmlTask();
        User user = new User("Георгий", "polyanskiy-g@mail.ru");
        System.out.println(xmlTask.getNoteText(user, "МОЯ ЗАМЕТКА"));

        xmlTask.setPrivileges("МОЯ ЗАМЕТКА", user, 1);

        xmlTask.updateNote(user, "МОЯ ЗАМЕТКА", "новый текст");
    }
}
