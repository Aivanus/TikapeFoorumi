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
        String context = "http://localhost:4567";

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("alueet", aluedao.findAll());
            map.put("context",context);

            return new ModelAndView(map, "alueet");
        }, new ThymeleafTemplateEngine());

        post("/", (req, res) -> {
            String nimi = req.queryParams("nimi");
            aluedao.add(new Alue(nimi));
            res.redirect("./");
            return null;
        });
        
        get("/alue", (req, res) -> {
            HashMap map = new HashMap<>();
            int id = Integer.parseInt(req.queryParams("id"));
            map.put("ketjut",ketjudao.findAllIn(id));
            map.put("alue",aluedao.findOne(id).getNimi());
            return new ModelAndView(map, "ketjut");
        }, new ThymeleafTemplateEngine());


    }

}
