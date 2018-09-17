package com.esharex.bigdata;

import ch.qos.logback.classic.db.names.TableName;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;

/**
 * @program: bigdata
 * @description: test
 * @author: pengxk
 * @create: 2018-08-29 11:05
 **/
public class Test {
//    public static void main(String[] args) throws Exception {
//        Test hbase = new Test();
//        //创建一张表
//        hbase.createTable("doc","cf");
//    }
//
//
//    /**
//     * 创建一张表
//     * @param tableName
//     * @param column
//     * @throws Exception
//     */
//    public void createTable(String tableName, String column) throws Exception {
//        if(admin.tableExists(TableName.valueOf(tableName))){
//            System.out.println(tableName+"表已经存在！");
//        }else{
//            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
//            tableDesc.addFamily(new HColumnDescriptor(column.getBytes()));
//            admin.createTable(tableDesc);
//            System.out.println(tableName+"表创建成功！");
//        }
//    }
}
