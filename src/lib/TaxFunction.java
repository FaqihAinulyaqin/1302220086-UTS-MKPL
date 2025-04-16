package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	private static final int BASIC_NON_TAXABLE_INCOME = 54000000;
	private static final int MARRIAGE_ALLOWANCE = 4500000;
	private static final int CHILD_ALLOWANCE = 1500000;
	private static final double TAX_RATE = 0.05;
	private static final int MAX_CHILDREN = 3;
	private static final int MAX_MONTHS_IN_YEAR = 12;
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > MAX_MONTHS_IN_YEAR) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > MAX_CHILDREN) {
			numberOfChildren = MAX_CHILDREN;
		}
		
		int netIncome = calculateNetIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible);
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
		int taxableIncome = calculateTaxableIncome(netIncome, nonTaxableIncome);

		return calculateAnnualTax(taxableIncome);
			 
	}

	private static int calculateNetIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible) {
		return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible;
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int nonTaxable = BASIC_NON_TAXABLE_INCOME;
		
		if (isMarried) {
			nonTaxable += MARRIAGE_ALLOWANCE;
		}
		nonTaxable += numberOfChildren * CHILD_ALLOWANCE;

		return nonTaxable;
	}

	private static int calculateTaxableIncome(int netIncome, int nonTaxableIncome) {
		return Math.max(0, netIncome - nonTaxableIncome);
	}

	private static int calculateAnnualTax(int taxableIncome) {
		return (int) Math.round(TAX_RATE * taxableIncome);
	}
}
