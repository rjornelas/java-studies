package dao;

import entites.Student;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void save(Student student){

        /*
         * Isso é uma sql comum, os ? são os parâmetros que nós vamos adicionar
         * na base de dados
         */

        String sql = "INSERT INTO students(name,age)" +
                " VALUES(?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, student.getName());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setInt(2, student.getAge());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        }finally{

            //Fecha as conexões

            try{
                if(pstm != null){

                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }

            }catch(Exception e){

                e.printStackTrace();
            }
        }
    }

    public void removeById(Integer id){

        String sql = "DELETE FROM students WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{

            try{
                if(pstm != null){

                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }

            }catch(Exception e){

                e.printStackTrace();
            }
        }
    }

    public void update(Student student){

        String sql = "UPDATE students SET name = ?, age = ?" + " WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            //Adiciona o valor do primeiro parâmetro da sql
            pstm.setString(1, student.getName());
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setInt(2, student.getAge());

            pstm.setInt(3, student.getId());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            //Fecha as conexões

            try{
                if(pstm != null){

                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }

            }catch(Exception e){

                e.printStackTrace();
            }
        }
    }

    public List<Student> getStudents(){

        String sql = "SELECT * FROM students";

        List<Student> students = new ArrayList<Student>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while(rset.next()){

                Student student = new Student();

                //Recupera o id do banco e atribui ele ao objeto
                student.setId(rset.getInt("id"));

                //Recupera o nome do banco e atribui ele ao objeto
                student.setName(rset.getString("name"));

                //Recupera a idade do banco e atribui ele ao objeto
                student.setAge(rset.getInt("age"));

                //Adiciono o contato recuperado, a lista de contatos
                students.add(student);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }finally{

            try{

                if(rset != null){

                    rset.close();
                }

                if(pstm != null){

                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }

            }catch(Exception e){

                e.printStackTrace();
            }
        }

        return students;
    }
}
