import com.cane.j.app.rubber.model.Profile;

public class ProfileTest {

	public static void main(String[] args) {
		for (Profile p : Profile.values()) {
			System.out.print("Profile name: " + p.name());
			System.out.print("; code: " + p.getCode());
			System.out.print("; id: " + p.ordinal());
			System.out.print("; string: " + p.toString());
			System.out.print("; description: " + p.getDescription());
			System.out.print("\n");
			// System.out.println();
		}
	}

}
