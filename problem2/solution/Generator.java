package solution;

public class Generator {
	
	private int gornjaGranica;
	private int donjaGranica;
	
	public Generator(int donjaGranica, int gornjaGranica) {
		this.donjaGranica = donjaGranica;
		this.gornjaGranica = gornjaGranica;
	}
	
	public int slucajanBroj() {
		return (int) Math.round(Math.random() * Math.abs(gornjaGranica-donjaGranica));
	}
	
//	public static void main(String[] args) {
//		Generator g1 = new Generator(0, 10);
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//		System.out.println(g1.slucajanBroj());
//	}
	
//	private static Generator generator;
//	public Generator() {
//	}
//	public static Generator getInstance(int donjaGranica, int gornjaGranica) {
//		if (generator == null) generator = new Generator();
//		generator.donjaGranica = donjaGranica;
//		generator.gornjaGranica = gornjaGranica;
//		return generator;
//	}
//	Generator g1 = Generator.getInstance(0, 10);
	
}
