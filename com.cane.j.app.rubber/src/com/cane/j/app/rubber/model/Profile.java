package com.cane.j.app.rubber.model;

/**
 * Profile is the enum class for describing different types of the rubber
 * profiles.
 * 
 * @author cane
 *
 */
public enum Profile {

	P1(1, 170.0F, 160.0F), CT(2, 220.0F, 200.0F), EA(3, 240.0F, 220.0F), E3(3,
			240.0F, 220.0F) {
		public String getDescription() {
			return "HOT E3";
		};
	};

	private int code;
	private String description;
	private Float cost;
	private Float markup;

	// private final Float MIN_COST = new Float(150.0);
	// private final Float MIN_EXTRA = new Float(150.0);

	private Profile(int code) {
		this(code, 150.0F, 150.0F, "");
	}

	private Profile(int code, String desc) {
		this(code, 150.0F, 150.0F, desc);
	}

	private Profile(int code, Float cost, Float extra) {
		this(code, cost, extra, "");
	}

	private Profile(int code, Float cost, Float extra, String desc) {
		this.code = code;
		this.cost = cost;
		this.markup = extra;

		if ("".equals(desc))
			this.description = this.name();
		else
			this.description = desc;
	}

	public int getCode() {
		return code;
	}

	/**
	 * ��������� ������ ��������� ����� ��������� ���� �������
	 * 
	 * @return
	 */
	public Float getCost() {
		return cost;
	};

	/**
	 * ������� �� ��� �������
	 * 
	 * @return
	 */
	public Float getMarkup() {
		return markup;
	};

	/**
	 * profile description
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	};

	@Override
	public String toString() {
		return "[" + Profile.class.getSimpleName() + " " + name() + "; cost: "
				+ getCost().toString() + "; markup: " + getMarkup().toString()
				+ "]";
	};

}
