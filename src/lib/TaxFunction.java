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
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		int netIncome = calculateNetIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible);
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
		int taxableIncome = calculateTaxableIncome(netIncome, nonTaxableIncome);

		return calculateAnnualTax(taxableIncome);
			 
	}

	private static int calculateNetIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible) {
		return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible;
	}

	private static int calculateNonTaxableincome(boolean isMarried, int numberOfChildren) {
		int nonTaxable = 54000000;
		
		if (isMarried) {
			nonTaxable += 4500000;
		}
		nonTaxable += numberOfChildren * 1500000;

		return nonTaxable;
	}

	private static int calculateTaxableIncome(int netIncome, int nonTaxableIncome) {
		int taxable = netIncome - nonTaxableIncome;
		return Math.max(0, taxable);
	}

	private static int calculateAnnualTax(int taxableIncome) {
		return (int) Math.round(0.05 * taxableIncome);
	}
}
