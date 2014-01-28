package guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import sun.security.provider.certpath.Vertex;

import java.util.Map;

/**
 * Created by wujiabin on 14-1-28.
 */
public class TableDemo {
    public static void main(String[] args) {
        // 类似一张表格, like HashMap<R, HashMap<C, V>>, R C是行和列, V是表格中的数据
        Table<String, String, Double> weightedGraph = HashBasedTable.create();
        weightedGraph.put("a", "b", 4d);
        weightedGraph.put("a", "c", 20d);
        weightedGraph.put("b", "c", 5d);

        System.out.println(weightedGraph.row("a")); // returns a Map mapping b to 4, c to 20
        System.out.println(weightedGraph.column("c")); // returns a Map mapping a to 20, b to 5

        // iteration
        for (Table.Cell<String, String, Double> e : weightedGraph.cellSet()) {
            System.out.println(e.getRowKey() + "," + e.getColumnKey() + "\t" + e.getValue());
        }

        // rowMap
        for (Map.Entry<String, Map<String, Double>> e : weightedGraph.rowMap().entrySet()) {
            System.out.println(e.getKey() + "," + e.getValue());
        }
    }
}
