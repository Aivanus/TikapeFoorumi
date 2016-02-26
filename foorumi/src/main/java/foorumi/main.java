package foorumi;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class main {

    public static void main(String[] args) throws Exception {
        String address = "jdbc:sqlite:foorumi.db";
        Database database = new Database(address);
        AlueDao aluedao = new AlueDao(database);
        ViestiDao viestidao = new ViestiDao(database);
        KetjuDao ketjudao = new KetjuDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("alueet", aluedao.findAll());
            
            return new ModelAndView(map, "alueet");
        }, new ThymeleafTemplateEngine());

        post("/", (req, res) -> {
            String nimi = req.queryParams("nimi");
            aluedao.add(new Alue(nimi));
            res.redirect("./");
<<<<<<< HEAD
            return "";
=======
            return null;
>>>>>>> 465bc211a07ac820451daefb1bfd1b1ce4e12989
        });

    }

}
