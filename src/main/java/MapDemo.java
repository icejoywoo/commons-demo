import java.util.HashMap;
import java.util.Map;

public class MapDemo {
	{
		System.out.println("test");
	}
	public static void main(String[] args) {
		Map<String, Integer[]> map = new HashMap<String, Integer[]>();
		Integer[] empty = {0, 0};
		map.put("A", empty.clone());
		map.put("B", empty.clone());
		System.out.println(map.get("A")[0]);
		System.out.println(map.get("A")[1]);
		System.out.println(map.get("B")[0]);
		System.out.println(map.get("B")[1]);
		map.get("A")[0] = 1;
		map.get("A")[1] = 1;
		System.out.println(map);
		System.out.println(map.get("A")[0]);
		System.out.println(map.get("A")[1]);
		System.out.println(map.get("B")[0]);
		System.out.println(map.get("B")[1]);
		
		new MapDemo() {
			{
				System.out.println("test2");
			}
		};
		
		new MapDemo() {
			{
				System.out.println("test2");
			}
		};
	}
}
