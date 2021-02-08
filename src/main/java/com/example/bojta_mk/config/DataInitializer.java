//package com.example.bojta_mk.config;
//
//
//import com.example.bojta_mk.model.Category;
//import com.example.bojta_mk.model.Shape;
//import org.springframework.stereotype.Component;
//import com.example.bojta_mk.service.ProductService;
//import com.example.bojta_mk.service.ShapeService;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class DataInitializer {
//    ProductService productService;
//    ShapeService shapeService;
//
//    public DataInitializer(ProductService productService, ShapeService shapeService) {
//        this.productService = productService;
//        this.shapeService = shapeService;
//    }
//
//    @PostConstruct
//    public void initData(){
//        List<String> dimensions = new ArrayList<>();
//        String dimension = "300x300";
//        dimensions.add(dimension);
//        dimension = "300x300";
//        dimensions.add(dimension);
//        Shape shape = shapeService.create("square", dimensions);
//
//        List<String> dimensions2 = new ArrayList<>();
//        String dimension2 = "900x200";
//        dimensions.add(dimension2);
//        dimension2 = "800x300";
//        dimensions.add(dimension2);
//        Shape shape2 = shapeService.create("rectangle", dimensions2);
//
//        for (int i = 1; i < 6; i++) {
//            productService.create("200."+i, Category.opasnost,shape,
//                    "Opasnost na pat"+i,"Description za opasnoast na pat"+i);
//        }
//        for (int i = 1; i < 6; i++) {
//            productService.create("300."+i, Category.opasnost,shape,
//                    "Izvestuvanje na pat"+i,"Description za izvestuvanje na pat"+i);
//        }
//    }
//}
