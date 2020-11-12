package com.siegelion.streaming.utils;

import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBJDBC {

    public static void main( String args[] ){
        try{
            // 本地开启mongodb
            // 连接到 mongodb 服务 默认localhost:27017
            MongoClient mongoClient = MongoClients.create();

            // 连接到数据库
            MongoDatabase database = mongoClient.getDatabase("douban");
            System.out.println("Connect to database successfully");
            // 获取数据库中的集合
            MongoCollection<Document> collection = database.getCollection("test");
            System.out.println("Access a collection successfully");

            Document myDoc = collection.find().first();
            assert myDoc != null;
            System.out.println(myDoc.toJson());
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    cursor.next();
                }
            }
            finally {
                cursor.close();
            }

            System.out.println(collection.countDocuments());


        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
