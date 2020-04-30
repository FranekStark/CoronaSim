import java.util.Random;

/**
 * Main class.
 * Simulation can be started with a fixed amount of simulated days (ticks) 
 * or the simulation can be made until everyone is healthy, recovered or dead. 
 */
public class Main
{
public static void main(boolean simulateUntilNoInfections)
    {

    }
public static void main(int simulatedDays)
    {
        for(int i = 0; i < simulatedDays; ++i)
        {
            // to be coded...
        }
    }
    

public static double giveRandomLatency(){
    return RandomCounts.giveGaussianValue(MedicalSettings.latency_mean, MedicalSettings.latency_std);
    }

public static double giveRandomIncubation(){
    return RandomCounts.giveGaussianValue(MedicalSettings.incubation_mean, MedicalSettings.incubation_std);
    }

public static double giveRandomMildDuration(){
    return RandomCounts.giveGaussianValue(MedicalSettings.desease_mild_mean, MedicalSettings.desease_mild_std);
    }

public static double giveRandomHeavyDuration(){
    return RandomCounts.giveGaussianValue(MedicalSettings.desease_heavy_mean, MedicalSettings.desease_heavy_std);
    }

/**
 * Gives the amount of humans (weighted by 10000) with a specific age and a predesease
*/
public static void giveAgeAndDeseaseAmount(){
    double Age0PreDesease = (1/10000)*MedicalSettings.age_prob_0_to_7*MedicalSettings.predesease_prob_0_to_7;
    double Age0NoPreDesease = (1/10000)*MedicalSettings.age_prob_0_to_7*(1 - MedicalSettings.predesease_prob_0_to_7);

    double Age8PreDesease = (1/10000)*MedicalSettings.age_prob_8_to_17*MedicalSettings.predesease_prob_8_to_17;
    double Age8NoPreDesease = (1/10000)*MedicalSettings.age_prob_8_to_17*(1 - MedicalSettings.predesease_prob_8_to_17);

    double Age18PreDesease = (1/10000)*MedicalSettings.age_prob_18_to_29*MedicalSettings.predesease_prob_18_to_29;
    double Age18NoPreDesease = (1/10000)*MedicalSettings.age_prob_18_to_29*(1 - MedicalSettings.predesease_prob_18_to_29);

    double Age30PreDesease = (1/10000)*MedicalSettings.age_prob_30_to_39*MedicalSettings.predesease_prob_30_to_39;
    double Age30NoPreDesease = (1/10000)*MedicalSettings.age_prob_30_to_39*(1 - MedicalSettings.predesease_prob_30_to_39);

    double Age40PreDesease = (1/10000)*MedicalSettings.age_prob_40_to_49*MedicalSettings.predesease_prob_40_to_49;
    double Age40NoPreDesease = (1/10000)*MedicalSettings.age_prob_40_to_49*(1 - MedicalSettings.predesease_prob_40_to_49);

    double Age50PreDesease = (1/10000)*MedicalSettings.age_prob_50_to_59*MedicalSettings.predesease_prob_50_to_59;
    double Age50NoPredesease = (1/10000)*MedicalSettings.age_prob_50_to_59*(1 - MedicalSettings.predesease_prob_50_to_59);

    double Age60PreDesease = (1/10000)*MedicalSettings.age_prob_60_to_69*MedicalSettings.predesease_prob_60_to_69;
    double Age60NoPreDesease = (1/10000)*MedicalSettings.age_prob_60_to_69*(1 - MedicalSettings.predesease_prob_60_to_69);

    double Age70PreDesease = (1/10000)*MedicalSettings.age_prob_70_to_79*MedicalSettings.predesease_prob_70_to_79;
    double Age70NoPreDesease = (1/10000)*MedicalSettings.age_prob_70_to_79*(1 - MedicalSettings.predesease_prob_70_to_79);

    double Age80PreDesease = (1/10000)*MedicalSettings.age_prob_80_to_89*MedicalSettings.predesease_prob_80_to_89;
    double Age80NoPreDesease = (1/10000)*MedicalSettings.age_prob_80_to_89*(1 - MedicalSettings.predesease_prob_80_to_89);

    double Age90PreDesease = (1/10000)*MedicalSettings.age_prob_90_to_100*MedicalSettings.predesease_prob_90_to_100;
    double Age90NoPreDesease = (1/10000)*MedicalSettings.age_prob_90_to_100*(1 - MedicalSettings.predesease_prob_90_to_100);
}


