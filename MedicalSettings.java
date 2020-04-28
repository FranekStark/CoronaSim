
/**
 * This Class contains all Magic-Numbers which regards the medical topic
 */
public class MedicalSettings {

    
    /**
     * How many ticks is a infection long
     */
    public static int infection_duration = 14; //In ticks

    public static int infection_probability = 70; //In %

    public static int reinfection_probability = 0; //In %

    /**
     * real data age distribution in %
     * [source: https://service.destatis.de/]
     */
    public static final double age_prob_0_to_7 = 8;
    public static final double age_prob_8_to_17 = 9;
    public static final double age_prob_18_to_29 = 13;
    public static final double age_prob_30_to_39 = 11;
    public static final double age_prob_40_to_49 = 13;
    public static final double age_prob_50_to_59 = 17;
    public static final double age_prob_60_to_69 = 13;
    public static final double age_prob_70_to_79 = 9;
    public static final double age_prob_80_to_89 = 6;
    public static final double age_prob_80_to_100 = 1;

    /**
     * data for the latency time (assuming gaussian)
     * [source: rki.de]
     */
    public static double latency_mean = 1.5;
    public static double latency_std = 4.25;

    /**
     * data for the incubation time (assuming gaussian)
     * [source: rki.de]
     */
    public static double incubation_mean = 4.;
    public static double incubation_std = 4.25;

    /**
     * data for the desease duration for mild symptoms (assuming gaussian)
     * [sorce: who.int]
     */
    public static double desease_mild_mean = 14;
    public static double desease_mild_std = 5.18;

    /**
     * data for the desease duration for heavy symptoms (assuming gaussian)
     * [source: who.int]
     */
    public static double desease_heavy_mean = 30;
    public static double desease_heavy_std = 5.18;

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
    public static final double predesease_prob_80_to_100 = 7.7;


}