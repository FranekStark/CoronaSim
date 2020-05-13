import java.util.HashMap;
import java.util.Map;

/**
 * This Class contains all Magic-Numbers regarding the medical system. And the statistical data.
 */
public class MedicalSettings {

    /**
     * Describes the medical situation. 1 for the best medical system, 0 for the
     * badest. Affects the recoverey of people with heavy symptoms.
     **/
    public static double medical_factor = 0.9;

    /**
     * Describes the likelihood of infection if a healthy human is on the same cell
     * with a infected one.
     */
    public static int infection_probability = 70; // In %

    /**
     * Describes the likelihood of infection after recovery: 0 for COVID-19
     */
    public static int reinfection_probability = 0; // In %

    /**
     * real data age distribution in % [source: https://service.destatis.de/]
     */
    public static double age_prob_0_to_7 = 8;
    public static double age_prob_8_to_17 = 9;
    public static double age_prob_18_to_29 = 13;
    public static double age_prob_30_to_39 = 11;
    public static double age_prob_40_to_49 = 13;
    public static double age_prob_50_to_59 = 17;
    public static double age_prob_60_to_69 = 13;
    public static double age_prob_70_to_79 = 9;
    public static double age_prob_80_to_89 = 6;
    public static double age_prob_90_to_100 = 1;

    /**
     * data for the latency time (assuming gaussian) [source: rki.de]
     */
    public static double latency_mean = 1.5;
    public static double latency_std = 4.25;

    /**
     * data for the incubation time (assuming gaussian) [source: rki.de]
     */
    public static double incubation_mean = 4.;
    public static double incubation_std = 4.25;

    /**
     * data for the desease duration for mild symptoms (assuming gaussian) [sorce:
     * who.int]
     */
    public static double desease_mild_mean = 14;
    public static double desease_mild_std = 5.18;

    /**
     * data for the desease duration for heavy symptoms (assuming gaussian) [source:
     * who.int]
     */
    public static double desease_heavy_mean = 30;
    public static double desease_heavy_std = 5.18;

    /**
     * data for the time in the hospital (assuming gaussian)
     * [source: rki.de]
     */
    public static double hospital_time_mean = 10;
    public static double hospital_time_std = 5.18;

    /**
     * real data probability of having a lung desease (besides COVID-19) in %
     * [source: rki.de]
     */
    public static final double predesease_prob_0_to_7 = 4;
    public static final double predesease_prob_8_to_17 = 4;
    public static final double predesease_prob_18_to_29 = 4;
    public static final double predesease_prob_30_to_39 = 4.4;
    public static final double predesease_prob_40_to_49 = 4.4;
    public static final double predesease_prob_50_to_59 = 5.2;
    public static final double predesease_prob_60_to_69 = 6;
    public static final double predesease_prob_70_to_79 = 7.7;
    public static final double predesease_prob_80_to_89 = 7.7;
    public static final double predesease_prob_90_to_100 = 7.7;

 
    public static int giveRandomLatency() {
        return (int)RandomCounts.giveGaussianValue(MedicalSettings.latency_mean, MedicalSettings.latency_std);
    }

    public static int giveRandomIncubation() {
        return(int) RandomCounts.giveGaussianValue(MedicalSettings.incubation_mean, MedicalSettings.incubation_std);
    }

    public static int giveRandomMildDuration() {
        return (int)RandomCounts.giveGaussianValue(MedicalSettings.desease_mild_mean, MedicalSettings.desease_mild_std);
    }

    public static int giveRandomHeavyDuration() {
        return (int)RandomCounts.giveGaussianValue(MedicalSettings.desease_heavy_mean, MedicalSettings.desease_heavy_std);
    }

    public static int giveRandomTimeInHospital(){
        return (int) RandomCounts.giveGaussianValue(MedicalSettings.hospital_time_mean, MedicalSettings.hospital_time_std);
    }
    


    public static  Map<Integer, AgeDistributal> giveAgeDistribution() {
        Map<Integer, AgeDistributal> distribution = new HashMap<>();

           /**
     * Gives the amount of humans (weighted by 10000) with a specific age and a
     * predesease
     */
     final double age0PreDesease = (1.0 / 10000.0) * age_prob_0_to_7 * predesease_prob_0_to_7;
     final double age0NoPreDesease = (1.0 / 10000.0) * age_prob_0_to_7 * (1 - predesease_prob_0_to_7);

     final double age8PreDesease = (1.0 / 10000.0) * age_prob_8_to_17 * predesease_prob_8_to_17;
     final double age8NoPreDesease = (1.0 / 10000.0) * age_prob_8_to_17 * (1 - predesease_prob_8_to_17);

     final double age18PreDesease = (1.0 / 10000.0) * age_prob_18_to_29 * predesease_prob_18_to_29;
     final double age18NoPreDesease = (1.0 / 10000.0) * age_prob_18_to_29 * (1 - predesease_prob_18_to_29);

     final double age30PreDesease = (1.0 / 10000.0) * age_prob_30_to_39 * predesease_prob_30_to_39;
     final double age30NoPreDesease = (1.0 / 10000.0) * age_prob_30_to_39 * (1 - predesease_prob_30_to_39);

     final double age40PreDesease = (1.0 / 10000.0) * age_prob_40_to_49 * predesease_prob_40_to_49;
     final double age40NoPreDesease = (1.0 / 10000.0) * age_prob_40_to_49 * (1 - predesease_prob_40_to_49);

     final double age50PreDesease = (1.0 / 10000.0) * age_prob_50_to_59 * predesease_prob_50_to_59;
     final double age50NoPreDesease = (1.0 / 10000.0) * age_prob_50_to_59 * (1 - predesease_prob_50_to_59);

     final double age60PreDesease = (1.0 / 10000.0) * age_prob_60_to_69 * predesease_prob_60_to_69;
     final double age60NoPreDesease = (1.0 / 10000.0) * age_prob_60_to_69 * (1 - predesease_prob_60_to_69);

     final double age70PreDesease = (1.0 / 10000.0) * age_prob_70_to_79 * predesease_prob_70_to_79;
     final double age70NoPreDesease = (1.0 / 10000.0) * age_prob_70_to_79 * (1 - predesease_prob_70_to_79);

     final double age80PreDesease = (1.0 / 10000.0) * age_prob_80_to_89 * predesease_prob_80_to_89;
     final double age80NoPreDesease = (1.0 / 10000.0) * age_prob_80_to_89 * (1 - predesease_prob_80_to_89);

     final double age90PreDesease = (1.0 / 10000.0) * age_prob_90_to_100 * predesease_prob_90_to_100;
     final double age90NoPreDesease = (1.0 / 10000.0) * age_prob_90_to_100 * (1 - predesease_prob_90_to_100);

        distribution.put(0, new AgeDistributal(age0PreDesease, age0NoPreDesease));
        distribution.put(8, new AgeDistributal(age8PreDesease, age8NoPreDesease));
        distribution.put(18, new AgeDistributal(age18PreDesease, age18NoPreDesease));
        distribution.put(30, new AgeDistributal(age30PreDesease, age30NoPreDesease));
        distribution.put(40, new AgeDistributal(age40PreDesease, age40NoPreDesease));
        distribution.put(50, new AgeDistributal(age50PreDesease, age50NoPreDesease));
        distribution.put(60, new AgeDistributal(age60PreDesease, age60NoPreDesease));
        distribution.put(70, new AgeDistributal(age70PreDesease, age70NoPreDesease));
        distribution.put(80, new AgeDistributal(age80PreDesease, age80NoPreDesease));
        distribution.put(90, new AgeDistributal(age90PreDesease, age90NoPreDesease));

        return distribution;
    }

    public static class AgeDistributal{
        public  final double _PreDesease;
        public final double _NoPreDesease;

        public AgeDistributal(double predesease, double nopredesease){
            _PreDesease = predesease;
            _NoPreDesease = nopredesease;
        }
    }



}