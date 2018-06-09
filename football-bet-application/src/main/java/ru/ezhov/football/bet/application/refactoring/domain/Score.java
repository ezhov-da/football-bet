package ru.ezhov.football.bet.application.refactoring.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Score {
    public void add(Player player, Game game, int oneScore, int twoScore) {
//        //Этот код будет вносить данные в таблицу, но перед эти, будем проверять =)
//        ObjectUserName user = (ObjectUserName) jComboBoxHo.getSelectedItem();
//        ObjectListGame game = (ObjectListGame) jComboBoxGame.getSelectedItem();
//        int one = Integer.parseInt(jTextFieldSchet1.getText());
//        int two = Integer.parseInt(jTextFieldSchet2.getText());
//        try {
//            if (one < 0 || two < 0) throw new MyException("Айяяй, что за счет, внеси корректный!");
//            //Этот код будет проверять время внесения, если время меньше 9:00 и больше 18:00, т огда бэньг!
//            Date timeFirst = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(
//                    new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + " 9:00:00");
//            Date timeLast = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(
//                    new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + " 18:00:00");
//            Date timeNow = new Date();
//            // if (timeFirst.compareTo(timeNow)>0 || timeLast.compareTo(timeNow)<0) throw new MyException("Ая яй, как не хорошо =)");
//            Statement st = ConnectionMy.getInctanse().createStatement();
//            ResultSet rs = st.executeQuery("Select userstrana1 from AZ_DEV.dbo.T_E_football_userSchet where username='" + user.getDoubleusername() + "' and idmatcha = '" + game.getId() + "'");
//            while (rs.next()) {
//                throw new MyException("Вы уже вносили счет по указанной игре!");
//            }
//            st.execute("insert into AZ_DEV.dbo.T_E_football_userSchet values('" + user.getDoubleusername() + "','" + game.getId() + "','" + one + "','" + two + "')");
//            new MyException("Данные внесены!").info();
//            jTextFieldSchet1.setText("");
//            jTextFieldSchet2.setText("");
//            jComboBoxGame.setModel(new javax.swing.DefaultComboBoxModel(
//                    new Zaprosy().selectListGame(" select \n" +
//                            " t0.id,\n" +
//                            " CONVERT(VarChar(50), t0.data, 104) as data,\n" +
//                            " t0.gamer,\n" +
//                            " t1.* \n" +
//                            " from \n" +
//                            "		 (\n" +
//                            "				  select \n" +
//                            "				 t0.id,\n" +
//                            "				 CONVERT(VarChar(50), t0.data, 104) as data,\n" +
//                            "				 t0.gamer,\n" +
//                            "				 t1.doubleusername\n" +
//                            "				 from AZ_DEV.dbo.T_E_football_respisanie t0,AZ_DEV.dbo.T_E_football_users t1\n" +
//                            "		 ) t0\n" +
//                            " left join AZ_DEV.dbo.T_E_football_userSchet t1 on t1.idmatcha = t0.id and t1.username=t0.doubleusername\n" +
//                            " where cast(data as date) between cast(Getdate()+1 as date) and cast(Getdate()+40 as date) and t0.doubleusername = '" + StaticPublic.getUserName() + "' and t1.idmatcha is null\n" +
//                            " order by data")
//
//            ));
//            RunQueryTable runTable = new RunQueryTable(this);
//            runTable.itog(jTableItog);
//            runTable.itogGame(jTableResultGame);
//            runTable.prognoz(jTablePrognos);
//        } catch (ClassNotFoundException ex) {
//            new MyException(ex.getMessage()).alarm();
//        } catch (SQLException ex) {
//            new MyException(ex.getMessage()).alarm();
//        } catch (MyException ex) {
//            ex.alarm();
//        } catch (ParseException ex) {
//            Logger.getLogger(FormFootball.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
