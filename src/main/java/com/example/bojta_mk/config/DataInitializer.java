package com.example.bojta_mk.config;


import com.example.bojta_mk.model.Post;
import com.example.bojta_mk.model.enumerations.Category;
import com.example.bojta_mk.model.Shape;
import com.example.bojta_mk.model.User;
import com.example.bojta_mk.model.enumerations.Role;
import com.example.bojta_mk.service.PostService;
import com.example.bojta_mk.service.UserService;
import org.springframework.stereotype.Component;
import com.example.bojta_mk.service.ProductService;
import com.example.bojta_mk.service.ShapeService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    ProductService productService;
    ShapeService shapeService;
    UserService userService;
    PostService postService;

    public DataInitializer(PostService postService,ProductService productService, ShapeService shapeService, UserService userService) {
        this.productService = productService;
        this.shapeService = shapeService;
        this.userService = userService;
        this.postService= postService;
    }

    @PostConstruct
    public void initData(){
        List<String> dimensions = new ArrayList<>();
        String dimension = "300x300";
        dimensions.add(dimension);
        dimension = "400x400";
        dimensions.add(dimension);
        Shape shape = shapeService.create("square", dimensions);

        List<String> dimensions2 = new ArrayList<>();
        String dimension2 = "900x200";
        dimensions2.add(dimension2);
        dimension2 = "800x300";
        dimensions2.add(dimension2);
        Shape shape2 = shapeService.create("rectangle", dimensions2);
        Post post = postService.create("Headline1","Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Donec quis risus mollis, luctus mi sit amet, vestibulum eros. " +
                "Fusce vitae mauris scelerisque, efficitur felis id, vehicula augue." +
                "Duis id sollicitudin ex. Nunc laoreet tristique ipsum. Integer faucibus dignissim accumsan." +
                " Sed nec elit ligula. Phasellus convallis varius mollis. Vestibulum in mauris vel lorem viverra pellentesque. " +
                "Pellentesque ut augue vel dolor tempor pulvinar.","src/main/resources/static/uploads/road.jpg");
        Post post2 = postService
                .create("Headline2","Proin accumsan odio at congue semper. " +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra," +
                        " per inceptos himenaeos. Nullam vel enim in libero mollis dictum." +
                        " Aenean condimentum in mi ac dignissim. Etiam dictum volutpat libero, " +
                        "convallis facilisis diam sollicitudin volutpat. Phasellus ultrices sem sed velit " +
                        "tempor euismod. Ut convallis elit non mattis consectetur. Integer congue tincidunt" +
                        " ligula, in tincidunt purus imperdiet sit amet. " +
                "Ut ac laoreet lorem, sit amet finibus diam." +
                "Post 1 really sucks lol","src/main/resources/static/uploads/road2.jpg");
        User admin =  this.userService.register("admin@yahoo.com", "admin", "admin", "Jakov", "Mitrovski", "124343", Role.ROLE_ADMIN);
        User user = this.userService.register("user@yahoo.com", "user", "user", "Jakov123", "Jakov123", "12345", Role.ROLE_USER);
        for (int i = 1; i < 6; i++) {
            productService.create("200."+i, Category.opasnost,shape,
                    "Опасност на пат"+i);
        }
        for (int i = 1; i < 6; i++) {
            productService.create("300."+i, Category.izvestuvanje,shape2,
                    "Известување на пат"+i);
        }
    }
}
