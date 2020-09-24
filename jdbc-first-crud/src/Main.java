import dao.StudentDAO;
import entites.Student;

public class Main {
    public static void main(String args[]){

        StudentDAO studentDAO = new StudentDAO();

        //Cria um contato e salva no banco
//        Student student = new Student();
//        student.setName("ETELVINO");
//        student.setAge(503);
//
//        studentDAO.save(student);

        //Atualiza o contato com id = 1 com os dados do objeto contato1
        Student student1 = new Student();
        student1.setId(2);
        student1.setName("Testando");
        student1.setAge(32);

        studentDAO.update(student1);

        //Remove o contato com id = 1

        studentDAO.removeById(2);

        //Lista todos os contatos do banco de dados

        for(Student s : studentDAO.getStudents()){

            System.out.println("NOME: " + s.getName());
        }
    }
}
