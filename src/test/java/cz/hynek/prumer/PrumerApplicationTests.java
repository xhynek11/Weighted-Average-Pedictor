package cz.hynek.prumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.Math.round;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PrumerApplicationTests {

	@Test
	void itShouldConvertNumberToLetter(){
		// given
		Predmet p1 = new Predmet("TIN",6,1D);
		Predmet p2 = new Predmet("SOS",5,3D);
		//when
		p1.setZnamka(2D);
		p2.setGradeString("A");
		//then
		String expected1 = "C";
		Double expected2 = 1D;
		assertThat(p1.getZnamkaString()).isEqualTo(expected1);
		assertThat(p2.getZnamka()).isEqualTo(expected2);

	}

	@Test
	void itShouldCountAverage() {
		// given
		IndexPredmetu ip = new IndexPredmetu();
		ip.addSubjectAll("TIN",6,1D);
		ip.addSubjectAll("SOS",4,3D);
		//when
		double result =ip.calculateAverage();
		//then
		double expected = 1.8;
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void itShouldValidIndex() {
		// given
		IndexPredmetu ip = new IndexPredmetu();
		ip.addSubjectAll("TIN",6,1D);
		ip.addSubjectAll("SOS",4,3D);
		ip.addSubjectAll("EK1",1,3D);
		//when
		boolean result =ip.isValid(1.5);
		//then
		boolean expected = false;
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void itShouldValidIndex2() {
		// given
		IndexPredmetu ip = new IndexPredmetu();
		ip.addSubjectAll("TIN",6,1D);
		ip.addSubjectAll("SOS",4,2D);
		ip.addSubjectAll("EK1",1,3D);
		//when
		boolean result =ip.isValid(1.6);
		//then
		boolean expected = true;
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void itShouldValidIndex3() {
		// given
		IndexPredmetu ip = new IndexPredmetu();
		ip.addSubjectAll("TIN",6,1D);
		ip.addSubjectAll("SOS",4,2D);
		ip.addSubjectAll("EK1",1,3D);
		ip.addSubjectAll("KOM",6,1D);
		ip.addSubjectAll("AKR",6,4D);
		//when
		boolean result =ip.isValid(1.4);
		//then
		boolean expected = false;
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void itShouldReadAndSaveFile() {
		// given
		IndexPredmetu ip = new IndexPredmetu();
		ip.readFile("test1.txt");
		//when
		String result =ip.getSubjects().get(0).getNazev()+round(ip.getSubjects().get(0).getZnamka())+ip.getSubjects().get(0).getVaha();
		result +=ip.getSubjects().get(1).getNazev()+ip.getSubjects().get(1).getZnamkaString()+ip.getSubjects().get(1).getVaha();
		//then
		String expected = "IC117DAKC6";
		assertThat(result).isEqualTo(expected);
	}









}
